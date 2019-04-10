package com.example.lab5_pa.MVP;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


import com.example.lab5_pa.AUTO.Automobil;
import com.example.lab5_pa.AUTO.CustomVehicle;
import com.example.lab5_pa.AUTO.Moto;
import com.example.lab5_pa.AUTO.Truck;
import com.example.lab5_pa.AUTO.Vehicle;
import com.example.lab5_pa.R;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class MainRepository implements MainContract.MainRepository
{

    public MainRepository()
    {
        Realm realm = Realm.getDefaultInstance();
        if (realm.where(CustomVehicle.class).count() == 0)
            fillData();
    }

     private void fillData()
    {
        Realm realm=Realm.getDefaultInstance();
        realm.beginTransaction();

        for (int i = 0; i < 10; i++)
        {
            CustomVehicle v=realm.createObject(CustomVehicle.class, UUID.randomUUID().toString());
            if (i % 3 == 0)
            {
                v.setEngine_capacity(15.2f);
                v.setFuel_level(7);
                v.setWheel_number(10);
                v.setFuel_tank(100);
                v.setPhoto_res_id(R.mipmap.ic_truck);
            }
            else if (i % 3 == 1)
            {
                v.setEngine_capacity(0.5f);
                v.setFuel_level(5);
                v.setWheel_number(2);
                v.setFuel_tank(10);
                v.setPhoto_res_id(R.mipmap.ic_moto_);
            }
            else if (i % 3 == 2)
            {
                v.setEngine_capacity(4.2f);
                v.setFuel_level(30);
                v.setWheel_number(4);
                v.setFuel_tank(60);
                v.setPhoto_res_id(R.mipmap.ic_caar);
            }
        }

        realm.commitTransaction();



    }

    @Override
    public List<CustomVehicle> sort(MainContract.SortBy sortBy, boolean reversed) {
        String field_to_sort = "";
        switch(sortBy)
        {
            case FUEL_TANK:
                field_to_sort = "fuel_tank";

                break;
            case ENGINE_CAPACITY:
                field_to_sort = "engine_capacity";

                break;
            case FUEL_LEVEL:
                field_to_sort = "fuel_level";

                break;
            case WHEEL_NUMBER:
                field_to_sort = "wheel_number";

                break;
        }


        Realm realm = Realm.getDefaultInstance();
        RealmResults<CustomVehicle> cvs = realm.where(CustomVehicle.class).findAll();
        if (field_to_sort != "")
            cvs = cvs.sort(field_to_sort, reversed? Sort.DESCENDING : Sort.ASCENDING);
        return new ArrayList<CustomVehicle>(Arrays.asList(cvs.toArray(new CustomVehicle[0])));
    }

    @Override
    public void remove(CustomVehicle v)
    {
        Realm realm = Realm.getDefaultInstance();
        /*realm.beginTransaction();
        realm.where(CustomVehicle.class).equalTo("id", v.getId()).findFirst().deleteFromRealm();
        realm.commitTransaction();*/
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.where(CustomVehicle.class)
                        .equalTo("id", v.getId())
                        .findFirst().deleteFromRealm();
            }
        });
    }

    @Override
    public CustomVehicle add(int fuel_tank, int fuel_level, int wheel_number, int photo_res_id, float engine_capacity)
    {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CustomVehicle newCV = realm.createObject(CustomVehicle.class, UUID.randomUUID().toString());
        newCV.setEngine_capacity(engine_capacity);
        newCV.setFuel_level(fuel_level);
        newCV.setWheel_number(wheel_number);
        newCV.setFuel_tank(fuel_tank);
        newCV.setPhoto_res_id(photo_res_id);
        realm.commitTransaction();
        return newCV;
    }

    @Override
    public void reload() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(CustomVehicle.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
        fillData();
    }


    @Override
    final public List<CustomVehicle> getVehicles()
    {
        return  sort(MainContract.SortBy.NOTHING, false);
    }
}
