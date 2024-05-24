package br.edu.fecap.projetofecap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {

    private List<Evento> eventos;
    private OnItemClickListener mListener; // Interface para lidar com cliques nos itens da lista

    // Interface para lidar com cliques nos itens da lista
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Método para definir o listener de clique nos itens da lista
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNomeEvento;
        public TextView textViewDataHoraEvento;

        public EventoViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewNomeEvento = itemView.findViewById(R.id.textViewNomeEvento);
            textViewDataHoraEvento = itemView.findViewById(R.id.textViewDataHora);

            // Configurando OnClickListener para os itens da lista
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            // Chama o método onItemClick da interface quando um item é clicado
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public EventoAdapter(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento, parent, false);
        EventoViewHolder viewHolder = new EventoViewHolder(v, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        Evento eventoAtual = eventos.get(position);
        holder.textViewNomeEvento.setText(eventoAtual.getNome());
        holder.textViewDataHoraEvento.setText(eventoAtual.getData() + " - " + eventoAtual.getHora());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    // Método para atualizar a lista de eventos
    public void updateEventos(List<Evento> eventos) {
        this.eventos = eventos;
        notifyDataSetChanged();
    }
}
