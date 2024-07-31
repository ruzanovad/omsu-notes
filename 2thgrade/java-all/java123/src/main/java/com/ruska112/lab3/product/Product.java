package com.ruska112.lab3.product;

public class Product {
    protected String title;
    protected String description;

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Product setTitle: title is null");
        }
        if ("".equals(title)) {
            throw new IllegalArgumentException("Product setTitle: title is empty");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Product setDescription: description is null");
        }
        if ("".equals(description)) {
            throw new IllegalArgumentException("Product setDescription: description is empty");
        }
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Product() {
        this.title = "Product";
        this.description = "Description";
    }

    public Product(Product product) {
        setTitle(product.getTitle());
        setDescription(product.getDescription());
    }

    public Product(String title, String description) {
        setTitle(title);
        setDescription(description);
    }

    public int hashCode() {
        int result = title.hashCode();
        result += description.hashCode();
        return result;
    }

    public boolean equals(Product product) {
        if (product == null) {
            return false;
        }
        return hashCode() == product.hashCode();
    }

    public String toString() {
        return String.format("Title: %s, Description: %s", title, description);
    }
}
