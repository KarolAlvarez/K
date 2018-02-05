package com.example.k11.k.View;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.k11.k.Helpers.SqliteHelper;
import com.example.k11.k.MainActivity;
import com.example.k11.k.R;
import com.example.k11.k.Utilidades.Constantes;
import com.example.k11.k.Utilidades.IdUser;

public class AgregarEncargo extends AppCompatActivity {

    Bundle bundle;
    SqliteHelper sqliteHelper;
    SQLiteDatabase db;

    EditText edtNombreEncargo,
            edtCantidadEncargo,
            edtDescEncargo,
            edtNomCliente,
            edtCelCli;

    private Button btnGuardarEncargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_encargo);
        //asignacion de vaiables
        sqliteHelper = new SqliteHelper(this, "DB_ENCARGOS", null, 1);
        btnGuardarEncargo = findViewById(R.id.btnGuardarEncargo);
        edtNombreEncargo = findViewById(R.id.edtNomEncargo);
        edtCantidadEncargo = findViewById(R.id.edtCanEncargo);
        edtDescEncargo = findViewById(R.id.edtDescEncargo);
        edtNomCliente = findViewById(R.id.edtNomCliEncargo);
        edtCelCli = findViewById(R.id.edtCelCliEncargo);

        //acciones iniciales
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //eventos clic
        btnGuardarEncargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearEncargo();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mi_menu2, menu);
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
            case android.R.id.home:
                finish();
                Intent inte = new Intent(getApplicationContext(), VerEncargos.class);
                startActivity(inte);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void crearEncargo() {
        if (edtNombreEncargo.getText().toString().isEmpty() |
                edtCantidadEncargo.getText().toString().isEmpty() |
                edtNomCliente.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Uno de los campos importantes esta vacio", Toast.LENGTH_LONG).show();
        } else {
            db = sqliteHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Constantes.TABLA_ENCARGO_NOM_ENCARGO, edtNombreEncargo.getText().toString());
            values.put(Constantes.TABLA_ENCARGO_CANTIDAD, edtCantidadEncargo.getText().toString());
            values.put(Constantes.TABLA_ENCARGO_DESCRIPCION, edtDescEncargo.getText().toString());
            values.put(Constantes.TABLA_ENCARGO_NOM_CLIENTE, edtNomCliente.getText().toString());
            values.put(Constantes.TABLA_ENCARGO_CEL_CLIENTE, edtCelCli.getText().toString());
            values.put(Constantes.TABLA_ENCARGO_ID_USUARIO, IdUser.getIdUser());

            Long query = db.insert(Constantes.TABLA_NOMBRE_ENCARGO, Constantes.TABLA_ENCARGO_ID, values);
            if (query > 0) {
                Toast.makeText(getApplicationContext(), "El encargo se almaceno con exito", Toast.LENGTH_LONG).show();
                edtNombreEncargo.setText("");
                edtCantidadEncargo.setText("");
                edtDescEncargo.setText("");
                edtNomCliente.setText("");
                edtCelCli.setText("");
                finish();
                Intent intent = new Intent(getApplicationContext(), VerEncargos.class);
                startActivity(intent);
                db.close();
            }
        }
    }
}