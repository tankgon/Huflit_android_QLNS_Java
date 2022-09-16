package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Model.Thongbao;

import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationVH> {


    public interface OnNotificationItemClickListener {
        void onRestaurantItemClick(Thongbao thongbao);
    }

    private List<Thongbao> mThongbao;
    private OnNotificationItemClickListener mListener;

    public NotificationAdapter(List<Thongbao> thongbaos, OnNotificationItemClickListener listener, int i) {
        mThongbao =thongbaos;
        mListener = listener;
    }


    @NonNull
    @Override
    public NotificationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_noti, parent, false);
        return new NotificationVH(view);
    }

    class NotificationVH extends RecyclerView.ViewHolder {
        TextView txtTital, txtDes;
        public NotificationVH(@NonNull View itemView) {
            super(itemView);
            txtTital = itemView.findViewById(R.id.txtTieuDe);
            txtDes = itemView.findViewById(R.id.txtTitalDetalUpdate);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull NotificationVH holder, int position) {

        Thongbao thongbao = mThongbao.get(position);
        if (thongbao == null ){
            return;
        }
        holder.txtTital.setText(thongbao.getName());
        holder.txtDes.setText(thongbao.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRestaurantItemClick(thongbao);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mThongbao!=null){
            return mThongbao.size();
        }
        return 0;
    }

    public void addThongbao(List<Thongbao> thongbaos){
        mThongbao.addAll(thongbaos);
        notifyDataSetChanged();
    }


}
