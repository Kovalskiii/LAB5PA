package com.example.lab5_pa.AUTO;

import com.example.lab5_pa.R;

public class Automobil extends VehicleType
    {
        

        @Override
        public String getEngineType()
        {
            return "petrol";
        }

        @Override
        public int getFuelTank()
        {
            return 60;
        }

        @Override
        public int getFuelLevel()
        {
            return 30;
        }

        @Override
        public float getEngineCapacity()
        {
            return (float) 4.2;
        }

        @Override
        public int getWheelNumber()
        {
            return 4;
        }

        @Override
        public int getPhotoResId() {
            return R.mipmap.ic_caar;
        }
    }


