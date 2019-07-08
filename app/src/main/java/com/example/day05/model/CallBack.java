package com.example.day05.model;

import com.example.day05.bean.FuliBean;

import java.util.List;

public interface CallBack {
    void updateSucces(List<FuliBean.ResultsBean>resultsBeans);
    void updateError(String error);
}
