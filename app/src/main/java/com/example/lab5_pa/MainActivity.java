package com.example.lab5_pa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.lab5_pa.AUTO.CustomVehicle;
import com.example.lab5_pa.MVP.MainContract;
import com.example.lab5_pa.MVP.MainPresenter;



import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {


    MainContract.MainPresenter presenter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);


        presenter = new MainPresenter(this);

        mAdapter = new VehicleAdapter(presenter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();


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

        presenter.OnAdd(fuel_tank, fuel_level, wheel_number, photoId, engine_capacity);
        mAdapter.notifyItemInserted(0);
//        presenter.getList().add(0, custom_vehicle);


    }

    public void OnSortButton(View view) {
        switch (view.getId()) {
            case R.id.engine_sort:
                presenter.OnSortClicked(MainContract.SortBy.ENGINE_CAPACITY);
                break;
            case R.id.fuel_level_sort:
                presenter.OnSortClicked(MainContract.SortBy.FUEL_LEVEL);
                break;
            case R.id.fuel_tank_sort:
                presenter.OnSortClicked(MainContract.SortBy.FUEL_TANK);
                break;
            case R.id.wheel_sort:
                presenter.OnSortClicked(MainContract.SortBy.WHEEL_NUMBER);
                break;
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showVehicles(List<CustomVehicle> vehicles) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void OnReload(View view) {
        presenter.OnReload();
        mAdapter.notifyDataSetChanged();
    }
}


