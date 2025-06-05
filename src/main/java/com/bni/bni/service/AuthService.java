package com.bni.bni.service;

import com.bni.bni.entity.User;
import com.bni.bni.repository.UserRepository;
import com.bni.bni.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

  public String register(String username, String password, String emailAddress) { // Menambahkan emailAddress
        if (repo.existsByUsername(username)) {
            return "User already exists";
        }
        if (repo.existsByEmailAddress(emailAddress)) { // Pengecekan baru untuk email
            return "Email address already exists";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password)); // Mengubah setPasswordHash menjadi setPassword
        user.setCreatedAt(OffsetDateTime.now());
        user.setEmailAddress(emailAddress); // Menetapkan email address
        user.setIsActive(true); // Default true berdasarkan skema database
        user.setUpdatedAt(OffsetDateTime.now()); // Menetapkan waktu update awal
        // Jika Anda ingin mempertahankan role, Anda perlu menambahkannya kembali ke entitas User dan skema DB
        // user.setRole("USER"); // Hapus atau tambahkan kembali kolom 'role' jika diperlukan
        repo.save(user);

        return "Registered successfully";
    }

    public String login(String username, String password) {
        Optional<User> user = repo.findByUsername(username);
        if (user.isPresent() && encoder.matches(password, user.get().getPassword())) { // Mengubah getPasswordHash menjadi getPassword
            // Jika Anda ingin menggunakan is_active sebagai bagian dari login, tambahkan pengecekan di sini
            if (!user.get().getIsActive()) {
                return "User is not active";
            }
            // Jika Anda mempertahankan kolom 'role' di entitas User, gunakan user.get().getRole()
            return jwtUtil.generateToken(username, "USER"); // Perhatikan: 'USER' hardcoded jika role dihapus dari DB
        }

        return null;
    }
}