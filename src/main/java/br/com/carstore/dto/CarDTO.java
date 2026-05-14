package br.com.carstore.dto;

public class CarDTO {

    private String id;
    private String name;
    private String color;
    private String brand;

    public CarDTO(String name, String color, String brand, String model, String yearFabric, String yearModel) {
        this.name = name;
        this.color = color;
        this.brand = brand;
    }

    public CarDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}