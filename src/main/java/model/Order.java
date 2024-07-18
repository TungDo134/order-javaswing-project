package model;

public class Order {
    private String idCus, nameCus, idProduct, nameProduct, amount;

    public Order(String idCus, String nameCus, String idProduct, String nameProduct,String amount) {
        this.idCus = idCus;
        this.nameCus = nameCus;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.amount = amount;
    }

    public String getIdCus() {
        return idCus;
    }

    public void setIdCus(String idCus) {
        this.idCus = idCus;
    }

    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
