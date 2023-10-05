package fpoly.minhptph32719.duanmau.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.dao.SachDAO;
import fpoly.minhptph32719.duanmau.dao.ThanhVienDAO;
import fpoly.minhptph32719.duanmau.fragment.PhieuMuonFragment;
import fpoly.minhptph32719.duanmau.model.PhieuMuon;
import fpoly.minhptph32719.duanmau.model.Sach;
import fpoly.minhptph32719.duanmau.model.ThanhVien;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageButton btn_delete;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
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
            view = inflater.inflate(R.layout.item_phieu_muon, null);
        }
        final PhieuMuon item = lists.get(position);
        if (item != null) {
            tvMaPM = view.findViewById(R.id.tvMaPM);
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvTenTV = view.findViewById(R.id.tvTenTV);
            tvTienThue = view.findViewById(R.id.tvTienThue);
            tvNgay = view.findViewById(R.id.tvNgay);
            tvTraSach = view.findViewById(R.id.tvTraSach);
            btn_delete = view.findViewById(R.id.btn_delete);
            sachDAO = new SachDAO(context);
            thanhVienDAO = new ThanhVienDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.maSach));
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.maTV));
            tvMaPM.setText("Mã phiếu: " + item.maPM);
            tvTenSach.setText("Tên sách: " + sach.tenSach);
            tvTenTV.setText("Thành viên: " + thanhVien.hoTen);
            tvTienThue.setText("Tiền thuê: " + sach.giaThue);
            tvNgay.setText("Ngày thuê: " + sdf.format(item.ngay));
            if (item.traSach == 1) {
                tvTraSach.setText("Đã trả sách");
                tvTraSach.setTextColor(Color.BLUE);
            } else {
                tvTraSach.setText("Chưa trả sách");
                tvTraSach.setTextColor(Color.RED);
            }
            btn_delete.setOnClickListener(v -> {
                fragment.xoa(String.valueOf(item.maPM));
            });
        }
        return view;
    }
}
