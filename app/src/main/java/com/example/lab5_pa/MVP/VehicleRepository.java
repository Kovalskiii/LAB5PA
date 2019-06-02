package com.example.lab5_pa.MVP;


import android.content.Context;
import android.support.annotation.MainThread;

import com.example.lab5_pa.AUTO.IVehicle;
import com.example.lab5_pa.AUTO.Vehicle;
import com.example.lab5_pa.MainActivity;

import java.util.ArrayList;
import java.util.Collection;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class VehicleRepository implements VehicleContract.VehicleRepository
{

    Realm _realm;

    public VehicleRepository(Context context)
    {
        Realm.init(context);
        _realm = Realm.getDefaultInstance();
    }

    @Override
     public void Reload(VehicleReloadCallback callback) {

        Collection<Vehicle> toInsert = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Vehicle toAdd = null;

            if (i % 3 == 0)
                toAdd = Vehicle.Truck();
            else if (i % 3 == 1)
                toAdd = Vehicle.Automobil();
            else if (i % 3 == 2)
                toAdd = Vehicle.Moto();

            toInsert.add(toAdd);
        }
        _realm.executeTransactionAsync(r ->{
            r.where(Vehicle.class).findAll().deleteAllFromRealm();
            r.insert(toInsert);
        }, () -> Load(laoded -> callback.OnReload(laoded)));
//        callback.OnReload(new ArrayList<>(toInsert));
    }

    @Override
    public void Add(String name, int fuelTank, int fuelLevel, int wheelNumber, int photoResID, float engineCapacity, VehicleAddCallBack addCallBack) {
//        IVehicle vehicle = new Vehicle();
        Vehicle v = new Vehicle();
        v.setName(name);
        v.setFuelTank(fuelTank);
        v.setFuelLevel(fuelLevel);
        v.setWheelNumber(wheelNumber);
        v.setPhotoResId(photoResID);
        v.setEngineCapacity(engineCapacity);

//        _realm.executeTransactionAsync(
//                realm -> {
//                    realm.insert((Vehicle)vehicle);
//                },
//                () -> {
//                    addCallBack.OnAdd(vehicle);
//                }
//        ); Doesnt work ffs
        _realm.executeTransactionAsync(
                r -> r.insert(v),
                () -> _realm.executeTransaction(r -> addCallBack.OnAdd(r.where(Vehicle.class).equalTo("id", v.getId()).findFirst())));
//        addCallBack.OnAdd(v);
    }

    @Override
    public void Remove(IVehicle vehicleToRemove, VehicleRemoveCallback removeCallback) {
//        RealmResults<Vehicle> result = _realm.where(Vehicle.class).equalTo("id", vehicleToRemove.getId()).findAllAsync();
//        result.addChangeListener(res -> {res.deleteFirstFromRealm(); removeCallback.OnRemove(vehicleToRemove);});
        final String idToRemove = vehicleToRemove.getId();
        _realm.executeTransaction(realm -> {
            Vehicle v = realm.where(Vehicle.class).equalTo("id", idToRemove).findFirst();
            removeCallback.OnRemove(v);
            v.deleteFromRealm();
        });
//                () -> removeCallback.OnRemove(idToRemove));
    }

    @Override
    public void Sort(VehicleContract.SortBy sortBy, boolean reversed, VehicleSortCallback sortCallback) {
        String field_to_sort = "";
        switch(sortBy)
        {
            case FUEL_TANK:
                field_to_sort = "fuelTank";

                break;
            case ENGINE_CAPACITY:
                field_to_sort = "engineCapacity";

                break;
            case FUEL_LEVEL:
                field_to_sort = "fuelLevel";

                break;
            case WHEEL_NUMBER:
                field_to_sort = "wheelNumber";
                break;
        }

        final String fieldToSort = field_to_sort;
//        Collection<IVehicle> sorted = new ArrayList<>();
        RealmResults<Vehicle> sorted = _realm.where(Vehicle.class).findAllAsync();
        sorted.addChangeListener((vehicles, changeSet) -> sortCallback.OnSort(new ArrayList<>(vehicles.sort(fieldToSort, reversed? Sort.DESCENDING : Sort.ASCENDING))));
    }

    @Override
    public void Load(VehicleLoadCallback loadCallback) {
//        Collection<Vehicle> vehicles = new ArrayList<>();
//        _realm.executeTransactionAsync(
//                realm1 -> {
//                    vehicles.addAll(realm1.where(Vehicle.class).findAll());
//                },
//                () -> {
//                    loadCallback.OnLoad(new ArrayList<IVehicle>(vehicles));
//                }
//        );
        RealmResults<Vehicle> result = _realm.where(Vehicle.class).findAllAsync();
        result.addChangeListener( vs -> loadCallback.OnLoad(new ArrayList<IVehicle>(vs)));
    }
}
