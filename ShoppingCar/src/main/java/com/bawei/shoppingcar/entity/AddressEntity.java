package com.bawei.shoppingcar.entity;

public class AddressEntity {

    private int id;
    private String name;
    private String phone;
    private String province;
    private String home;
    private String place;
    private int isCheck;

    public AddressEntity() {
    }

    public AddressEntity(int id, String name, String phone, String province, String home, String place, int isCheck) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.home = home;
        this.place = place;
        this.isCheck = isCheck;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }
}
