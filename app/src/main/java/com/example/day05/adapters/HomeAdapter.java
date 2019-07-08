package com.example.day05.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.day05.R;
import com.example.day05.SqlUtils;
import com.example.day05.bean.FuliBean;
import com.example.day05.bean.TitleBean;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    List<FuliBean.ResultsBean> resultsBeans = new ArrayList<>();
    List<Boolean> ischeckeds = new ArrayList<>();

    public void initData(List<FuliBean.ResultsBean> resultsBeans) {
        this.resultsBeans = resultsBeans;
        notifyDataSetChanged();
    }

    public void initBoolean(List<Boolean> ischeckeds) {
        this.ischeckeds = ischeckeds;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.recyc_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final FuliBean.ResultsBean resultsBean = resultsBeans.get(i);
        Glide.with(context).load(resultsBean.getUrl()).into(viewHolder.recyc_image);
        viewHolder.recyc_title.setText(resultsBean.get_id());
        List<TitleBean> titleBeans = SqlUtils.queryItem(resultsBean.get_id());
        if (titleBeans.size() > 0) {
            ischeckeds.set(i, true);
        } else {
            ischeckeds.set(i, false);
        }
        viewHolder.cb.setChecked(ischeckeds.get(i));

        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean aBoolean = ischeckeds.get(i);
                if (aBoolean) {
                    ischeckeds.set(i, false);
                    List<TitleBean> titleBeans = SqlUtils.queryItem(resultsBean.get_id());
                    TitleBean titleBean = titleBeans.get(0);
                    SqlUtils.delete(titleBean);
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    ischeckeds.set(i, true);
                    //添加数据库
                    TitleBean titleBean = new TitleBean();
                    titleBean.set_id(resultsBean.get_id());
                    titleBean.setUrl(resultsBean.getUrl());
                    SqlUtils.initsert(titleBean);
                    Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recyc_image;
        TextView recyc_title;
        CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyc_image = itemView.findViewById(R.id.recyc_image);
            recyc_title = itemView.findViewById(R.id.recyc_title);
            cb = itemView.findViewById(R.id.cb);
        }
    }
}
