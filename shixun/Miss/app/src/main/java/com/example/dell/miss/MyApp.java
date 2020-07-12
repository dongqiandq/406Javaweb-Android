package com.example.dell.miss;

import android.app.Application;

public class MyApp extends Application {
    private Integer myLable;

    private Integer birthEdit;
    private Integer nameEdit;
    private Integer addressEdit;

    public Integer getBirthEdit() {
        return birthEdit;
    }

    public void setBirthEdit(Integer birthEdit) {
        this.birthEdit = birthEdit;
    }

    public Integer getNameEdit() {
        return nameEdit;
    }

    public void setNameEdit(Integer nameEdit) {
        this.nameEdit = nameEdit;
    }

    public Integer getAddressEdit() {
        return addressEdit;
    }

    public void setAddressEdit(Integer addressEdit) {
        this.addressEdit = addressEdit;
    }

    public Integer getMyLable() {
        return myLable;
    }

    public void setMyLable(Integer myLable) {
        this.myLable = myLable;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        setMyLable(0);
        setNameEdit(0);
        setAddressEdit(0);
        setBirthEdit(0);
    }
}
