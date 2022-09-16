package com.example.login;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Model.Nhanvien;
import com.example.login.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienVH> implements Filterable {

    DatabaseReference reference;
    FirebaseUser firebaseUser;

    public interface OnNhanvienItemClickListener {
        void onNhanvienItemClickListener(Nhanvien nhanvien);
    }

    private List<Nhanvien> mNhanvien;
    private List<Nhanvien> mNhanVienOld;

    private OnNhanvienItemClickListener mListener;

    public NhanVienAdapter(List<Nhanvien> nhanviens, OnNhanvienItemClickListener listener) {
        mNhanvien = nhanviens;
        mNhanVienOld =nhanviens;
        mListener = listener;

    }

    @NonNull
    @Override
    public NhanVienVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhanvien, parent, false);
        NhanVienAdapter.NhanVienVH nhanVienVH = new NhanVienAdapter.NhanVienVH(view);
        return nhanVienVH;
    }



    @Override
    public void onBindViewHolder(@NonNull NhanVienVH holder, int position) {
        Nhanvien nhanvien = mNhanvien.get(position);

        if (nhanvien == null ){
            return;
        }

//        holder.imgAvata.setImageResource(nhanVien.Avata);
        holder.txtNameNV.setText(nhanvien.getName());
        holder.txtSDTNV.setText(nhanvien.getSdt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onNhanvienItemClickListener(nhanvien);
            }
        });

        holder.Btn_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgAvata.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialog_update_nv))
                        .setExpanded(true, 1200)
                        .create();


                View view = dialogPlus.getHolderView();

                EditText edtHoTen = view.findViewById(R.id.edtHoTen);
                EditText edtGioiTinh = view.findViewById(R.id.edtGioiTinh);
                EditText edtNgaySinh = view.findViewById(R.id.edtNgaySinh);
                EditText edtPhongBan = view.findViewById(R.id.edtPhongBanlNV);
                EditText edtChucVu = view.findViewById(R.id.edtChucVuNV);
                EditText edtEmail = view.findViewById(R.id.edtEmailNV);
                EditText edtSDT = view.findViewById(R.id.edtSDT);
                EditText edtDiaChi = view.findViewById(R.id.edtDiaChi);

                Button Btn_upDate = view.findViewById(R.id.btnUpdateNhanVien);

                edtHoTen.setText(nhanvien.getName());
//                edtGioiTinh.setText(nhanvien.getGioitinh());
                edtNgaySinh.setText(nhanvien.getNamsinh());

//                edtPhongBan.setText(nhanvien.getPhongban());
//                edtChucVu.setText(nhanvien.getChucvu());

                edtEmail.setText(nhanvien.getEmail());
                edtSDT.setText(nhanvien.getSdt());
//                edtDiaChi.setText(nhanvien.getDiachi());

                dialogPlus.show();

                Btn_upDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name",edtHoTen.getText().toString() );
                        map.put("name",edtNgaySinh.getText().toString() );
                        map.put("name",edtEmail.getText().toString() );
                        map.put("name",edtSDT.getText().toString() );




                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        reference = FirebaseDatabase.getInstance().getReference("nhanvien").child(firebaseUser.getUid());
                        reference.updateChildren((Map<String, Object>) nhanvien);


                    }
                });


            }

        });
    }



    @Override
    public int getItemCount() {
        if (mNhanvien!=null){
            return mNhanvien.size();
        }
        return 0;
    }

    class NhanVienVH extends RecyclerView.ViewHolder{
        TextView txtNameNV, txtSDTNV;
        ImageView imgAvata;
        Button Btn_Up;



        public NhanVienVH(@NonNull View itemView) {
            super(itemView);
            txtNameNV = itemView.findViewById(R.id.txt_NameNV);
            txtSDTNV = itemView.findViewById(R.id.txt_SDTNV);
            imgAvata = itemView.findViewById(R.id.img_AvataNhanVien);


            Btn_Up = (Button) itemView.findViewById(R.id.button2);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    mNhanvien = mNhanVienOld;
                }
                else {
                    List<Nhanvien> list = new ArrayList<>();
                    for (Nhanvien nhanvien : mNhanVienOld){
                        if(nhanvien.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(nhanvien);
                        }
                    }
                    mNhanvien = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mNhanvien;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mNhanvien =(List<Nhanvien>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
