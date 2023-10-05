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
import fpoly.minhptph32719.duanmau.fragment.LoaiSachFragment;
import fpoly.minhptph32719.duanmau.fragment.ThanhVienFragment;
import fpoly.minhptph32719.duanmau.model.LoaiSach;
import fpoly.minhptph32719.duanmau.model.ThanhVien;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> lists;
    TextView tv_maLoai, tv_tenLoai;
    ImageButton btn_delete;

    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> lists) {
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
            view = inflater.inflate(R.layout.item_loai_sach, null);
        }
        final LoaiSach item = lists.get(position);
        if (item != null) {
            tv_maLoai = view.findViewById(R.id.tv_maLoai);
            tv_tenLoai = view.findViewById(R.id.tv_tenLoai);
            btn_delete = view.findViewById(R.id.btn_delete);
            tv_maLoai.setText("Mã loại: " + item.maLoai);
            tv_tenLoai.setText("Tên loại: " + item.tenLoai);
            btn_delete.setOnClickListener(v -> {
                fragment.xoa(String.valueOf(item.maLoai));
            });
        }
        return view;
    }
}

