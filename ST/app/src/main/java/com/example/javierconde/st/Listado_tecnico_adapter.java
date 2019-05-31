package com.example.javierconde.st;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Listado_tecnico_adapter extends RecyclerView.Adapter<Listado_tecnico_adapter.ViewHolder> {
    private Context contextTec;
    private List<Listado_tecnico> listTec;
    private OnItemClickListener itemClickListener;

    public Listado_tecnico_adapter(Context contextTec, List<Listado_tecnico> listTec, OnItemClickListener itemClickListener) {
        this.contextTec = contextTec;
        this.listTec = listTec;
        this.itemClickListener = itemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentTec, int viewTypeTec) {
        View vTec = LayoutInflater.from(contextTec).inflate(R.layout.single_item_tecnico, parentTec, false);
        return new ViewHolder(vTec);
    }

    @Override
    public void onBindViewHolder(ViewHolder holderTec, int positionTec) {
        holderTec.Bind(listTec.get(positionTec), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return listTec.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNo_ordenTec, txtEstadoTec, txtFechaTec, txtPrioridadTec, txtDomicilioTec;

        public ViewHolder(View itemViewTec) {
            super(itemViewTec);
            txtNo_ordenTec = itemView.findViewById(R.id.main_no_ordenTec);
            txtEstadoTec = itemView.findViewById(R.id.main_estadoTec);
            txtFechaTec = itemView.findViewById(R.id.main_fechaTec);
            txtPrioridadTec = itemView.findViewById(R.id.main_prioridadTec);
            txtDomicilioTec = itemView.findViewById(R.id.main_domicilioTec);
        }

        public void Bind(final Listado_tecnico list, final OnItemClickListener listener){
            txtNo_ordenTec.setText("Orden No. " + list.getNo_ordenTec());
            txtEstadoTec.setText("Actividad " + list.getEstadoTec());

            switch (txtEstadoTec.getText().toString()) {
                case "Actividad finalizada":
                    txtEstadoTec.setTextColor(Color.GREEN);
                    break;
                case "Actividad pendiente":
                    txtEstadoTec.setTextColor(Color.RED);
                    break;
            }

            txtFechaTec.setText("Fecha: " + list.getFechaTec());

            txtPrioridadTec.setText("Prioridad " + list.getPrioridadTec());

            switch (txtPrioridadTec.getText().toString()) {
                case "Prioridad baja":
                    txtPrioridadTec.setTextColor(Color.GREEN);
                    break;
                case "Prioridad media":
                    txtPrioridadTec.setTextColor(Color.YELLOW);
                    break;
                case "Prioridad alta":
                    txtPrioridadTec.setTextColor(Color.RED);
                    break;
            }

            txtDomicilioTec.setText(list.getDomicilioTec());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(list, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Listado_tecnico listTec, int position);
    }
}
