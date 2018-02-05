package com.example.k11.k.View;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.k11.k.Helpers.SqliteHelper;
import com.example.k11.k.R;

public class CrearUsuario extends AppCompatActivity {
    private EditText edtNombreUsuarioReg, edtContrasehnaUsuReg;
    private Button btnRegUsuario;
    SqliteHelper sqliteHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        //asignacion de vaiables
        sqliteHelper = new SqliteHelper(this, "DB_ENCARGOS", null, 1);
        btnRegUsuario = (Button) findViewById(R.id.btnRegUsuario);
        edtNombreUsuarioReg = (EditText) findViewById(R.id.edtNomUsu);
        edtContrasehnaUsuReg = (EditText) findViewById(R.id.edtContraUsu);

        //acciones iniciales
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //eventos clic
        btnRegUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void registrarUsuario() {

        if (edtContrasehnaUsuReg.getText().toString().isEmpty() | edtNombreUsuarioReg.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Uno de los dos campos esta vacio", Toast.LENGTH_LONG).show();
        } else {
            db = sqliteHelper.getWritableDatabase();
            db.execSQL("INSERT INTO USUARIO ('NOMBRE','CONTRASENA') VALUES ('" + edtNombreUsuarioReg.getText().toString() +
                    "','" + edtContrasehnaUsuReg.getText().toString() + "')");
            db.close();
            edtNombreUsuarioReg.setText("");
            edtContrasehnaUsuReg.setText("");
            finish();
        }
    }
}