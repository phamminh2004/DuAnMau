package fpoly.minhptph32719.duanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.model.LoaiSach;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> lists;
    TextView tv_maLoai, tv_tenLoai;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            view = inflater.inflate(R.layout.item_sp_loai_sach, null);
        }
        final LoaiSach item = lists.get(position);
        if (item != null) {
            tv_maLoai = view.findViewById(R.id.tv_maLoai);
            tv_tenLoai = view.findViewById(R.id.tv_tenLoai);
            tv_maLoai.setText(item.maLoai + ". ");
            tv_tenLoai.setText(item.tenLoai);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            view = inflater.inflate(R.layout.item_sp_loai_sach, null);
        }
        final LoaiSach item = lists.get(position);
        if (item != null) {
            tv_maLoai = view.findViewById(R.id.tv_maLoai);
            tv_tenLoai = view.findViewById(R.id.tv_tenLoai);
            tv_maLoai.setText(item.maLoai + ". ");
            tv_tenLoai.setText(item.tenLoai);
        }
        return view;
    }

}

