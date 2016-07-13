package com.example.maheshpujala.onlinestorefragment.model;


import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements  Serializable {
    private static final long serialVersionUID = -4073256626483275668L;
    private int pId;
    private String pQuantity;
    private String pProductName;
    private String pDescription;
    private BigDecimal pRate;
    private String pDelivery;
    private String pAvailSize;
    private String pProductImage;


    public Product() {
        super();
    }

    public Product(int pId, String pQuantity, String pProductName, String pDescription, BigDecimal pRate, String pDelivery, String pAvailSize, String pProductImage) {
        setId(pId);
        setQuantity(pQuantity);
        setName(pProductName);
        setPrice(pRate);
        setDescription(pDescription);
        setImageName(pProductImage);
        setAvailableSize(pAvailSize);
        setDelivery(pDelivery);

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Product)) return false;

        return (this.pId == ((Product) o).getId());
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + pId;
        hash = hash * prime + (pProductName == null ? 0 : pProductName.hashCode());
        hash = hash * prime + (pRate == null ? 0 : pRate.hashCode());
        hash = hash * prime + (pDescription == null ? 0 : pDescription.hashCode());

        hash = hash * prime + (pQuantity == null ? 0 : pQuantity.hashCode());
        hash = hash * prime + (pProductImage == null ? 0 : pProductImage.hashCode());
        hash = hash * prime + (pAvailSize == null ? 0 : pAvailSize.hashCode());
        hash = hash * prime + (pDelivery == null ? 0 : pDelivery.hashCode());

        return hash;
    }


    public int getId() {
        return pId;
    }

    public void setId(int id) {
        this.pId = id;
    }

    public void setQuantity(String quantity) {
        this.pQuantity = quantity;
    }
    public String getpQuantity() {
        return pQuantity;
    }


    public void setPrice(BigDecimal price) {
        this.pRate = price;
    }

    public void setName(String name) {
        this.pProductName = name;
    }

    public String getDescription() {
        return pDescription;
    }

    public void setDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getImageName() {
        return pProductImage;
    }

    public void setImageName(String imageName) {
        this.pProductImage = imageName;
    }

    public void setAvailableSize(String size) {
        this.pAvailSize = size;
    }
    public String getpAvailSize() {
        return pAvailSize;
    }

    public void setDelivery(String delivery) {
        this.pAvailSize = delivery;
    }
    public String getpDelivery() {
        return pDelivery;
    }
}
