package com.example.login;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.login.PhongBan.HanhChinhFragment;
import com.example.login.PhongBan.KeToanTaiChinhFragment;
import com.example.login.PhongBan.KinhDoanhFragment;
import com.example.login.PhongBan.KyThuatFragment;
import com.example.login.PhongBan.MarketingFragment;
import com.example.login.PhongBan.ThietKeFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhongBanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhongBanFragment extends Fragment {

    Button btnHanhChinh, btnKeToan, btnKinhDoanh, btnKyThuat, btnThietKe, btnMarketing;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhongBanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhongBanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhongBanFragment newInstance(String param1, String param2) {
        PhongBanFragment fragment = new PhongBanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phong_ban, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnHanhChinh = view.findViewById(R.id.btn_PbHanhChinh);
        btnKeToan = view.findViewById(R.id.btn_PbKeToanTaiChinh);
        btnKinhDoanh = view.findViewById(R.id.btn_PbKinhDoanh);
        btnKyThuat = view.findViewById(R.id.btn_PbKyThuat);
        btnThietKe = view.findViewById(R.id.btn_PbThietKe);
        btnMarketing = view.findViewById(R.id.btn_PbMarketing);
        btnHanhChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.content, new HanhChinhFragment());
                ft.addToBackStack(" ");
                ft.commit();
            }
        });
        btnKeToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.content, new KeToanTaiChinhFragment());
                ft.addToBackStack(" ");
                ft.commit();
            }
        });
        btnKinhDoanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.content, new KinhDoanhFragment());
                ft.addToBackStack(" ");
                ft.commit();
            }
        });
        btnKyThuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.content, new KyThuatFragment());
                ft.addToBackStack(" ");
                ft.commit();
            }
        });
        btnThietKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.content, new ThietKeFragment());
                ft.addToBackStack(" ");
                ft.commit();
            }
        });
        btnMarketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.content, new MarketingFragment());
                ft.addToBackStack(" ");
                ft.commit();
            }
        });
    }

}