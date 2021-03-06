package com.example.cong.controller;

import com.example.cong.entitis.*;
import com.example.cong.service.CartItemService;
import com.example.cong.service.CustomerService;
import com.example.cong.service.GoodService;
import com.example.cong.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/banhang")
public class SellController {

    @Autowired
    CartItemService cartItemService;

    @Autowired
    CustomerService customerService;

    @Autowired
    GoodService goodService;

    @Autowired
    BillService billService;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    @GetMapping("/home")
    public String getHome(HttpSession session, Model model) {
        List<Goods> listMatHang = goodService.getListGood();
        List<Customer> listCustomer = customerService.getAllCustomer();
        List<CartItem> listCartItem = new ArrayList<>();
        Bill bill = new Bill();
        //bill.setCustomer(customerService.getCustomerById(1));
        //lưu list vào session
        bill.setCustomer(new Customer());
        session.setAttribute(Constants.LIST_MAT_HANG, listMatHang);
        session.setAttribute(Constants.BILL, bill);
        session.setAttribute(Constants.LIST_KHACH_HANG, listCustomer);
        session.setAttribute(Constants.LIST_CART, listCartItem);
        return "seeling";
    }

    @GetMapping("/clickKhachHang")
    public String clickKhachHang(HttpSession session, @RequestParam("id") int id) {
        Bill bill = (Bill) session.getAttribute(Constants.BILL);
        bill.setCustomer(customerService.getCustomerById(id));
        session.setAttribute(Constants.BILL, bill);
        return "seeling";
    }

    @PostMapping("/addsanpham")
    public String addSanPham(Model model,
                             HttpSession session,
                             @Valid @RequestParam(value = "key", required = false) String key,
                             @RequestParam("soLuong") String soLuong) {
        System.out.println("key" + key);
        if (key == null) {
            model.addAttribute("error_chon", "Xin vui long chon 1 san pham");
            return "seeling";
        }
        int intSoLuong = 0;
        try {
            intSoLuong = Integer.parseInt(soLuong);
            if (intSoLuong < 0) {
                model.addAttribute("error_so_luong", "ban nhap so luong sai roi");
                return "seeling";
            }
        } catch (Exception e) {
            model.addAttribute("error_so_luong", "vui long nhap dung dinh dang");
            return "seeling";
        }

        List<CartItem> cartList = (List<CartItem>) session.getAttribute(Constants.LIST_CART);
        Goods matHang = goodService.getGoodById(Integer.parseInt(key));

        boolean check = cartItemService.checkExistCartItem(cartList, matHang);
        //setData for Item
        if (check) {
            for (CartItem item : cartList) {
                if (item.getGoods().getId() == matHang.getId()) {
                    item.setAmount(item.getAmount() + intSoLuong);
                    item.setTotalPrice(cartItemService.getPriceFromCartItem(intSoLuong, matHang.getPrice()));
                    break;
                }
            }
        } else {
            CartItem itemCart = new CartItem();
            itemCart.setName(matHang.getName());
            itemCart.setPrice(matHang.getPrice());
            itemCart.setTotalPrice(matHang.getPrice() * intSoLuong);
            itemCart.setTotalPrice(cartItemService.getPriceFromCartItem(intSoLuong, matHang.getPrice()));
            itemCart.setAmount(intSoLuong);
            itemCart.setGoods(matHang);
            cartList.add(itemCart);
        }
        int soLuongTong = 0;
        int tongTien = 0;
        soLuongTong = cartItemService.getSumQuantityCart(cartList);
        tongTien = cartItemService.getSumOfListCart(cartList);
        Bill bill = (Bill) session.getAttribute(Constants.BILL);
        bill.setTotalPrice(tongTien);
        bill.setTotalAmount(soLuongTong);
        session.setAttribute(Constants.BILL, bill);
        session.setAttribute(Constants.LIST_CART, cartList);
        return "seeling";
    }

    @GetMapping(path = "/xoaSanPham")
    public String deleteSanPham(@RequestParam("id") long id, HttpSession session) {
        List<CartItem> cartList = (List<CartItem>) session.getAttribute(Constants.LIST_CART);
        cartList = cartItemService.handleDeleteCartItem(id, cartList);
        int soLuongTong;
        int tongTien;
        soLuongTong = cartItemService.getSumQuantityCart(cartList);
        tongTien = cartItemService.getSumOfListCart(cartList);
        Bill bill = (Bill) session.getAttribute(Constants.BILL);
        bill.setTotalPrice(tongTien);
        bill.setTotalAmount(soLuongTong);
        session.setAttribute(Constants.BILL, bill);
        session.setAttribute(Constants.LIST_CART, cartList);
        return "seeling";
    }


