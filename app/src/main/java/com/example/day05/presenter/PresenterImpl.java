package com.example.day05.presenter;

import com.example.day05.bean.FuliBean;
import com.example.day05.model.CallBack;
import com.example.day05.model.ModelImpl;
import com.example.day05.view.IView;

import java.util.List;

public class PresenterImpl implements IPresenter {
    private IView view;
    private final ModelImpl model;

    public PresenterImpl(IView view) {
        this.view=view;
        model = new ModelImpl();
    }

    @Override
    public void getFuLi() {
        model.getFuLI(new CallBack() {
            @Override
            public void updateSucces(List<FuliBean.ResultsBean> resultsBeans) {
                view.updateUIList(resultsBeans);
            }

            @Override
            public void updateError(String error) {
                view.updateError(error);
            }
        });
    }
}
