package com.bawei.shoppingcar.entity;

public class PayEntity {

    private int id;
    private String orderNumber;
    private int goodsId;
    private String goodsImgUrl;
    private float goodsPrice;
    private int goodsCount;
    private float goodsTotalValue;

    public PayEntity() {
    }

    public PayEntity(int id, String orderNumber, int goodsId, String goodsImgUrl, float goodsPrice, int goodsCount, float goodsTotalValue) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.goodsId = goodsId;
        this.goodsImgUrl = goodsImgUrl;
        this.goodsPrice = goodsPrice;
        this.goodsCount = goodsCount;
        this.goodsTotalValue = goodsTotalValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImgUrl() {
        return goodsImgUrl;
    }

    public void setGoodsImgUrl(String goodsImgUrl) {
        this.goodsImgUrl = goodsImgUrl;
    }

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public float getGoodsTotalValue() {
        return goodsTotalValue;
    }

    public void setGoodsTotalValue(float goodsTotalValue) {
        this.goodsTotalValue = goodsTotalValue;
    }
}
