package com.example.kanmeitu.adapter.response;

import java.util.List;

public class ListResponse<T> {
    private List<T> data;
    public List<T> getData(){
        return data;
    }
    public void setData(List<T>data){
        this.data=data;
    }
}
