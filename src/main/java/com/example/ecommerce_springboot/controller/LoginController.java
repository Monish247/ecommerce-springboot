package com.example.ecommerce_springboot.controller;

import com.example.ecommerce_springboot.model.Customer;
import com.example.ecommerce_springboot.repository.CustomerRepository;
import com.example.ecommerce_springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && customer.getPassword().equals(password)) {
            // Đăng nhập thành công
            model.addAttribute("customer", customer);
            return "welcome"; // chuyển tới trang chào mừng
        } else {
            // Đăng nhập thất bại
            model.addAttribute("error", "Invalid email or password");
            return "login"; // quay lại trang đăng nhập
        }
    }
    @GetMapping("/forgotpassword")
    public String showForgotPasswordPage() {
        return "forgotpassword";  // Tên file HTML của trang forgot password
    }
}