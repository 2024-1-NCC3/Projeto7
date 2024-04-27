package br.edu.fecap.senacagendareventos;

public class Evento {
    private String codigoOferta, nomeEvento, data, tipo,descricao, categoria, publicoAlvo, local;


    // Construtor
    public Evento(String codigoOferta, String nomeEvento, String data, String tipo, String descricao, String categoria, String publicoAlvo, String local) {
        this.codigoOferta = codigoOferta;
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.publicoAlvo = publicoAlvo;
        this.local = local;
    }

    // Métodos getters e setters
    public String getCodigoOferta() {
        return codigoOferta;
    }

    public void setCodigoOferta(String codigoOferta) {
        this.codigoOferta = codigoOferta;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
