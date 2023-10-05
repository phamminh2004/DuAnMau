package fpoly.minhptph32719.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.minhptph32719.duanmau.helper.DbHelper;
import fpoly.minhptph32719.duanmau.model.ThanhVien;
import fpoly.minhptph32719.duanmau.model.ThuThu;

public class ThuThuDAO {
    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThuThu tt) {
        ContentValues values = new ContentValues();
        values.put("maTT", tt.maTT);
        values.put("hoTen", tt.hoTen);
        values.put("matKhau", tt.matKhau);
        return db.insert("ThuThu", null, values);
    }

    public int updatePass(ThuThu tt) {
        ContentValues values = new ContentValues();
        values.put("hoTen", tt.hoTen);
        values.put("matKhau", tt.matKhau);
        return db.update("ThuThu", values, "maTT=?", new String[]{tt.maTT});
    }

    public int delete(String id) {
        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String... selectionArgs) {
        List<ThuThu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            ThuThu tt = new ThuThu();
            tt.maTT = cursor.getString((cursor.getColumnIndex("maTT")));
            tt.hoTen = cursor.getString(cursor.getColumnIndex("hoTen"));
            tt.matKhau = cursor.getString(cursor.getColumnIndex("matKhau"));
            list.add(tt);
        }
        return list;
    }

    public List<ThuThu> getAll() {
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    public ThuThu getID(String id) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql, id);
        return list.get(0);
    }

    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }
}
