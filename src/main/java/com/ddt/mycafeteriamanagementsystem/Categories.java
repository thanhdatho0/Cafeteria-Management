package com.ddt.mycafeteriamanagementsystem;

import java.util.List;

public class Categories {
    int id;
    private String typeName;

    private List<Product> productList;
    public Categories() {}

    public Categories(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public Categories(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString()
    {
        return typeName;
    }
}
