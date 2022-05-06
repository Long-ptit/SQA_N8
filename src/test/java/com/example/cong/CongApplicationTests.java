package com.example.cong;

import com.example.cong.entitis.Customer;
import com.example.cong.service.CustomerService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class CongApplicationTests {

    @Autowired
    private CustomerService customerService;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetCustomerByID(){
        //excep
        Customer c = customerService.getCustomerById(0);
        Assert.assertNull("Null", c);
        //standard
        Customer d = customerService.getCustomerById(1);
        Assert.assertNotNull("K null",d);
        Assert.assertEquals("cong@gmail.com", d.getEmail());

    }

    @Test
    void testGetCustomerByphone(){
        //except
        List<Customer> list = customerService.getCustomerByPhone("xxxxxxxx");
        Assert.assertEquals(0, list.size());
        //
        list = customerService.getCustomerByPhone("0337595001");
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("Bùi Minh Công", list.get(0).getName());

        list = customerService.getCustomerByPhone("");
        Assert.assertEquals(3, list.size());

        list = customerService.getCustomerByPhone("033");
        Assert.assertEquals(2, list.size());

    }

    @Test
    void testGetCustomers(){
        List<Customer> list = customerService.getAllCustomer();
        Assert.assertNotNull(list);
        Assert.assertEquals(4,list.size());

    }

    @Test
    void testSaveCustoemr(){
        Customer c = new Customer("Hà Nội", "01687152321", "Huyen@gmail.com", "Nguen Huyen", 0,1);

        customerService.saveCustoemr(c);
        Assert.assertNotNull(c);
        Assert.assertTrue(15 < c.getId()); // test correct id
        Assert.assertEquals(5,customerService.getAllCustomer().size());


        Customer cu1 = customerService.getCustomerById(c.getId());
        Assert.assertEquals("01687152321", cu1.getPhone());

    }

    @Test
    void updateCustomer(){

            Customer c = customerService.getCustomerById(21);
            c.setName("Lý Thị Hòa");
            c.setPhone("0123456789");
            c.setAddress("Bac Kan");
            c.setEmail("hoa@gmail.com");
            customerService.edtiCustomer(21, c);
            Assert.assertEquals(c.getId(), 21);
            Assert.assertEquals(c.getName(), "Lý Thị Hòa");

    }

    @Test
    void delete(){
        Customer c = customerService.getCustomerById(22);
        Assert.assertNotNull(c);

        customerService.deleteCustomerById(c.getId());
        Assert.assertEquals(c.getIsActive() , 0);
    }

}
