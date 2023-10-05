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
import fpoly.minhptph32719.duanmau.model.Sach;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> lists;
    TextView tvTenSach, tvMaSach;

    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> lists) {
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
            view = inflater.inflate(R.layout.item_sp_sach, null);
        }
        final Sach item = lists.get(position);
        if (item != null) {
            tvMaSach = view.findViewById(R.id.tvMaSach);
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvMaSach.setText(item.maSach + ". ");
            tvTenSach.setText(item.tenSach);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            view = inflater.inflate(R.layout.item_sp_sach, null);
        }
        final Sach item = lists.get(position);
        if (item != null) {
            tvMaSach = view.findViewById(R.id.tvMaSach);
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvMaSach.setText(item.maSach + ". ");
            tvTenSach.setText(item.tenSach);
        }
        return view;
    }
}
