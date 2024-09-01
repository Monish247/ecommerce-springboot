package com.example.ecommerce_springboot.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce_springboot.model.Customer;
import com.example.ecommerce_springboot.service.CustomerService;
import com.example.ecommerce_springboot.service.EmailService;

@RestController
@RequestMapping("/api")
public class ForgotPasswordController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        Customer customer = customerService.findByEmail(email);
        
        if (customer == null) {
            return ResponseEntity.badRequest().body("Email không tồn tại trong hệ thống!");
        }

        // Tạo mã token reset password duy nhất
        String resetToken = UUID.randomUUID().toString();

        // Tạo link đặt lại mật khẩu
        String resetPasswordLink = "http://localhost:8080/resetpassword?token=" + resetToken;

        // Lưu token vào cơ sở dữ liệu hoặc liên kết nó với người dùng
        customer.setResetToken(resetToken);
        customerService.saveCustomerWithoutEncryption(customer);

        // Gửi email với liên kết đặt lại mật khẩu
        emailService.sendResetPasswordEmail(email, resetPasswordLink);

        return ResponseEntity.ok("Password reset link has been sent!");
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        String newPassword = payload.get("password");

        Customer customer = customerService.findByResetToken(token);
        if (customer == null) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }

        // Cập nhật mật khẩu mới cho khách hàng
        customer.setPassword(customerService.encryptPassword(newPassword));
        customer.setResetToken(null);  // Xóa token sau khi đã sử dụng
        customerService.saveCustomerWithoutEncryption(customer);

        return ResponseEntity.ok("Password has been successfully reset.");
    }
}
