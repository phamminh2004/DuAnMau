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
import fpoly.minhptph32719.duanmau.model.ThanhVien;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> lists;
    TextView tv_maTV, tv_tenTV;

    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> lists) {
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
            view = inflater.inflate(R.layout.item_sp_thanh_vien, null);
        }
        final ThanhVien item = lists.get(position);
        if (item != null) {
            tv_maTV = view.findViewById(R.id.tv_maTV);
            tv_tenTV = view.findViewById(R.id.tv_tenTV);
            tv_maTV.setText(item.maTV + ".");
            tv_tenTV.setText(item.hoTen);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            view = inflater.inflate(R.layout.item_sp_thanh_vien, null);
        }
        final ThanhVien item = lists.get(position);
        if (item != null) {
            tv_maTV = view.findViewById(R.id.tv_maTV);
            tv_tenTV = view.findViewById(R.id.tv_tenTV);
            tv_maTV.setText(item.maTV + ".");
            tv_tenTV.setText(item.hoTen);
        }
        return view;
    }
}
