package com.example.lab5_pa.MVP;

import com.example.lab5_pa.AUTO.CustomVehicle;
import com.example.lab5_pa.AUTO.Vehicle;

import java.util.List;

public interface MainContract
{
    enum SortBy {FUEL_TANK, FUEL_LEVEL, ENGINE_CAPACITY, WHEEL_NUMBER, NOTHING}
     interface MainRepository
    {

        List<CustomVehicle> sort(SortBy sortBy, boolean reversed);
        void remove(CustomVehicle v);
        void reload();
        List<CustomVehicle> getVehicles();

        CustomVehicle add(int fuel_tank, int fuel_level, int wheel_number, int photo_res_id, float engine_capacity);
    }

    interface MainPresenter
    {
        List<CustomVehicle> getList();
        void OnSortClicked(SortBy sortBy);
        void OnRemove(CustomVehicle v);
        public void OnAdd(int fuel_tank, int fuel_level, int wheel_number, int photo_res_id, float engine_capacity);
        void OnReload();
    }

    interface MainView
    {
        void showVehicles(List<CustomVehicle> vehicles);
        void toast(String message);
    }
}
