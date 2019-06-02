package com.example.lab5_pa.AUTO;


public interface IVehicle

{
    String getId();

    String getName();
    void setName(String name);

    String getEngineType();
    void setEngineType(String engineType);

    int getFuelTank();
    void setFuelTank(int fuelTank);

    int getFuelLevel();
    void setFuelLevel(int fuelLevel);

    float getEngineCapacity();
    void setEngineCapacity(float engineCapacity);

    int getWheelNumber();
    void setWheelNumber(int wheelNumber);

    int getPhotoResId();
    void setPhotoResId(int photoResId);
}