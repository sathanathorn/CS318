package com.example.demo.model;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // // Getters and setters <-- แทนที่ตรงนี้ด้วยโค้ดจริง

    // 1. **GETTER** สำหรับ username (จำเป็นสำหรับ UserDetailsServiceImpl)
    public String getUsername() {
        return username;
    }

    // 2. **GETTER** สำหรับ password (จำเป็นสำหรับ UserDetailsServiceImpl)
    public String getPassword() {
        return password;
    }

    // 3. **SETTER** สำหรับ username (อาจจำเป็นสำหรับการบันทึก/อัปเดตข้อมูล)
    public void setUsername(String username) {
        this.username = username;
    }

    // 4. **SETTER** สำหรับ password (อาจจำเป็นสำหรับการบันทึก/อัปเดตข้อมูล)
    public void setPassword(String password) {
        this.password = password;
    }
}
