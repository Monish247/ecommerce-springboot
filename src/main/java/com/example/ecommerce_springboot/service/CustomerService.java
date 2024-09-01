package com.example.ecommerce_springboot.service;

import com.example.ecommerce_springboot.model.Customer;
import com.example.ecommerce_springboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Tìm kiếm người dùng qua email
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // Tìm kiếm người dùng qua reset token
    public Customer findByResetToken(String token) {
        return customerRepository.findByResetToken(token);
    }

    // Lưu người dùng mới với mật khẩu đã được mã hóa
    public void saveCustomer(Customer customer) {
        customer.setPassword(encryptPassword(customer.getPassword()));
        customerRepository.save(customer);
    }

    // Lưu người dùng mà không mã hóa mật khẩu
    public void saveCustomerWithoutEncryption(Customer customer) {
        customerRepository.save(customer);
    }

    // Mã hóa mật khẩu bằng SHA-256
    public String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while encrypting password: " + e.getMessage(), e);
        }
    }

    // Kiểm tra mật khẩu người dùng với mật khẩu đã mã hóa
    public boolean checkPassword(Customer customer, String rawPassword) {
        String encryptedPassword = encryptPassword(rawPassword);
        return customer.getPassword().equals(encryptedPassword);
    }

    // Đặt lại mật khẩu mới cho người dùng
    public void resetPassword(String email, String newPassword) {
        Customer customer = findByEmail(email);
        if (customer != null) {
            customer.setPassword(encryptPassword(newPassword)); // Mã hóa mật khẩu mới
            customer.setResetToken(null); // Xóa token sau khi sử dụng
            customerRepository.save(customer);
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    // Đặt lại mật khẩu mới cho người dùng bằng token
    public void resetPasswordByToken(String token, String newPassword) {
        Customer customer = findByResetToken(token);
        if (customer != null) {
            customer.setPassword(encryptPassword(newPassword)); // Mã hóa mật khẩu mới
            customer.setResetToken(null); // Xóa token sau khi sử dụng
            customerRepository.save(customer);
        } else {
            throw new RuntimeException("Invalid token.");
        }
    }

    // Đặt lại mật khẩu mà không mã hóa (chỉ sử dụng cho mục đích đặc biệt)
    public void resetPasswordWithoutEncryption(String email, String newPassword) {
        Customer customer = findByEmail(email);
        if (customer != null) {
            customer.setPassword(newPassword); // Đặt mật khẩu mà không mã hóa
            customer.setResetToken(null); // Xóa token sau khi sử dụng
            customerRepository.save(customer);
        } else {
            throw new RuntimeException("User not found with email: " + email);
        }
    }
}
