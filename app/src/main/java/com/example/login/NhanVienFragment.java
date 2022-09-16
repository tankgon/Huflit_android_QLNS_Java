package com.example.login;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.Model.Nhanvien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NhanVienFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NhanVienFragment extends Fragment implements NhanVienAdapter.OnNhanvienItemClickListener{

    RecyclerView rvNhanVien;
    ArrayList<Nhanvien> nhanviens;
    NhanVienAdapter nhanVienAdapter;
    FirebaseDatabase fDatabase;
    DatabaseReference dThongbao;
    SearchView searchView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NhanVienFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NhanVienFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NhanVienFragment newInstance(String param1, String param2) {
        NhanVienFragment fragment = new NhanVienFragment();
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
        nhanviens = new ArrayList<>();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nhan_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvNhanVien = view.findViewById(R.id.rvNhanVien);
        nhanVienAdapter = new NhanVienAdapter(nhanviens, this);
        rvNhanVien.setAdapter(nhanVienAdapter);
//
//        rvNhanVien.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        rvNhanVien.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvNhanVien.setLayoutManager(layoutManager);
        rvNhanVien.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        rvNhanVien.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvNhanVien.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        rvNhanVien.setAdapter(nhanVienAdapter);

        fDatabase = FirebaseDatabase.getInstance();
        dThongbao = fDatabase.getReference();
        Query qNhanvien = dThongbao.child("nhanvien");
        qNhanvien.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nhanviens.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Nhanvien nhanvien = dataSnapshot.getValue(Nhanvien.class);
                    nhanviens.add(nhanvien);
                }
                nhanVienAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_news, menu);
        searchView = (SearchView) menu.findItem(R.id.mnuSearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                nhanVienAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                nhanVienAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuSearchNV){
            Intent intent = new Intent(getContext(), SearchNhanVienActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(getContext(), AddNhanVienActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onClick(NhanVien nhanVien) {
//        Intent intent = new Intent(getContext(), ThongTinNhanVienActivity.class);
//        intent.putExtra("nhanvien", nhanVien);
//        startActivity(intent);
//
//        Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
//    }



    @Override
    public void onNhanvienItemClickListener(Nhanvien nhanvien) {
        Intent intent = new Intent(getContext(), ThongTinNhanVienActivity.class);
        intent.putExtra("nhanvien", nhanvien);
        startActivity(intent);
    }
}