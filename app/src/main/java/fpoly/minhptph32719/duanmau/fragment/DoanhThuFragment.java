package fpoly.minhptph32719.duanmau.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.datepicker.SingleDateSelector;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import fpoly.minhptph32719.duanmau.R;
import fpoly.minhptph32719.duanmau.dao.ThongKeDAO;

public class DoanhThuFragment extends Fragment {
    TextView tv_start, tv_end, tv_doanhThu;
    EditText edt_start, edt_end;
    Button btn_doanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        edt_start = view.findViewById(R.id.edt_start);
        edt_end = view.findViewById(R.id.edt_end);
        tv_start = view.findViewById(R.id.tv_start);
        tv_end = view.findViewById(R.id.tv_end);
        tv_doanhThu = view.findViewById(R.id.tv_doanhThu);
        btn_doanhThu = view.findViewById(R.id.btn_doanhThu);
        tv_start.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateStart, mYear, mMonth, mDay);
            d.show();
        });
        tv_end.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateEnd, mYear, mMonth, mDay);
            d.show();
        });
        btn_doanhThu.setOnClickListener(v -> {
            String start = edt_start.getText().toString();
            String end = edt_end.getText().toString();
            ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
            tv_doanhThu.setText("Doanh Thu: " + thongKeDAO.getDoanhThu(start, end) + "VND");
        });
        return view;
    }

    DatePickerDialog.OnDateSetListener mDateStart = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edt_start.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDateEnd = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edt_end.setText(sdf.format(c.getTime()));
        }
    };
}