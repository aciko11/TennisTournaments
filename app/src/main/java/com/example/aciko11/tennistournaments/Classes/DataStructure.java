package com.example.aciko11.tennistournaments.Classes;

import java.io.Serializable;

public class DataStructure implements Serializable {
    private String[] name;
    private String[] value;
    private String jsonArrayName;
    private boolean isInsert;
    private int dataSize;

    public DataStructure(int size){
        this.name = new String[size];
        this.value = new String[size];
        this.dataSize = size;
    }

    public boolean getIsInsert() {
        return isInsert;
    }

    public void setIsInsert(boolean value) {
        isInsert = value;
    }

    public String getName(int i) {
        return name[i];
    }

    public void setName(String name, int i) {
        this.name[i] = name;
    }

    public String getValue(int i) {
        return value[i];
    }

    public void setValue(String value, int i) {
        this.value[i] = value;
    }

    public void setJsonArrayName(String value){
        this.jsonArrayName = value;
    }

    public String getJsonArrayName(){
        return this.jsonArrayName;
    }

    public int getDataSize(){
        return this.dataSize;
    }
}
