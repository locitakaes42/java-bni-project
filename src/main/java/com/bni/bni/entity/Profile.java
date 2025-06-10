package com.bni.bni.entity;

import jakarta.persistence.*;
import java.time.LocalDate; // Import for date_of_birth
import java.time.OffsetDateTime; // Import for created_at and updated_at

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true) // user_id should be unique as per the previous discussion for one-to-one
    @JsonIgnore //Gonna fix later
    private User user;

    @Column(name = "first_name", nullable = false)
    private String firstName; // Matches first_name in DB

    @Column(name = "last_name")
    private String lastName; // Matches last_name in DB

    @Column(name = "place_of_birth")
    private String placeOfBirth; // Matches place_of_birth in DB

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth; // Matches date_of_birth (DATE type) in DB

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt; // Matches created_at in DB

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt; // Matches updated_at in DB

    public Profile() {
    }

    // Constructor adjusted for new fields
    public Profile(User user, String firstName, String lastName, String placeOfBirth, LocalDate dateOfBirth, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // --- Getters and Setters for all fields ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}