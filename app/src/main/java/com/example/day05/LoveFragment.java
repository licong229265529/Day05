package com.example.day05;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.day05.adapters.LoveAdapter;
import com.example.day05.bean.TitleBean;

import java.util.List;

public class LoveFragment extends Fragment {
    private View view;
    private RecyclerView mMyRecycler;
    private LoveAdapter loveAdapter;

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
        if (isVisibleToUser&&loveAdapter!=null){
            List<TitleBean> titleBeans = SqlUtils.queryAll();
            loveAdapter.initData(titleBeans);
        }
    }

    private void initView(View view) {
        mMyRecycler = (RecyclerView) view.findViewById(R.id.myRecycler);
        mMyRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        loveAdapter = new LoveAdapter(getActivity());
        mMyRecycler.setAdapter(loveAdapter);
        loveAdapter.setOn(new LoveAdapter.onItem() {
            @Override
            public void onItem(TitleBean resultsBean) {
                List<TitleBean> resultsBeans = loveAdapter.getResultsBeans();
                List<TitleBean> titleBeans = SqlUtils.queryItem(resultsBean.get_id());
                if (titleBeans.size()>0) {
                    SqlUtils.delete(resultsBean);
                    resultsBeans.remove(resultsBean);
                }
                loveAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "取消收藏", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
