package com.example.k11.k.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.k11.k.Helpers.SqliteHelper;
import com.example.k11.k.R;
import com.example.k11.k.Utilidades.IdUser;

public class ModificarCantidad extends AppCompatActivity {

    private EditText edtCantDelEncargo;
    private Button btnModificarCant;
    SqliteHelper sqliteHelper;
    SQLiteDatabase db;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cantidad);


        //asignacion de vaiables
        sqliteHelper = new SqliteHelper(getApplicationContext(), "DB_ENCARGOS", null, 1);
        btnModificarCant = (Button) findViewById(R.id.btnModificarCant);
        edtCantDelEncargo = (EditText) findViewById(R.id.edtCantDelEncargo);

        //acciones iniciales
        bundle = getIntent().getExtras();
        edtCantDelEncargo.setText(Integer.toString(bundle.getInt("cantEncargo")));


        //eventos clic


        btnModificarCant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCant();
                finish();
                Intent intent = new Intent(getApplicationContext(), VerEncargos.class);
                startActivity(intent);
            }
        });
    }

    public void modificarCant() {

        db = sqliteHelper.getWritableDatabase();
        db.execSQL("UPDATE ENCARGO SET CANTIDAD = " + Integer.parseInt(edtCantDelEncargo.getText().toString())
                + "  WHERE ID_USUARIO = " + IdUser.getIdUser() + " AND ID = " + bundle.getInt("idEncargo"));
        db.close();
        edtCantDelEncargo.setText("");
    }

}