    @PostMapping(path = "/suaSanPham")
    public String editSanPham(@RequestParam("id") long id,@RequestParam("soLuongSua") int soLuongSua, HttpSession session) {
        List<CartItem> cartList = (List<CartItem>) session.getAttribute(Constants.LIST_CART);
        cartList = cartItemService.handleEditCartItem(id, soLuongSua, cartList);
        int soLuongTong;
        int tongTien;
        soLuongTong = cartItemService.getSumQuantityCart(cartList);
        tongTien = cartItemService.getSumOfListCart(cartList);
        Bill bill = (Bill) session.getAttribute(Constants.BILL);
        bill.setTotalPrice(tongTien);
        bill.setTotalAmount(soLuongTong);
        session.setAttribute(Constants.BILL, bill);
        session.setAttribute(Constants.LIST_CART, cartList);
        return "seeling";
    }


    @PostMapping(path = "/confirm")
    public String gotoConfirm(HttpSession session, @RequestParam("giamGia") String giamGia,
                              @Valid @RequestParam(value = "xu", required = false) String xu,
                              @RequestParam("id") int id, Model model)
    {

        List<CartItem> cartList = (List<CartItem>) session.getAttribute(Constants.LIST_CART);
        Bill bill = (Bill) session.getAttribute(Constants.BILL);
        if (bill.getCustomer().getName() == null) {
            model.addAttribute("error_khach_hang", "Vui lòng thêm một khách hàng");
            return "seeling";
        }
        if (cartList.size() == 0) {
            model.addAttribute("error_khach_hang", "Vui lòng thêm một mặt hàng");
            return "seeling";
        }
        int intGiamGia = 0;
        try {
            intGiamGia = Integer.parseInt(giamGia);
            if (intGiamGia > 100 || intGiamGia < 0) {
                model.addAttribute("error_giam_gia", "Vui lòng nhập giá trị là số nguyen từ 1 đến 100");
                return "seeling";
            }
        } catch (Exception e) {
            model.addAttribute("error_giam_gia", "Vui lòng nhập giá trị là số nguyen từ 1 đến 100");
            return "seeling";
        }
        bill.setDiscount(intGiamGia);
        int tongTien = bill.getTotalPrice();
        int tienSauGiamGia = 0;
        int tienSauKM = billService.getPriceAfterSale(bill);
        if (xu != null) {
            tienSauGiamGia = tienSauKM - customerService.getCustomerById(id).getTotalCoins();
            if (tienSauGiamGia < 0) {
                tienSauGiamGia = 0;
                bill.setCoinsPay(tienSauKM);
            } else {
                bill.setCoinsPay(customerService.getCustomerById(id).getTotalCoins());
            }
        } else {
            tienSauGiamGia = tienSauKM;
            bill.setCoinsPay(0);
        }
        bill.setActualPrice(tienSauGiamGia);
        bill.setCustomer(customerService.getCustomerById(id));
        bill.setDiscount(intGiamGia);
        Customer customer = customerService.getCustomerById(bill.getCustomer().getId());
        session.setAttribute("xuNhan", billService.getCoinWhenSave(bill, customer));
        return "confirm-selling";
    }

    @PostMapping(path = "/saveHoaDon")
    public String saveHoaDon(
            RedirectAttributes redirectAttributes,
            Model model,
            HttpSession session,
            @RequestParam("tienThua") int tienThua,
            @RequestParam("tienKhachTra") int tienKhachTraInt
            )
    {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<CartItem> cartList = (List<CartItem>) session.getAttribute(Constants.LIST_CART);
        Staff nhanVien = (Staff) session.getAttribute("nhanvien");
        Bill billBanHang = (Bill) session.getAttribute(Constants.BILL);
        billBanHang.setDate(dateFormat.format(new Date()));
        billBanHang.setStaff(nhanVien);
        billBanHang.setPriceBack(tienThua);
        billBanHang.setPricePay(tienKhachTraInt);
        Customer customer = customerService.getCustomerById(billBanHang.getCustomer().getId());
        customer.setTotalCoins(billService.getCoinAftefSaveBll(billBanHang, customer));
        //
        customerService.edtiCustomer(customer.getId(), customer);
        Staff staff = (Staff) session.getAttribute("staff");
        billBanHang.setStaff(staff);
        Bill billBH = billService.saveItem(billBanHang);
        for(CartItem item: cartList) {
            item.setBill(billBH);
            cartItemService.saveItem(item);
        }
        //back back
        session.setAttribute("message", "Success");
        return "redirect:/banhang/home";
    }

}
