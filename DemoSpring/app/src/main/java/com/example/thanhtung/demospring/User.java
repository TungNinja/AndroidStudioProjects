package com.example.thanhtung.demospring;

/**
 * Created by ThanhTung on 01/08/2017.
 */

public class User {

    private int id;

    private String name;

    private int age;

    private String createdDate;

    private String hinhAnh;


    public User(int id, String name, String createdDate) {
        this.name = name;
        this.id = id;
        this.createdDate = createdDate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
