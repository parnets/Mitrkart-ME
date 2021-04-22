package com.Mitrakarte_ME.meappmintrakarte.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class AG {
    //    private Tables addtable;
    private User user;
    private Tables tables;
    private Address address;


      //  private Products products;
    private Category category;
//    private Address address;
//    private Products addproducts;
    private static AG omInst = null;

    public ArrayList<Category>newproductlist;
    public ArrayList<Category>mobileProductlist;
    public ArrayList<Category> categoryList;
    public ArrayList<Category>AllProductlistnew;
    private ArrayList<Category>categoryListall;
    public ArrayList<Category>fetchcatproductlist;
    private ArrayList<Category>viewcartlist;
    public ArrayList<Tables>orderHistorylist;
    public ArrayList<Tables>orderHistoryProductlist;
    private Tables addtable;

    private ArrayList<Address>keyAddressList;



    //Todo: Corporate
    public ArrayList<Category>AllSubCategorylistCorporate;
    private ArrayList<Category> AllproductListCorporate;
    private ArrayList<Category> corporateViewCartlist;
    public ArrayList<Tables> CorporateOrderHistorylist;
    public ArrayList<Tables> CorporateOrderHistoryProductlist;


    //Todo: Employee
    private ArrayList<Category> EmployeeViewCartlist;
    private ArrayList<Address> EmployeeAddressList;
    public ArrayList<Tables>  EmployeeOrderHistorylist;
    public ArrayList<Tables> EmployeeOrderHistoryProductlist;

    public ArrayList<Tables> getEmployeeOrderHistoryProductlist() {
        return EmployeeOrderHistoryProductlist;
    }

    public void setEmployeeOrderHistoryProductlist(ArrayList<Tables> employeeOrderHistoryProductlist) {
        EmployeeOrderHistoryProductlist = employeeOrderHistoryProductlist;
    }

    public ArrayList<Tables> getEmployeeOrderHistorylist() {
        return EmployeeOrderHistorylist;
    }

    public void setEmployeeOrderHistorylist(ArrayList<Tables> employeeOrderHistorylist) {
        EmployeeOrderHistorylist = employeeOrderHistorylist;
    }

    public ArrayList<Address> getEmployeeAddressList() {
        return EmployeeAddressList;
    }

    public void setEmployeeAddressList(ArrayList<Address> employeeAddressList) {
        EmployeeAddressList = employeeAddressList;
    }

    public ArrayList<Category> getEmployeeViewCartlist() {
        return EmployeeViewCartlist;
    }

    public void setEmployeeViewCartlist(ArrayList<Category> employeeViewCartlist) {
        EmployeeViewCartlist = employeeViewCartlist;
    }

    public ArrayList<Tables> getCorporateOrderHistoryProductlist() {
        return CorporateOrderHistoryProductlist;
    }

    public void setCorporateOrderHistoryProductlist(ArrayList<Tables> corporateOrderHistoryProductlist) {
        CorporateOrderHistoryProductlist = corporateOrderHistoryProductlist;
    }

    public ArrayList<Tables> getCorporateOrderHistorylist() {
        return CorporateOrderHistorylist;
    }

    public void setCorporateOrderHistorylist(ArrayList<Tables> corporateOrderHistorylist) {
        CorporateOrderHistorylist = corporateOrderHistorylist;
    }

    public ArrayList<Tables> getOrderHistoryProductlist() {
        return orderHistoryProductlist;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setOrderHistoryProductlist(ArrayList<Tables> orderHistoryProductlist) {
        this.orderHistoryProductlist = orderHistoryProductlist;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }

    public ArrayList<Address> getKeyAddressList() {
        return keyAddressList;
    }

    public void setKeyAddressList(ArrayList<Address> keyAddressList) {
        this.keyAddressList = keyAddressList;
    }

    public Tables getAddtable() {
        return addtable;
    }

    public void setAddtable(Tables addtable) {
        this.addtable = addtable;
    }

    public ArrayList<Tables> getOrderHistorylist() {
        return orderHistorylist;
    }

    public void setOrderHistorylist(ArrayList<Tables> orderHistorylist) {
        this.orderHistorylist = orderHistorylist;
    }

    public ArrayList<Category> getMobileProductlist() {
        return mobileProductlist;
    }

    public void setMobileProductlist(ArrayList<Category> mobileProductlist) {
        this.mobileProductlist = mobileProductlist;
    }

    public ArrayList<Category> getCorporateViewCartlist() {
        return corporateViewCartlist;
    }

    public void setCorporateViewCartlist(ArrayList<Category> corporateViewCartlist) {
        this.corporateViewCartlist = corporateViewCartlist;
    }

    public ArrayList<Category> getAllproductListCorporate() {
        return AllproductListCorporate;
    }

    public void setAllproductListCorporate(ArrayList<Category> allproductListCorporate) {
        AllproductListCorporate = allproductListCorporate;
    }

    public ArrayList<Category> getAllSubCategorylistCorporate() {
        return AllSubCategorylistCorporate;
    }

    public void setAllSubCategorylistCorporate(ArrayList<Category> allSubCategorylistCorporate) {
        AllSubCategorylistCorporate = allSubCategorylistCorporate;
    }

    public ArrayList<Category> getViewcartlist() {
        return viewcartlist;
    }

    public void setViewcartlist(ArrayList<Category> viewcartlist) {
        this.viewcartlist = viewcartlist;
    }

    public ArrayList<Category> getFetchcatproductlist() {
        return fetchcatproductlist;
    }

    public void setFetchcatproductlist(ArrayList<Category> fetchcatproductlist) {
        this.fetchcatproductlist = fetchcatproductlist;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private HashMap<Integer, ArrayList<Category>> subcatMapping;

    public HashMap<Integer, ArrayList<Category>> getSubcatMapping() {
        return subcatMapping;
    }

    public void setSubcatMapping(HashMap<Integer, ArrayList<Category>> subcatMapping) {
        this.subcatMapping = subcatMapping;
    }

    private ArrayList<Category>allproductlist;

    public ArrayList<Category> getAllproductlist() {
        return allproductlist;
    }

    public void setAllproductlist(ArrayList<Category> allproductlist) {
        this.allproductlist = allproductlist;
    }

    public ArrayList<Category> getCategoryListall() {
        return categoryListall;
    }

    public void setCategoryListall(ArrayList<Category> categoryListall) {
        this.categoryListall = categoryListall;
    }

    private ArrayList<Category> productList2;



    public ArrayList<Category> getProductList2() {
        return productList2;
    }

    public void setProductList2(ArrayList<Category> productList2) {
        this.productList2 = productList2;
    }

    public static AG getOmInst() {
        return omInst;
    }

    public static void setOmInst(AG omInst) {
        AG.omInst = omInst;
    }


    //    private ArrayList<Plan>planArrayList;
    public Integer cartqunatity = 0;
    private Boolean isInitDone;

    public static AG getInstance() {
        if (omInst == null) {
            omInst = new AG();
        }

        return omInst;
    }

    public Integer getCartqunatity() {
        return cartqunatity;
    }

    public void setCartqunatity(Integer cartqunatity) {
        this.cartqunatity = cartqunatity;
    }

    public ArrayList<Category> getAllProductlistnew() {
        return AllProductlistnew;
    }

    public void setAllProductlistnew(ArrayList<Category> allProductlistnew) {
        AllProductlistnew = allProductlistnew;
    }

    public ArrayList<Category> getNewproductlist() {
        return newproductlist;
    }

    public void setNewproductlist(ArrayList<Category> newproductlist) {
        this.newproductlist = newproductlist;
    }

    private AG() {
        user = new User();
        categoryList = new ArrayList<Category>();
        category = new Category();
        allproductlist = new ArrayList<Category>();
        AllProductlistnew = new ArrayList<Category>();
        productList2 = new ArrayList<Category>();
        orderHistorylist = new ArrayList<Tables>();
        //todo: key acconut
        keyAddressList  = new ArrayList<Address>();
        orderHistoryProductlist = new ArrayList<Tables>();
        //todo:product all
        categoryListall = new ArrayList<Category>();
        newproductlist = new ArrayList<Category>();
        fetchcatproductlist = new ArrayList<Category>();
        viewcartlist = new ArrayList<Category>();
        mobileProductlist = new ArrayList<Category>();
        //Todo: Corporate
        AllSubCategorylistCorporate = new ArrayList<Category>();
        AllproductListCorporate = new ArrayList<Category>();
        corporateViewCartlist = new ArrayList<Category>();
        CorporateOrderHistorylist = new ArrayList<Tables>();
        CorporateOrderHistoryProductlist = new ArrayList<Tables>();



        //Todo: Employee
        EmployeeViewCartlist = new ArrayList<Category>();
        EmployeeAddressList  = new ArrayList<Address>();
        EmployeeOrderHistorylist = new ArrayList<Tables>();
        EmployeeOrderHistoryProductlist = new ArrayList<Tables>();







    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Boolean getInitDone() {
        return isInitDone;
    }

    public void setInitDone(Boolean initDone) {
        isInitDone = initDone;
    }


//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public ArrayList<Category> getToprateproductlist() {
//        return toprateproductlist;
//    }
//
//    public void setToprateproductlist(ArrayList<Category> toprateproductlist) {
//        this.toprateproductlist = toprateproductlist;
//    }
//
//    public ArrayList<Products> getCartItems() {
//        return cartItems;
//    }
//
//    public void setCartItems(ArrayList<Products> cartItems) {
//        this.cartItems = cartItems;
//    }
//
//    public void setOfferList(ArrayList<Category> offerList) {
//        this.offerList = offerList;
//    }
//
//    public void setSliderList(ArrayList<Category> sliderList) {
//        this.sliderList = sliderList;
//    }
//
//    public ArrayList<Category> getNewproductlist() {
//        return newproductlist;
//    }
//
//    public ArrayList<Category> getBestproductlist() {
//        return bestproductlist;
//    }
//
//    public void setBestproductlist(ArrayList<Category> bestproductlist) {
//        this.bestproductlist = bestproductlist;
//    }
//
//    public void setNewproductlist(ArrayList<Category> newproductlist) {
//        this.newproductlist = newproductlist;
//    }
//
//    public ArrayList<Products> getmProductList() {
//        return mProductList;
//    }
//
//    public void setmProductList(ArrayList<Products> mProductList) {
//        this.mProductList = mProductList;
//    }
//
//    public Products getProducts() {
//        return products;
//    }
//
//    public void setProducts(Products products) {
//        this.products = products;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public ArrayList<Address> getPincodelist() {
//        return pincodelist;
//    }
//
//    public void setPincodelist(ArrayList<Address> pincodelist) {
//        this.pincodelist = pincodelist;
//    }

//    public Integer getCartqunatity() {
//        return cartqunatity;
//    }
//
//    public void setCartqunatity(Integer cartqunatity) {
//        this.cartqunatity = cartqunatity;
//    }

//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    public ArrayList<Address> getStorelist() {
//        return storelist;
//    }
//
//    public ArrayList<Category> getFiltercatlist() {
//        return filtercatlist;
//    }
//
//    public Tables getAddtable() {
//        return addtable;
//    }
//
//    public void setAddtable(Tables addtable) {
//        this.addtable = addtable;
//    }
//
//    public void setFiltercatlist(ArrayList<Category> filtercatlist) {
//        this.filtercatlist = filtercatlist;
//    }
//
//    public void setStorelist(ArrayList<Address> storelist) {
//        this.storelist = storelist;
//    }

//    void dummyData() {
//
//        ArrayList<Category> listcoffe = new ArrayList<Category>();
//        for (int i = 0; i < 15; i++) {
//            Category prd = new Category();
//            prd.setCatImage("http://parnetslink.com/mitrakarte_api/images/product/bookmybasket050320061324.png");
//            prd.setCatName("Vegetables");
//            listcoffe.add(prd);
//            categoryList.add(prd);
//        }
//
//
//
//        ArrayList<Address> address = new ArrayList<Address>();
//        for (int i = 0; i < 3; i++) {
//            Address prd = new Address();
//            prd.setCity("Bangalore");
//            prd.setAddress("Vidyaranyapura");
//            prd.setCountry("India");
//            prd.setLandmark("Vidyaranyapura");
//            prd.setPincode("567123");
//            address.add(prd);
//            addresslist.add(prd);
//        }
//
//    }

}
