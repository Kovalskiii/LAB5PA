package com.example.lab5_pa.AUTO;

import com.example.lab5_pa.R;

public class VehicleType implements Vehicle
{



    @Override
    public String getEngineType()
    {
        return " ";
    }

    @Override
    public int getFuelTank()
    {
        return 0;
    }

    @Override
    public int getFuelLevel()
    {
        return 0;
    }

    @Override
    public float getEngineCapacity()
    {
        return (float) 0.0;
    }

    @Override
    public int getWheelNumber()
    {
        return 0;
    }

    @Override
    public int getPhotoResId() {
        return R.drawable.ic_launcher_foreground;
    }

    @Override
    public String toString()
    {
        return getEngineType() + " " + getEngineCapacity() + " " + getFuelLevel() + " " + getFuelTank() + " " + getWheelNumber();
    }

}
