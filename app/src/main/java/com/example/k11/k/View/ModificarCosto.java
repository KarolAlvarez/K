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

public class ModificarCosto extends AppCompatActivity {
    private Button btnModificarCosto;
    private EditText edtCostoDelEncargo;
    SqliteHelper sqliteHelper;
    SQLiteDatabase db;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_costo);

        //asignacion de vaiables
        sqliteHelper = new SqliteHelper(getApplicationContext(), "DB_ENCARGOS", null, 1);
        btnModificarCosto = findViewById(R.id.btnModificarCosto);
        edtCostoDelEncargo = findViewById(R.id.edtCostoDelEncargo);

        //acciones iniciales
        bundle = getIntent().getExtras();
        edtCostoDelEncargo.setText(Integer.toString(bundle.getInt("CostoUnit")));

        //eventos clic
        btnModificarCosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarCosto();
                finish();
                Intent intent = new Intent(getApplicationContext(), VerEncargos.class);
                startActivity(intent);
            }
        });
    }

    public void modificarCosto() {
        db = sqliteHelper.getWritableDatabase();
        db.execSQL("UPDATE ENCARGO SET COSTO_ENCARGO = " + Integer.parseInt(edtCostoDelEncargo.getText().toString())
                + "  WHERE ID_USUARIO = " + IdUser.getIdUser() + " AND ID = " + bundle.getInt("idEncargo"));
        db.close();
        edtCostoDelEncargo.setText("");
    }
}
