package com.example.k11.k;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.k11.k.Helpers.SqliteHelper;
import com.example.k11.k.Utilidades.IdUser;
import com.example.k11.k.View.CrearUsuario;
import com.example.k11.k.View.VerEncargos;

public class MainActivity extends AppCompatActivity {
    private EditText edtNombreUsuarioIng, edtContraUsuIng;
    private Button btnIngresar, btnIngCrearUsus;
    SqliteHelper sqliteHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asignacion de variables
        sqliteHelper = new SqliteHelper(this, "DB_ENCARGOS", null, 1);
        btnIngresar = (Button) findViewById(R.id.btnIngUsu);
        btnIngCrearUsus = (Button) findViewById(R.id.btnIngRegUsu);
        edtNombreUsuarioIng = (EditText) findViewById(R.id.ingNomUsu);
        edtContraUsuIng = (EditText) findViewById(R.id.ingPasUsu);


        //acciones iniciales
        getSupportActionBar().setTitle("Inicio");
        inicio();

        //eventos de clic
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingreso();
            }
        });

        btnIngCrearUsus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CrearUsuario.class);
                startActivity(intent);
            }
        });

    }


    public void ingreso() {
        if (edtNombreUsuarioIng.getText().toString().isEmpty() | edtContraUsuIng.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "El campo de usuario o contraseña estan vacios", Toast.LENGTH_SHORT).show();
        } else {
            db = sqliteHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM USUARIO WHERE USUARIO.NOMBRE = '" + edtNombreUsuarioIng.getText().toString() + "' AND USUARIO.CONTRASENA = '" + edtContraUsuIng.getText().toString() + "'", null);

            if (cursor.getCount() == 1) {

                while (cursor.moveToNext()) {
                    IdUser.setIdUser(cursor.getInt(0));
                }
                db = sqliteHelper.getWritableDatabase();
                db.execSQL("UPDATE USUARIO SET ESTADO_CON = 'Activo' WHERE ID = " + IdUser.getIdUser());
                db.close();
                edtContraUsuIng.setText("");
                edtNombreUsuarioIng.setText("");
                finish();
                Intent intent = new Intent(getApplicationContext(), VerEncargos.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
            }
        }


    }

    public void inicio() {
        db = sqliteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USUARIO WHERE USUARIO.ESTADO_CON = 'Activo'", null);
        if (cursor.getCount() == 1) {
            while (cursor.moveToNext()) {
                IdUser.setIdUser(cursor.getInt(0));
                finish();
                Intent intent = new Intent(getApplicationContext(), VerEncargos.class);
                startActivity(intent);
            }
        }

    }
}
