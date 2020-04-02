package com.student.model;

import com.google.gson.annotations.SerializedName;

public class StudentResult {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @SerializedName("parent_phone")
    private String parentPhone;

    @SerializedName("gender")
    private String gender;

    @SerializedName("password")
    private String password;

    @SerializedName("phone_verified")
    private String phoneVerified;

    @SerializedName("email_verified")
    private String emailVerified;

    @SerializedName("status")
    private String status;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    public StudentResult(Integer id, String name, String phone, String email, String parentPhone, String gender, String password, String phoneVerified, String emailVerified, String status, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.parentPhone = parentPhone;
        this.gender = gender;
        this.password = password;
        this.phoneVerified = phoneVerified;
        this.emailVerified = emailVerified;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneVerified() {
        return phoneVerified;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
