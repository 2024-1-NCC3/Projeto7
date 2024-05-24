package br.edu.fecap.projetofecap;


public class Event {
    private int id;
    private String nomeEvento;
    private String nomeColaborador;
    private String dataEvento;
    private String horaEvento;

    public Event(int id, String nomeEvento, String nomeColaborador, String dataEvento, String horaEvento) {
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.nomeColaborador = nomeColaborador;
        this.dataEvento = dataEvento;
        this.horaEvento = horaEvento;
    }

    public int getId() {
        return id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public String getHoraEvento() {
        return horaEvento;
    }
}
