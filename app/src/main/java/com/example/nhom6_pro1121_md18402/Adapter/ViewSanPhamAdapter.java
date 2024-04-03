package com.example.nhom6_pro1121_md18402.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.nhom6_pro1121_md18402.Tab_Fragment.LoaiSanPhamFragment;
import com.example.nhom6_pro1121_md18402.Tab_Fragment.SanPhamFragment;

public class ViewSanPhamAdapter extends FragmentStateAdapter {
    public ViewSanPhamAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:{
                return new LoaiSanPhamFragment();
            }
            case 1:{
                return new SanPhamFragment();
            }

        }
        return new LoaiSanPhamFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
