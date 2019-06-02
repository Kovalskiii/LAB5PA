package com.example.lab5_pa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.lab5_pa.AUTO.IVehicle;
import com.example.lab5_pa.MVP.VehicleContract;
import com.example.lab5_pa.MVP.VehicleRepository;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements VehicleContract.VehiclePresenter {






    //private VehicleContract.VehicleView view;

    //private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private Map<VehicleContract.SortBy, Boolean> sortStatus = new HashMap<VehicleContract.SortBy, Boolean>();
    private VehicleContract.VehicleRepository vehicleRepository;
    private VehicleContract.VehicleView vehicleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = (RecyclerView) findViewById(R.id.recycler);


        VehicleAdapter vehicleAdapter = new VehicleAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(vehicleAdapter);

        vehicleView = vehicleAdapter;
        vehicleRepository = new VehicleRepository(this);
        vehicleRepository.Load(loaded -> vehicleView.ShowVehicles(loaded));

    }

    private static final int request_code_add = 1;
    public void OnAdd(View view){
        Intent intent = new Intent(this, AddItemActivity.class);

        startActivityForResult(intent, request_code_add);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == request_code_add) {
            if (resultCode == RESULT_OK) {
                addNewItem(data);
            }
        }
    }

    private void addNewItem(Intent data) {
        Bundle extras = data.getExtras();
        int     wheel_number = extras.getInt(AddItemActivity.WHEEL_NUMBER),
                fuel_level = extras.getInt(AddItemActivity.FUEL_LEVEL),
                fuel_tank = extras.getInt(AddItemActivity.FUEL_TANK);
        float   engine_capacity = extras.getFloat(AddItemActivity.ENGINE_CAPACITY);
        AddItemActivity.VehicleType vt = (AddItemActivity.VehicleType)extras.get(AddItemActivity.VEHICLE_TYPE);

        int photoId = 0;

        switch(vt) {
            case Auto:
                photoId = R.mipmap.ic_caar;
                break;
            case Moto:
                photoId = R.mipmap.ic_moto_;
                break;
            case Truck:
                photoId = R.mipmap.ic_truck;
                break;
        }

//        CustomVehicle custom_vehicle = new CustomVehicle(fuel_tank, fuel_level, wheel_number, photoId, engine_capacity);
          OnAdd(fuel_tank, fuel_level, wheel_number, photoId, engine_capacity);

//        presenter.getList().add(0, custom_vehicle);


    }

    public void OnSortButton(View view) {
        VehicleContract.SortBy sortBy = VehicleContract.SortBy.NOTHING;
        switch (view.getId()) {
            case R.id.engine_sort:
                sortBy = VehicleContract.SortBy.ENGINE_CAPACITY;
                break;
            case R.id.fuel_level_sort:
                sortBy = VehicleContract.SortBy.FUEL_LEVEL;
                break;
            case R.id.fuel_tank_sort:
                sortBy = VehicleContract.SortBy.FUEL_TANK;
                break;
            case R.id.wheel_sort:
                sortBy = VehicleContract.SortBy.WHEEL_NUMBER;
                break;
        }
        OnSort(sortBy);
    }


    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void OnSort(VehicleContract.SortBy sortBy) {

        Boolean clicked = sortStatus.get(sortBy);
        boolean reversed = false;
        if (clicked == null || clicked == false)
        {
            sortStatus.put(sortBy, true);
            reversed = false;

        } else {
            sortStatus.put(sortBy, false);
            reversed = true;

        }
        toast(String.format("Sorted by %s %s", sortBy, reversed?"reversed":""));
        vehicleRepository.Sort(sortBy, reversed, sorted -> {
            vehicleView.ShowVehicles(sorted);
        });
    }

    @Override
    public void OnRemove(IVehicle vehicle) {
        vehicleRepository.Remove(vehicle, removedV -> {
            vehicleView.Remove(removedV);
        });
    }

    @Override
    public void OnAdd(int fuel_tank, int fuel_level, int wheel_number, int photo_res_id, float engine_capacity) {
        vehicleRepository.Add("addedVehicle", fuel_tank, fuel_level, wheel_number, photo_res_id, engine_capacity, added -> {
            vehicleView.Add(added);
        });
    }

    public void btnReload(View view)
    {
        OnReload();
    }

    @Override
    public void OnReload() {
        vehicleRepository.Reload(vehicles -> {
            vehicleView.ShowVehicles(vehicles);
        });
    }
}


