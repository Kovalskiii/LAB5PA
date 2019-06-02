package com.example.lab5_pa.AUTO;

import com.example.lab5_pa.R;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Vehicle extends  RealmObject implements IVehicle {
    public Vehicle()
    {}

    public static Vehicle Moto(){
        Vehicle v = new Vehicle();
        v.setEngineType("petrol");
        v.setFuelTank(10);
        v.setFuelLevel(5);
        v.setEngineCapacity(0.5f);
        v.setWheelNumber(2);
        v.setPhotoResId(R.mipmap.ic_moto_);
        v.setName("Moto");
        return v;

    }

    public static Vehicle Truck(){
        Vehicle v = new Vehicle();
        v.setEngineType("diesel");
        v.setFuelTank(100);
        v.setFuelLevel(7);
        v.setEngineCapacity(15.2f);
        v.setWheelNumber(10);
        v.setPhotoResId(R.mipmap.ic_truck);
        v.setName("Truck");
        return v;
    }

    public static Vehicle Automobil(){
        Vehicle v = new Vehicle();
        v.setEngineType("petrol");
        v.setFuelTank(60);
        v.setFuelLevel(30);
        v.setEngineCapacity(4.2f);
        v.setWheelNumber(4);
        v.setPhotoResId(R.mipmap.ic_caar);
        v.setName("Automobil");
        return v;
    }


    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String name;
    private int fuelTank, fuelLevel, wheelNumber, photoResID;
    private float engineCapacity;
    private String engineType = "petrol";


    @Override
    public String getId() { return  id; }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEngineType() {
        return engineType;
    }

    @Override
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public int getFuelTank() {
        return fuelTank;
    }

    @Override
    public void setFuelTank(int fuelTank) {
        this.fuelTank = fuelTank;
    }

    @Override
    public int getFuelLevel() {
        return fuelLevel;
    }

    @Override
    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    @Override
    public float getEngineCapacity() {
        return engineCapacity;
    }

    @Override
    public void setEngineCapacity(float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
    public int getWheelNumber() {
        return wheelNumber;
    }

    @Override
    public void setWheelNumber(int wheelNumber) {
        this.wheelNumber = wheelNumber;
    }

    @Override
    public int getPhotoResId() {
        return photoResID;
    }

    @Override
    public void setPhotoResId(int photoResId) {
        this.photoResID = photoResId;
    }
}
