/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.products;

/**
 *
 * @author ownhi
 */
public class ProductDTO {
    private String productID;
    private String name;
    private double price;
    private int quantity;
    private CategoryDTO category;
    private String img;

    public ProductDTO(String productID, String name, double price, int quantity,CategoryDTO category, String img) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.img = img;
    }
    public ProductDTO(){
        this.productID = "";
        this.name = "";
        this.price = 0;
        this.quantity = 0;
        this.category = new CategoryDTO();
        this.img = "";
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return productID + "_" + name + "_" + price + "_" + quantity + "_" + category + "_" + img;
    }
    

    
}
