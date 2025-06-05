package com.bni.bni.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "email_address", nullable = false) // Kolom baru
    private String emailAddress;

    @Column(name = "is_active", nullable = false) // Kolom baru
    private Boolean isActive;

    @Column(name = "updated_at", nullable = false) // Kolom baru
    private OffsetDateTime updatedAt;


    public User() {
      // default constructor
    }

    public User(String username, String password, String role, OffsetDateTime createdAt, String emailAddress, Boolean isActive, OffsetDateTime updatedAt) {
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
        this.emailAddress = emailAddress;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

     // Mengubah nama getter/setter dari getPasswordHash menjadi getPassword
    public String getPassword() {
        return password;
    }

    // Mengubah nama getter/setter dari setPasswordHash menjadi setPassword
    public void setPassword(String password) {
        this.password = password;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Getter dan Setter untuk properti baru
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}