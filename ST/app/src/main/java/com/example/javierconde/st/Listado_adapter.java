package com.example.javierconde.st;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class Listado_adapter extends RecyclerView.Adapter<Listado_adapter.ViewHolder> {
    private Context context;
    private List<Listado_cliente> list;
    private OnItemClickListener itemClickListener;


    public Listado_adapter(Context context, List<Listado_cliente> list, OnItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.itemClickListener = itemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.Bind(list.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNo_orden, txtEstado, txtFecha, txtPrioridad, txtDomicilio;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNo_orden = itemView.findViewById(R.id.main_no_orden);
            txtEstado = itemView.findViewById(R.id.main_estado);
            txtFecha = itemView.findViewById(R.id.main_fecha);
            txtPrioridad = itemView.findViewById(R.id.main_prioridad);
            txtDomicilio = itemView.findViewById(R.id.main_domicilio);
        }

        public void Bind(final Listado_cliente list, final OnItemClickListener listener) {
            txtNo_orden.setText("Orden No. " + list.getNo_orden());
            txtEstado.setText("Actividad " + list.getEstado());

            switch (txtEstado.getText().toString()) {
                case "Actividad finalizada":
                    txtEstado.setTextColor(Color.GREEN);
                    break;
                case "Actividad pendiente":
                    txtEstado.setTextColor(Color.RED);
                    break;
            }

            txtFecha.setText("Fecha: " + list.getFecha());

            txtPrioridad.setText("Prioridad " + list.getPrioridad());

            switch (txtPrioridad.getText().toString()) {
                case "Prioridad baja":
                    txtPrioridad.setTextColor(Color.GREEN);
                    break;
                case "Prioridad media":
                    txtPrioridad.setTextColor(Color.YELLOW);
                    break;
                case "Prioridad alta":
                    txtPrioridad.setTextColor(Color.RED);
                    break;
            }

            txtDomicilio.setText(list.getDomicilio());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(list, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Listado_cliente list, int position);
    }
}
