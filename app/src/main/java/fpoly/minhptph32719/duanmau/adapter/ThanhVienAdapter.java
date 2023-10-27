package fpoly.minhptph32719.duanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.fragment.ThanhVienFragment;
import fpoly.minhptph32719.duanmau.model.ThanhVien;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> lists;
    TextView tvMaTV, tvTenTV, tvNamSinh, tvStk;
    ImageButton btn_delete;

    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            view = inflater.inflate(R.layout.item_thanh_vien, null);
        }
        final ThanhVien item = lists.get(position);
        if (item != null) {
            tvMaTV = view.findViewById(R.id.tvMaTV);
            tvTenTV = view.findViewById(R.id.tvTenTV);
            tvStk = view.findViewById(R.id.tvStk);
            tvNamSinh = view.findViewById(R.id.tvNamSinh);
            btn_delete = view.findViewById(R.id.btn_delete);
            tvMaTV.setText("Mã thành viên: " + item.maTV);
            tvTenTV.setText("Tên thành viên: " + item.hoTen);
            tvStk.setText("STK: "+item.stk);
            tvNamSinh.setText("Năm sinh: " + item.namSinh);
            btn_delete.setOnClickListener(v -> {
                fragment.xoa(String.valueOf(item.maTV));
            });
        }
        return view;
    }
}
