package fpoly.minhptph32719.duanmau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.adapter.TopAdapter;
import fpoly.minhptph32719.duanmau.dao.ThongKeDAO;
import fpoly.minhptph32719.duanmau.model.Top;

public class TopFragment extends Fragment {
    ListView lv_top;
    ArrayList<Top> list;
    TopAdapter adapter;
    ThongKeDAO thongKeDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        lv_top = view.findViewById(R.id.lv_top);
        thongKeDAO = new ThongKeDAO(getActivity());
        list = (ArrayList<Top>) thongKeDAO.getTop();
        adapter = new TopAdapter(getActivity(), this, list);
        lv_top.setAdapter(adapter);
        return view;
    }
}