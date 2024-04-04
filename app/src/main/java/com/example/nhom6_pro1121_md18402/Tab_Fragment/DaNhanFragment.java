package com.example.nhom6_pro1121_md18402.Tab_Fragment;

import static com.example.nhom6_pro1121_md18402.MainActivity.account_all;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhom6_pro1121_md18402.Adapter.OrderAdapter;
import com.example.nhom6_pro1121_md18402.DAO.DatHangDAO;
import com.example.nhom6_pro1121_md18402.MODEL.DatHang;
import com.example.nhom6_pro1121_md18402.R;

import java.util.List;


public class DaNhanFragment extends Fragment {

    View view;
    RecyclerView Order_list;
    List<DatHang> list;
    DatHangDAO datHangDAO;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_da_nhan, container, false);

        Order_list = view.findViewById(R.id.Order_list);
        datHangDAO = new DatHangDAO(getContext());

        getData();

        return view;
    }

    private void getData() {
        list = datHangDAO.getCartStatus(account_all.getId(),5,5);
        setList();
    }

    private void setList() {
        OrderAdapter adapter = new OrderAdapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Order_list.setLayoutManager(linearLayoutManager);
        Order_list.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume( );
        getData();
    }
}