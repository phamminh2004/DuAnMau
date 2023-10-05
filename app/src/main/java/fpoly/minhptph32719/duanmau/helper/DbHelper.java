package fpoly.minhptph32719.duanmau.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "PNLIB";
    static final int dbVersion = 1;

    public DbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableThuThu = "CREATE TABLE ThuThu(\n" +
                "  maTT text PRIMARY KEY,\n" +
                "  hoTen text NOT NULL,\n" +
                "  matKhau text NOT NULL\n" +
                ")";
        db.execSQL(tableThuThu);
        String tt = "INSERT INTO ThuThu VALUES\n" +
                "('admin','Administrator','admin')," +
                "('tt1','Phạm Hải Nam','tt1')," +
                "('tt2','Nguyễn Văn An','tt2')";
        db.execSQL(tt);

        String tableThanhVien = "CREATE TABLE ThanhVien(\n" +
                "  maTV integer PRIMARY KEY AUTOINCREMENT,\n" +
                "  hoTen text not NULL,\n" +
                "  namSinh text NOT NULL\n" +
                ")\n";
        db.execSQL(tableThanhVien);
        String tv = "INSERT INTO ThanhVien VALUES\n" +
                "(1,'Phạm Thị Linh',2004)," +
                "(2,'Nguyễn Văn Tú',2000)," +
                "(3,'Dương Quốc Anh',2001)," +
                "(4,'Kiều Mỹ Duyên',1998)";
        db.execSQL(tv);

        String tableLoaiSach = "CREATE TABLE LoaiSach(\n" +
                "  maLoai integer PRIMARY KEY AUTOINCREMENT,\n" +
                "  tenLoai text NOT NULL\n" +
                ")";
        db.execSQL(tableLoaiSach);
        String ls = "INSERT INTO LoaiSach VALUES\n" +
                "(1,'Java')," +
                "(2,'JS')," +
                "(3,'C++')," +
                "(4,'Android')";
        db.execSQL(ls);

        String tableSach = "CREATE TABLE Sach(\n" +
                "  maSach integer PRIMARY KEY AUTOINCREMENT,\n" +
                "  tenSach text NOT NULL,\n" +
                "  giaThue integer NOT NULL,\n" +
                "  maLoai integer REFERENCES LoaiSach(maLoai) \n" +
                ")";
        db.execSQL(tableSach);
        String s = "INSERT INTO Sach VALUES\n" +
                "(1,'Java 1',100000,1)," +
                "(2,'JavaScript cơ bản',120000,2)," +
                "(3,'Nhập môn lập trình',80000,3)," +
                "(4,'Android 1&2',320000,4)";
        db.execSQL(s);

        String tablePhieuMuon = "CREATE TABLE PhieuMuon(\n" +
                "  maPM integer PRIMARY KEY AUTOINCREMENT,\n" +
                "  maTT text REFERENCES ThuThu(maTT),\n" +
                "  maTV integer REFERENCES ThanhVien(maTV),\n" +
                "  maSach integer REFERENCES Sach(maSach),\n" +
                "  tienThue integer NOT NULL,\n" +
                "  ngay date NOT NULL,\n" +
                "  traSach integer NOT NULL\n" +
                ")\n";
        db.execSQL(tablePhieuMuon);
        String pm = "INSERT INTO PhieuMuon VALUES\n" +
                "(1,'tt1',1,1,100000,'2023-05-10',1)," +
                "(2,'tt2',2,3,80000,'2023-05-30',0)," +
                "(3,'tt2',4,2,120000,'2023-06-06',1)," +
                "(4,'tt1',3,4,320000,'2023-08-18',1)," +
                "(5,'tt1',2,1,100000,'2023-09-12',0)," +
                "(6,'tt2',1,4,320000,'2023-10-01',0)";
        db.execSQL(pm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
        onCreate(db);
    }
}
