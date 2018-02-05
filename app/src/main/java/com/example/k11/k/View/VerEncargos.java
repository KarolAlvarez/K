package com.example.k11.k.View;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.k11.k.Adaptadores.ListarEncargosAdapter;
import com.example.k11.k.Helpers.SqliteHelper;
import com.example.k11.k.MainActivity;
import com.example.k11.k.Modelos.Encargo;
import com.example.k11.k.R;
import com.example.k11.k.Utilidades.IdUser;

import java.util.ArrayList;
import java.util.List;

public class VerEncargos extends AppCompatActivity {

    List<Encargo> encargoList = new ArrayList<>();
    ListarEncargosAdapter listarEncargosAdapter;
    RecyclerView VistaDeEncargos;
    SqliteHelper sqliteHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_encargos);

        //asignacion de vaiables
        sqliteHelper = new SqliteHelper(this, "DB_ENCARGOS", null, 1);
        VistaDeEncargos = findViewById(R.id.VistaDeEncargos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //acciones iniciales
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        VistaDeEncargos.setLayoutManager(linearLayoutManager);
        listarEncargos();

        //eventos clic

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.cerrarSesion:
                db = sqliteHelper.getWritableDatabase();
                db.execSQL("UPDATE USUARIO SET ESTADO_CON = 'Inactivo' WHERE ID = " + IdUser.getIdUser());
                IdUser.setIdUser(0);
                db.close();
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.AgregarEncargo:
                Intent inten = new Intent(getApplicationContext(), AgregarEncargo.class);
                startActivity(inten);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void listarEncargos() {
        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ENCARGO WHERE ID_USUARIO= " + IdUser.getIdUser() + " ORDER BY ESTADO_COMPRA DESC", null);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No se encontro registro de Encargos", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                Encargo encargo = new Encargo();
                encargo.setIdEncargo(cursor.getInt(0));
                encargo.setNomEnc(cursor.getString(1));
                encargo.setCantidadEnc(cursor.getInt(2));
                encargo.setDescEnc(cursor.getString(3));
                encargo.setNomCli(cursor.getString(4));
                encargo.setCostoEnc(cursor.getInt(5));
                encargo.setEstadoCompra(cursor.getString(6));
                encargo.setCelCliente(cursor.getString(7));
                encargoList.add(encargo);
            }
        }
        cursor.close();
        if (encargoList.size() != 0) {
            processData();
        }
    }

    public void processData() {
        listarEncargosAdapter = new ListarEncargosAdapter(encargoList, getApplicationContext());
        VistaDeEncargos.setAdapter(listarEncargosAdapter);
    }
}