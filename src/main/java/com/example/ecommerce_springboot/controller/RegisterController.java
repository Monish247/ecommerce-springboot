package com.example.ecommerce_springboot.controller;

import com.example.ecommerce_springboot.model.Customer;
import com.example.ecommerce_springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";  // Tên file HTML trong thư mục templates
    }

    @PostMapping("/api/register")
    @ResponseBody
    public String registerUser(@RequestBody Customer customer) {
        // Kiểm tra xem email đã tồn tại chưa
        if (customerService.findByEmail(customer.getEmail()) != null) {
            return "Email đã được sử dụng!";
        }

        // Lưu khách hàng mà không mã hóa mật khẩu
        customerService.saveCustomerWithoutEncryption(customer);
        return "Người dùng đã đăng ký thành công!";
    }
}
