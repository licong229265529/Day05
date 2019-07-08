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

public class LoveAdapter extends RecyclerView.Adapter<LoveAdapter.ViewHolder> {
    Context context;

    public LoveAdapter(Context context) {
        this.context = context;
    }
    List<TitleBean>resultsBeans=new ArrayList<>();

    public List<TitleBean> getResultsBeans() {
        return resultsBeans;
    }

    public void initData(List<TitleBean>resultsBeans){
        this.resultsBeans=resultsBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.recyc2_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final TitleBean resultsBean = resultsBeans.get(i);
        Glide.with(context).load(resultsBean.getUrl()).into(viewHolder.recyc_image2);
        viewHolder.recyc_title2.setText(resultsBean.get_id());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              on.onItem(resultsBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recyc_image2;
        TextView recyc_title2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyc_image2=itemView.findViewById(R.id.recyc_image2);
            recyc_title2=itemView.findViewById(R.id.recyc_title2);
        }
    }
    private onItem on;

    public void setOn(onItem on) {
        this.on = on;
    }

    public interface  onItem{
        void onItem(TitleBean resultsBean );
    }
}
