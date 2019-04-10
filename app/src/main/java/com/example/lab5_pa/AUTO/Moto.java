package com.example.lab5_pa.AUTO;

import com.example.lab5_pa.R;

public class Moto extends VehicleType
{
    @Override
    public String getEngineType()
    {
        return "petrol";
    }

    @Override
    public int getFuelTank()
    {
        return 10;
    }

    @Override
    public float getEngineCapacity()
    {
        return (float) 0.5;
    }

    @Override
    public int getFuelLevel()
    {
        return 5;
    }

    @Override
    public int getWheelNumber()
    {
        return 2;
    }

    @Override
    public int getPhotoResId() {
        return R.mipmap.ic_moto_;
    }
}
