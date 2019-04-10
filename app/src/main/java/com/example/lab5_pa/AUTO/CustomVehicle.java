package com.example.lab5_pa.AUTO;

import com.example.lab5_pa.R;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CustomVehicle extends RealmObject implements Vehicle {

    public CustomVehicle(int fuel_tank, int fuel_level, int wheel_number, int photo_res_id, float engine_capacity) {
        this.fuel_tank = fuel_tank;
        this.fuel_level = fuel_level;
        this.wheel_number = wheel_number;
        this.photo_res_id = photo_res_id;
        this.engine_capacity = engine_capacity;
    }

    public CustomVehicle()
    {}

    public void setFuel_tank(int fuel_tank) {
        this.fuel_tank = fuel_tank;
    }

    public void setFuel_level(int fuel_level) {
        this.fuel_level = fuel_level;
    }

    public void setWheel_number(int wheel_number) {
        this.wheel_number = wheel_number;
    }

    public void setPhoto_res_id(int photo_res_id) {
        this.photo_res_id = photo_res_id;
    }

    public void setEngine_capacity(float engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private int fuel_tank, fuel_level, wheel_number, photo_res_id;
    private float engine_capacity;
    
    @Override
    public String getEngineType()
    {
        return "petrol";
    }

    @Override
    public int getFuelTank()
    {
        return fuel_tank;
    }

    @Override
    public int getFuelLevel()
    {
        return fuel_level;
    }

    @Override
    public float getEngineCapacity()
    {
        return engine_capacity;
    }

    @Override
    public int getWheelNumber()
    {
        return wheel_number;
    }

    @Override
    public int getPhotoResId() {
        return photo_res_id;
    }

    public String getId() { return  id; }
}
