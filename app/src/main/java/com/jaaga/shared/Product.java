package com.jaaga.shared;


public class Product {

    private int id;
    private String name;
    private int icon;
    private String description;
    private String price;

    public Product() {
        super();
    }

    public Product(int id, String name, int icon /*String description*/, String price) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon=icon;
        this.price = price;
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
    public int getImage() {
        return icon;
    }
   /* public void setImage(int icon) {
        this.icon = icon;
    }*/


   /* public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name +
                  ", price=" + price + "]";
    }
}