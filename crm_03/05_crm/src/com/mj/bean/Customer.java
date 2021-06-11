package com.mj.bean;

public class Customer {
    private Integer id;
    private String name;
    private Integer age;
    private Double height;

    public Customer() {
    }
    public Customer(Integer id, String name, Integer age, Double height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getHeight() {
        return height;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
