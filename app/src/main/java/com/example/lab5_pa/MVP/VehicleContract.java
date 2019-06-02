package com.example.lab5_pa.MVP;

import com.example.lab5_pa.AUTO.IVehicle;

import java.util.Collection;

public interface VehicleContract
{
    enum SortBy {FUEL_TANK, FUEL_LEVEL, ENGINE_CAPACITY, WHEEL_NUMBER, NOTHING}
     interface VehicleRepository
    {
        interface VehicleAddCallBack
        {
            void OnAdd(IVehicle addedVehicle);
        }
        void Add(String name, int fuelTank, int fuelLevel, int wheelNumber, int photoResID, float engineCapacity, VehicleAddCallBack addCallBack);

        interface VehicleRemoveCallback
        {
            void OnRemove(IVehicle vehicle);
        }
        void Remove(IVehicle vehicleToRemove, VehicleRemoveCallback removeCallback);

        interface VehicleSortCallback
        {
            void OnSort(Collection<IVehicle> sortedVehicles);
        }
        void Sort(SortBy sortBy, boolean reversed, VehicleSortCallback sortCallback);

        interface VehicleLoadCallback
        {
            void OnLoad(Collection<IVehicle> loadedVehicles);
        }
        void Load(VehicleLoadCallback loadCallback);

        interface VehicleReloadCallback
        {
            void OnReload(Collection<IVehicle> vehicles);
        }
        void Reload(VehicleReloadCallback reloadCallback);
    }

    interface VehiclePresenter
    {
        void OnSort(SortBy sortBy);
        void OnRemove(IVehicle vehicle);
        void OnAdd(int fuel_tank, int fuel_level, int wheel_number, int photo_res_id, float engine_capacity);
        void OnReload();
    }

    interface VehicleView
    {
        void ShowVehicles(Collection<IVehicle> vehicles);
        void Remove(IVehicle vehicle);
        void Add(IVehicle vehicle);
    }
}
