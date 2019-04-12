package com.example.chortoqsanatoriyasi.Model;

public class Users {
    private String phone,parol,ism;
    public Users(){}

    public Users(String phone, String parol, String ism) {
        this.phone = phone;
        this.parol = parol;
        this.ism = ism;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParol() {
        return parol;
    }

    public void setParol(String parol) {
        this.parol = parol;
    }

    public String getIsm() {
        return ism;
    }

    public void setIsm(String ism) {
        this.ism = ism;
    }
}
