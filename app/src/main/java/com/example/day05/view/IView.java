package com.example.day05.view;

import com.example.day05.bean.FuliBean;

import java.util.List;

public interface IView {
    void updateUIList(List<FuliBean.ResultsBean>resultsBeans);
    void updateError(String error);
}
