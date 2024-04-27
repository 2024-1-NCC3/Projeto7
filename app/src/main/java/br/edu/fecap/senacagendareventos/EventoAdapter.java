package br.edu.fecap.senacagendareventos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class EventoAdapter extends ArrayAdapter<Evento> {

    private LayoutInflater inflater;
    private List<Evento> eventos;

    public EventoAdapter(Context context, List<Evento> eventos) {
        super(context, 0, eventos);
        this.eventos = eventos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Evento getItem(int position) {
        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_evento_item, parent, false);
            holder = new ViewHolder();
            holder.nomeEventoTextView = convertView.findViewById(R.id.textViewNomeEvento);
            //Referências dos elementos de layout para os campos do evento
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Evento evento = eventos.get(position);
        holder.nomeEventoTextView.setText(evento.getNomeEvento());

        return convertView;
    }

    //Referências dos elementos de layout do evento
    private static class ViewHolder {
        TextView nomeEventoTextView;
    }
}
