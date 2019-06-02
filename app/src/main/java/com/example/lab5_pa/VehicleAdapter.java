package com.example.lab5_pa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab5_pa.AUTO.IVehicle;
import com.example.lab5_pa.MVP.VehicleContract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder> implements VehicleContract.VehicleView {
    private ArrayList<IVehicle> vehicles = new ArrayList<>();

    @Override
    public void ShowVehicles(Collection<IVehicle> vehicles) {
        this.vehicles = new ArrayList<>(vehicles);
        notifyDataSetChanged();
    }

    @Override
    public void Remove(IVehicle vehicle) {
        int i = vehicles.indexOf(vehicle);
        vehicles.remove(i);
        notifyItemRemoved(i);
    }

    @Override
    public void Add(IVehicle vehicle) {
        vehicles.add(0, vehicle);
        notifyItemInserted(0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView engine_capacity, wheel_number, fuel_tank, fuel_level;
        public ImageView picture;
        public Button delete;

        public MyViewHolder(View view) {
            super(view);
            engine_capacity = view.findViewById(R.id.engine_capacity);
            wheel_number = view.findViewById(R.id.wheel_number);
            fuel_tank = view.findViewById(R.id.fuel_tank);
            fuel_level = view.findViewById(R.id.fuel_level);
            picture = view.findViewById(R.id.picture);
            delete = view.findViewById(R.id.delete);
        }
    }


    private VehicleContract.VehiclePresenter presenter;
    public VehicleAdapter(VehicleContract.VehiclePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        IVehicle vehicle = vehicles.get(position);
        holder.fuel_level.setText(Integer.toString(vehicle.getFuelLevel()));
        holder.engine_capacity.setText(Float.toString(vehicle.getEngineCapacity()));
        holder.fuel_tank.setText(Integer.toString(vehicle.getFuelTank()));
        holder.wheel_number.setText(Integer.toString(vehicle.getWheelNumber()));
        holder.picture.setImageResource(vehicle.getPhotoResId());
        holder.delete.setEnabled(true);

        holder.delete.setOnClickListener(v -> {
//            int aPosition = holder.getAdapterPosition();
            presenter.OnRemove(vehicle);
            holder.delete.setEnabled(false);
        });

    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }
}
