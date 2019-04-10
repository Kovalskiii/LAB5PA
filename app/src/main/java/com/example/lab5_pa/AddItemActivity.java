package com.example.lab5_pa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
    }
    public static final String
        ENGINE_CAPACITY = "ENGINE_CAPACITY",
        FUEL_LEVEL = "FUEL_LEVEL",
        FUEL_TANK = "FUEL_TANK",
        WHEEL_NUMBER = "WHEEL_NUMBER",
        VEHICLE_TYPE = "VEHICLE_TYPE";

    public enum VehicleType {Truck, Auto, Moto}

    VehicleType getType(int radioButtonId){
        switch(radioButtonId){
            case R.id.car_rb:
                return VehicleType.Auto;
            case R.id.truck_rb:
                return VehicleType.Truck;
            case R.id.motorcycle_rb:
                return VehicleType.Moto;
        }
        return VehicleType.Auto;
    }
    public void OnAdd(View view){
        Intent intent = new Intent();

        EditText
                engine_capacity_edit = findViewById(R.id.engine_capacity_edit),
                fuel_level_edit = findViewById(R.id.fuel_level_edit),
                fuel_tank_edit = findViewById(R.id.fuel_tank_edit),
                wheel_number_edit = findViewById(R.id.wheel_number_edit);

        intent.putExtra(ENGINE_CAPACITY, Float.parseFloat(engine_capacity_edit.getText().toString()));
        intent.putExtra(FUEL_LEVEL, Integer.parseInt(fuel_level_edit.getText().toString()));
        intent.putExtra(FUEL_TANK, Integer.parseInt(fuel_tank_edit.getText().toString()));
        intent.putExtra(WHEEL_NUMBER, Integer.parseInt(wheel_number_edit.getText().toString()));
        intent.putExtra(VEHICLE_TYPE, getType(((RadioGroup)findViewById(R.id.vehicle_type_rg)).getCheckedRadioButtonId()));

        setResult(RESULT_OK, intent);
        finish();
    }
}
