package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ShopFoodDataBase extends SQLiteOpenHelper {
    static final String dbName ="SHOPFOOD";
    static final int dbVersion = 1;
    public ShopFoodDataBase(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableTaiKhoan = "create table TaiKhoan(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserName TEXT NOT NULL," +
                "PassWord TEXT NOT NULL," +
                "Roled INTEGER REFERENCES VaiTro(Id))";
        sqLiteDatabase.execSQL(createTableTaiKhoan);

        String createTableVaiTro = "create table VaiTro(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "Description TEXT NOT NULL," +
                "Created TEXT NOT NULL," +
                "Updated TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTableVaiTro);

        String createTablThongTinNguoiDung = "create table ThongTinNguoiDung(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "AccountID INTEGER REFERENCES TaiKhoan(Id)," +
                "FullName TEXT NOT NULL," +
                "Email TEXT NOT NULL," +
                "SDT TEXT NOT NULL," +
                "Addres TEXT NOT NULL," +
                "Avatar TEXT NOT NULL," +
                "Birthday DATE NOT NULL," +
                "Gender INT NOT NULL," +
                "Created  TEXT NOT NULL," +
                "Updated TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTablThongTinNguoiDung);

        String createTableDatHang = "create table DatHang(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "AccountId INTEGER REFERENCES TaiKhoan(Id)," +
                "TotalPrice  FLOAT NOT NULL," +
                "Created TEXT NOT NULL," +
                "Updated TEXT NOT NULL," +
                "Status INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(createTableDatHang);

        String createTableChiTietDatHang = "create table ChiTietDatHang(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "OrderId INTEGER REFERENCES DatHang(Id)," +
                "ProductId INTEGER NOT NULL," +
                "UnitPrice FLOAT NOT NULL," +
                "Amount INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(createTableChiTietDatHang);

        String createTableCuaHang = "create table CuaHang(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "Image TEXT NOT NULL," +
                "Phone TEXT NOT NULL," +
                "Email TEXT NOT NULL," +
                "Addres TEXT NOT NULL," +
                "Created TEXT NOT NULL," +
                "Updated TEXT NOT NULL," +
                "Status INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(createTableCuaHang);

        String createTableLoaiSanPham = "create table LoaiSanPham(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IdCuaHang INTEGER REFERENCES CuaHang(Id)," +
                "Name TEXT NOT NULL," +
                "Image TEXT NOT NULL," +
                "Created TEXT NOT NULL," +
                "Updated TEXT," +
                "Status INTEGER)";
        sqLiteDatabase.execSQL(createTableLoaiSanPham);

        String createTableSanPham = "create table SanPham(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "Image TEXT NOT NULL," +
                "Price FLOAT NOT NULL," +
                "TypeProduct INTEGER REFERENCES LoaiSanPham(Id)," +
                "Created TEXT NOT NULL," +
                "Updated TEXT," +
                "Status Integer)";
        sqLiteDatabase.execSQL(createTableSanPham);

        sqLiteDatabase.execSQL("INSERT INTO VaiTro VALUES(1,'ADMIN', 'ADMIN','20/03/2024','20/03/2024'),(2,'NHANVIEN','NHANVIEN','20/03/2024','20/03/2024'),(3,'KHACHHANG','KHACHHANG','20/03/2024','20/03/2024')");
        sqLiteDatabase.execSQL("INSERT INTO TaiKhoan VALUES(1,'ADMIN','1',1),(2,'KH1','KH1',3),(3,'NV1','NV1',2),(4,'KH2','KH2',3)");
        sqLiteDatabase.execSQL("INSERT INTO ThongTinNguoiDung VALUES(0,2,'huylam','null','0866177203','ThaiBinh','700042','15/10/2003','Nam','24/03/2024','24/03/2024')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableTaiKhoan = "drop table if exists TaiKhoan";
        sqLiteDatabase.execSQL(dropTableTaiKhoan);

        String dropTablVaiTro = "drop table if exists VaiTro";
        sqLiteDatabase.execSQL(dropTablVaiTro);

        String dropTableThongTinNguoiDung = "drop table ThongTinNguoiDung";
        sqLiteDatabase.execSQL(dropTableThongTinNguoiDung);

        String dropTableDatHang = "drop table DatHang";
        sqLiteDatabase.execSQL(dropTableDatHang);

        String dropTableChiTietDatHang = "drop table ChiTietDatHang";
        sqLiteDatabase.execSQL(dropTableChiTietDatHang);

        String dropTableCuaHang = "drop table CuaHang";
        sqLiteDatabase.execSQL(dropTableCuaHang);

        String dropTableLoaiSanPham = "drop table LoaiSanPham";
        sqLiteDatabase.execSQL(dropTableLoaiSanPham);

        String dropTableSanPham = "drop table SanPham";
        sqLiteDatabase.execSQL(dropTableSanPham);

        onCreate(sqLiteDatabase);


    }
}
