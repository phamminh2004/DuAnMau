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
import fpoly.minhptph32719.duanmau.fragment.TopFragment;
import fpoly.minhptph32719.duanmau.model.Top;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment fragment;
    private ArrayList<Top> lists;
    TextView tv_sach, tv_soLuong;

    public TopAdapter(@NonNull Context context, TopFragment fragment, ArrayList<Top> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            view = inflater.inflate(R.layout.item_top, null);
        }
        final Top item = lists.get(position);
        if (item != null) {
            tv_sach = view.findViewById(R.id.tv_sach);
            tv_soLuong = view.findViewById(R.id.tv_soLuong);
            tv_sach.setText("Sách: " + item.tenSach);
            tv_soLuong.setText("Số lượng: " + item.soLuong);
        }
        return view;
    }

}
