package com.example.day05;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day05.adapters.HomeAdapter;
import com.example.day05.bean.FuliBean;
import com.example.day05.presenter.PresenterImpl;
import com.example.day05.view.IView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IView{
    private View view;
    private RecyclerView mMyRecycler;
    private HomeAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, null);
        initView(view);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&homeAdapter!=null){
            PresenterImpl presenter = new PresenterImpl((IView) this);
            presenter.getFuLi();
        }
    }


    private void initView(View view) {
        mMyRecycler = (RecyclerView) view.findViewById(R.id.myRecycler);
        mMyRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        homeAdapter = new HomeAdapter(getActivity());
        mMyRecycler.setAdapter(homeAdapter);
    }

    private static final String TAG = "HomeFragment";
    @Override
    public void updateUIList(List<FuliBean.ResultsBean> resultsBeans) {
        ArrayList<Boolean> booleans = new ArrayList<>();
        for (int i = 0; i < resultsBeans.size(); i++) {
            booleans.add(false);
        }
        homeAdapter.initData(resultsBeans);
        homeAdapter.initBoolean(booleans);
    }

    @Override
    public void updateError(String error) {
        Log.d(TAG, "updateError: "+error);
    }
}
