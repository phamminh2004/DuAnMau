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
import fpoly.minhptph32719.duanmau.dao.LoaiSachDAO;
import fpoly.minhptph32719.duanmau.fragment.LoaiSachFragment;
import fpoly.minhptph32719.duanmau.fragment.SachFragment;
import fpoly.minhptph32719.duanmau.model.LoaiSach;
import fpoly.minhptph32719.duanmau.model.Sach;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    SachFragment fragment;
    private ArrayList<Sach> lists;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoaiSach;
    ImageButton btn_delete;

    public SachAdapter(@NonNull Context context, SachFragment fragment, ArrayList<Sach> lists) {
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
            view = inflater.inflate(R.layout.item_sach, null);
        }
        final Sach item = lists.get(position);
        if (item != null) {
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.maLoai));
            tvMaSach = view.findViewById(R.id.tvMaSach);
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvGiaThue = view.findViewById(R.id.tvGiaThue);
            tvLoaiSach = view.findViewById(R.id.tvLoaiSach);
            btn_delete = view.findViewById(R.id.btn_delete);
            tvMaSach.setText("Mã sách: " + item.maSach);
            tvTenSach.setText("Tên sách: " + item.tenSach);
            tvGiaThue.setText("Giá thuê: " + item.giaThue);
            tvLoaiSach.setText("Loại sách: " + loaiSach.tenLoai);
            btn_delete.setOnClickListener(v -> {
                fragment.xoa(String.valueOf(item.maSach));
            });
        }
        return view;
    }
}
