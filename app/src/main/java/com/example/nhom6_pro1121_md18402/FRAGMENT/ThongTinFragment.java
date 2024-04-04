package com.example.nhom6_pro1121_md18402.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nhom6_pro1121_md18402.DAO.ThongTinNguoiDungDAO;
import com.example.nhom6_pro1121_md18402.R;


public class ThongTinFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_tin, container, false);
    }
}