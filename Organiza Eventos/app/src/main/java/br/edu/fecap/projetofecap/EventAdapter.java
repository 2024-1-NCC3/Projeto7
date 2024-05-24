package br.edu.fecap.projetofecap;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> eventList;
    private Context context;

    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.nomeEvento.setText(event.getNomeEvento());
        holder.nomeColaborador.setText(event.getNomeColaborador());
        holder.dataEvento.setText(event.getDataEvento());
        holder.horaEvento.setText(event.getHoraEvento());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EventDetailActivity.class);
            intent.putExtra("id", event.getId());
            intent.putExtra("nomeEvento", event.getNomeEvento());
            intent.putExtra("nomeColaborador", event.getNomeColaborador());
            intent.putExtra("dataEvento", event.getDataEvento());
            intent.putExtra("horaEvento", event.getHoraEvento());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView nomeEvento, nomeColaborador, dataEvento, horaEvento;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeEvento = itemView.findViewById(R.id.nomeEvento);
            nomeColaborador = itemView.findViewById(R.id.nomeColaborador);
            dataEvento = itemView.findViewById(R.id.dataEvento);
            horaEvento = itemView.findViewById(R.id.horaEvento);
        }
    }
}