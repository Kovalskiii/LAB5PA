package com.example.lab5_pa.MVP;


import com.example.lab5_pa.AUTO.CustomVehicle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainPresenter implements MainContract.MainPresenter
{
    private MainContract.MainView mView;
    private MainContract.MainRepository mRepository;
    private Map<MainContract.SortBy, Boolean> mSortStatus = new HashMap<MainContract.SortBy, Boolean>();
    private List<CustomVehicle> list;

    public MainPresenter(MainContract.MainView view)
    {
        this.mView = view;
        this.mRepository = new MainRepository();
        this.list = mRepository.getVehicles();
    }

    @Override
    public List<CustomVehicle> getList() {
        return list;
    }

    @Override
    public void OnSortClicked(MainContract.SortBy sortBy)
    {
        Boolean clicked = mSortStatus.get(sortBy);
        boolean reversed = false;
        if (clicked == null || clicked == false)
        {
            mSortStatus.put(sortBy, true);
            reversed = false;

        } else {
            mSortStatus.put(sortBy, false);
            reversed = true;

        }
        list.clear(); list.addAll(mRepository.sort(sortBy, reversed));
        mView.toast(String.format("Sorted by %s %s", sortBy, reversed?"reversed":""));
    }

    @Override
    public void OnRemove(CustomVehicle v) {
        this.mRepository.remove(v);
    }

    @Override
    public void OnAdd(int fuel_tank, int fuel_level, int wheel_number, int photo_res_id, float engine_capacity) {
        final CustomVehicle vc = this.mRepository.add(fuel_tank, fuel_level, wheel_number, photo_res_id, engine_capacity);
        list.add(0, vc);
    }

    @Override
    public void OnReload() {
        mRepository.reload();
        list.clear(); list.addAll(mRepository.getVehicles());
        mView.toast("Reloaded...");
    }
}


