package com.example.k11.k.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.k11.k.Helpers.SqliteHelper;
import com.example.k11.k.Modelos.Encargo;
import com.example.k11.k.R;
import com.example.k11.k.Utilidades.IdUser;
import com.example.k11.k.View.ModificarCantidad;
import com.example.k11.k.View.ModificarCosto;
import com.example.k11.k.View.VerEncargos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by k11 on 25/01/18.
 */

public class ListarEncargosAdapter extends RecyclerView.Adapter<ListarEncargosAdapter.ViewHolder> {


    List<Encargo> encargoList = new ArrayList<>();
    Context context;

    public ListarEncargosAdapter(List<Encargo> encargoListList, Context context) {
        this.encargoList = encargoListList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.encargo, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.txtNomCli.setText(encargoList.get(position).getNomCli());
        holder.txtCelCli.setText(encargoList.get(position).getCelCliente());
        holder.txtTituloEnc.setText(encargoList.get(position).getNomEnc());
        holder.txtCantEncargo.setText(Integer.toString(encargoList.get(position).getCantidadEnc()));
        holder.txtDescEnc.setText(encargoList.get(position).getDescEnc());
        holder.txtCostoEncargo.setText(Integer.toString(encargoList.get(position).getCostoEnc()));
        holder.txtCostTotal.setText(Double.toString((encargoList.get(position).getCostoEnc()) * (encargoList.get(position).getCantidadEnc())));


        if (encargoList.get(position).getEstadoCompra().equals("Comprado")) {
            holder.checkEstadoDeCompra.setText(encargoList.get(position).getEstadoCompra());
            holder.checkEstadoDeCompra.setChecked(true);
        } else if (encargoList.get(position).getEstadoCompra().equals("Pendiente")) {
            holder.checkEstadoDeCompra.setText(encargoList.get(position).getEstadoCompra());
            holder.checkEstadoDeCompra.setChecked(false);
        }


        holder.txtModificarCosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ModificarCosto.class);
                intent.putExtra("idEncargo", encargoList.get(position).getIdEncargo());
                intent.putExtra("CostoUnit",encargoList.get(position).getCostoEnc());
                context.startActivity(intent);
            }
        });


        holder.txtModificarCant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ModificarCantidad.class);

                intent.putExtra("idEncargo", encargoList.get(position).getIdEncargo());
                intent.putExtra("cantEncargo", encargoList.get(position).getCantidadEnc());
                context.startActivity(intent);
            }
        });


        holder.checkEstadoDeCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqliteHelper sqliteHelper;
                SQLiteDatabase db;

                sqliteHelper = new SqliteHelper(context, "DB_ENCARGOS", null, 1);

                if (holder.checkEstadoDeCompra.isChecked()) {
                    //se debe agregar en favorito
                    db = sqliteHelper.getWritableDatabase();
                    db.execSQL("UPDATE ENCARGO SET ESTADO_COMPRA = 'Comprado'  WHERE ID_USUARIO = " + IdUser.getIdUser() + " AND ID = " + encargoList.get(position).getIdEncargo());
                    holder.checkEstadoDeCompra.setText("Comprado");
                } else {
                    holder.checkEstadoDeCompra.setText("Pendiente");
                    db = sqliteHelper.getWritableDatabase();
                    db.execSQL("UPDATE ENCARGO SET ESTADO_COMPRA = 'Pendiente'  WHERE ID_USUARIO = " + IdUser.getIdUser() + " AND ID = " + encargoList.get(position).getIdEncargo());

                }
            }
        });

        holder.txtEliminarEnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqliteHelper sqliteHelper;
                SQLiteDatabase db;

                sqliteHelper = new SqliteHelper(context, "DB_ENCARGOS", null, 1);

                db = sqliteHelper.getWritableDatabase();
                db.execSQL("DELETE FROM ENCARGO WHERE ID_USUARIO =" + IdUser.getIdUser() + " AND ID = " + encargoList.get(position).getIdEncargo());


                Intent intent = new Intent(view.getContext(), VerEncargos.class);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return encargoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNomCli, txtCelCli, txtTituloEnc, txtDescEnc, txtCostoEncargo,
                txtCostTotal, txtEliminarEnc, txtModificarCosto, txtCantEncargo,txtModificarCant;
        CheckBox checkEstadoDeCompra;

        public ViewHolder(View item) {
            super(item);
            txtModificarCant=(TextView)item.findViewById(R.id.txtModificarCant);
            txtNomCli = (TextView) item.findViewById(R.id.txtNombreCliente);
            txtCelCli = (TextView) item.findViewById(R.id.txtCelCli);
            txtTituloEnc = (TextView) item.findViewById(R.id.txtTituloEncargo);
            txtDescEnc = (TextView) item.findViewById(R.id.txtDescEncargo);
            txtCostoEncargo = (TextView) item.findViewById(R.id.txtCostoEncargo);
            txtCostTotal = (TextView) item.findViewById(R.id.txtCostoTotalEncargo);
            txtEliminarEnc = (TextView) item.findViewById(R.id.txtEliminarEncargo);
            txtModificarCosto = (TextView) item.findViewById(R.id.txtModificarCosto);
            txtCantEncargo = (TextView) item.findViewById(R.id.txtCantEncargo);
            checkEstadoDeCompra = (CheckBox) item.findViewById(R.id.checkEstadoDeCompra);

        }


    }

}





