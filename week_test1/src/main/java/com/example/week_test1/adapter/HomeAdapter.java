package com.example.week_test1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.week_test1.R;
import com.example.week_test1.TwoActivity;
import com.example.week_test1.entity.HomeEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.VH> {

    private Context context;
    private List<HomeEntity.RankingBean> list;

    public HomeAdapter(Context context, List<HomeEntity.RankingBean> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.home_item_layout,null);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {


        Glide.with(context).load(list.get(position).getAvatar())
                //10个像素的圆角
                .transform(new RoundedCorners(10))
                .into(holder.iv);

        holder.tv.setText(list.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, TwoActivity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder{

        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.name)
        TextView tv;


        public VH(@NonNull View itemView) {
            super(itemView);
            //
            ButterKnife.bind(this,itemView);
        }
    }
}
