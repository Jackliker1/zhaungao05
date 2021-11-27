package com.bawei.shoppingcar.entity;

public class ShoppingCarEntity {

    private String title;
    private String imageUrl;
    private int num = 1;
    private float price;
    private float sumPrice;
    private boolean isCheck = false;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public ShoppingCarEntity() {
    }

    public ShoppingCarEntity(String title, String imageUrl, int num, float price) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.num = num;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSumPrice() {
        return price * num;
    }

    public void setSumPrice(float sumPrice) {
        this.sumPrice = sumPrice;
    }
}
