package com.Mitrakarte_ME.meappmintrakarte.Model;

public class Category extends Products{


    private int catId;
    private String catName;
    private String catImage;
    private Integer sub_catId;
    private String sub_catName;
    private String sub_catImage;
    private int pro_id;
    private String price;
    private String offerimage;
    private String offerprize;
    private String offername;
    private String weight;

    //Todo: Corporate
    private String price_cor;
    private int pro_id_cor;


    //Todo: Employee
    private int pro_id_emp;
    private String price_emp;

    public String getPrice_emp() {
        return price_emp;
    }

    public void setPrice_emp(String price_emp) {
        this.price_emp = price_emp;
    }

    public int getPro_id_emp() {
        return pro_id_emp;
    }

    public void setPro_id_emp(int pro_id_emp) {
        this.pro_id_emp = pro_id_emp;
    }

    public int getPro_id_cor() {
        return pro_id_cor;
    }

    public void setPro_id_cor(int pro_id_cor) {
        this.pro_id_cor = pro_id_cor;
    }

    public String getPrice_cor() {
        return price_cor;
    }

    public void setPrice_cor(String price_cor) {
        this.price_cor = price_cor;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public Integer getSub_catId() {
        return sub_catId;
    }

    public void setSub_catId(Integer sub_catId) {
        this.sub_catId = sub_catId;
    }

    public String getSub_catName() {
        return sub_catName;
    }

    public void setSub_catName(String sub_catName) {
        this.sub_catName = sub_catName;
    }

    public String getSub_catImage() {
        return sub_catImage;
    }

    public void setSub_catImage(String sub_catImage) {
        this.sub_catImage = sub_catImage;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOfferimage() {
        return offerimage;
    }

    public void setOfferimage(String offerimage) {
        this.offerimage = offerimage;
    }

    public String getOfferprize() {
        return offerprize;
    }

    public void setOfferprize(String offerprize) {
        this.offerprize = offerprize;
    }

    public String getOffername() {
        return offername;
    }

    public void setOffername(String offername) {
        this.offername = offername;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
