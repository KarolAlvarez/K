package com.example.k11.k.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.k11.k.Utilidades.Constantes;

/**
 * Created by k11 on 25/01/18.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constantes.CREATE_TABLA_USUARIO);
        db.execSQL(Constantes.CREATE_TABLA_ENCARGO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_NOMBRE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_NOMBRE_ENCARGO);

        onCreate(db);
    }
}
