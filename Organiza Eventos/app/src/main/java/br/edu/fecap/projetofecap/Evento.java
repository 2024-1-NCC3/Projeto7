package br.edu.fecap.projetofecap;

public class Evento {
    private String id;
    private String codigoOferta;
    private String nome;
    private String tipo;
    private String descricao;
    private String categoria;
    private String publicoAlvo;
    private String local;
    private boolean belezaEstetica;
    private boolean bemEstar;
    private boolean comunicacaoMarketing;
    private boolean designArtesArquitetura;
    private boolean desenvolvimentoSocial;
    private boolean educacao;
    private boolean gastronomiaAlimentacao;
    private boolean gestaoNegocios;
    private boolean idiomas;
    private boolean meioAmbienteSeguranca;
    private boolean moda;
    private boolean saude;
    private boolean tecnologiaInformacao;
    private boolean turismoHospitalidade;

    private String data;
    private String hora;

    public Evento(String id,String codigoOferta, String nome, String tipo, String descricao, String categoria, String publicoAlvo, String local, String data, String hora,
                  boolean belezaEstetica, boolean bemEstar, boolean comunicacaoMarketing, boolean designArtesArquitetura,
                  boolean desenvolvimentoSocial, boolean educacao, boolean gastronomiaAlimentacao, boolean gestaoNegocios,
                  boolean idiomas, boolean meioAmbienteSeguranca, boolean moda, boolean saude, boolean tecnologiaInformacao,
                  boolean turismoHospitalidade) {
        this.id = id;
        this.codigoOferta = codigoOferta;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.publicoAlvo = publicoAlvo;
        this.local = local;
        this.belezaEstetica = belezaEstetica;
        this.bemEstar = bemEstar;
        this.comunicacaoMarketing = comunicacaoMarketing;
        this.designArtesArquitetura = designArtesArquitetura;
        this.desenvolvimentoSocial = desenvolvimentoSocial;
        this.educacao = educacao;
        this.gastronomiaAlimentacao = gastronomiaAlimentacao;
        this.gestaoNegocios = gestaoNegocios;
        this.idiomas = idiomas;
        this.meioAmbienteSeguranca = meioAmbienteSeguranca;
        this.moda = moda;
        this.saude = saude;
        this.tecnologiaInformacao = tecnologiaInformacao;
        this.turismoHospitalidade = turismoHospitalidade;
        this.data = data;
        this.hora = hora;
    }

    public String getId() { return id; }

    public void setId(String id) {this.id = id; }

    public String getCodigoOferta() {
        return codigoOferta;
    }

    public void setCodigoOferta(String codigoOferta) {
        this.codigoOferta = codigoOferta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isBelezaEstetica() {
        return belezaEstetica;
    }

    public void setBelezaEstetica(boolean belezaEstetica) {
        this.belezaEstetica = belezaEstetica;
    }

    public boolean isBemEstar() {
        return bemEstar;
    }

    public void setBemEstar(boolean bemEstar) {
        this.bemEstar = bemEstar;
    }

    public boolean isComunicacaoMarketing() {
        return comunicacaoMarketing;
    }

    public void setComunicacaoMarketing(boolean comunicacaoMarketing) {
        this.comunicacaoMarketing = comunicacaoMarketing;
    }

    public boolean isDesignArtesArquitetura() {
        return designArtesArquitetura;
    }

    public void setDesignArtesArquitetura(boolean designArtesArquitetura) {
        this.designArtesArquitetura = designArtesArquitetura;
    }

    public boolean isDesenvolvimentoSocial() {
        return desenvolvimentoSocial;
    }

    public void setDesenvolvimentoSocial(boolean desenvolvimentoSocial) {
        this.desenvolvimentoSocial = desenvolvimentoSocial;
    }

    public boolean isEducacao() {
        return educacao;
    }

    public void setEducacao(boolean educacao) {
        this.educacao = educacao;
    }

    public boolean isGastronomiaAlimentacao() {
        return gastronomiaAlimentacao;
    }

    public void setGastronomiaAlimentacao(boolean gastronomiaAlimentacao) {
        this.gastronomiaAlimentacao = gastronomiaAlimentacao;
    }

    public boolean isGestaoNegocios() {
        return gestaoNegocios;
    }

    public void setGestaoNegocios(boolean gestaoNegocios) {
        this.gestaoNegocios = gestaoNegocios;
    }

    public boolean isIdiomas() {
        return idiomas;
    }

    public void setIdiomas(boolean idiomas) {
        this.idiomas = idiomas;
    }

    public boolean isMeioAmbienteSeguranca() {
        return meioAmbienteSeguranca;
    }

    public void setMeioAmbienteSeguranca(boolean meioAmbienteSeguranca) {
        this.meioAmbienteSeguranca = meioAmbienteSeguranca;
    }

    public boolean isModa() {
        return moda;
    }

    public void setModa(boolean moda) {
        this.moda = moda;
    }

    public boolean isSaude() {
        return saude;
    }

    public void setSaude(boolean saude) {
        this.saude = saude;
    }

    public boolean isTecnologiaInformacao() {
        return tecnologiaInformacao;
    }

    public void setTecnologiaInformacao(boolean tecnologiaInformacao) {
        this.tecnologiaInformacao = tecnologiaInformacao;
    }

    public boolean isTurismoHospitalidade() {
        return turismoHospitalidade;
    }

    public void setTurismoHospitalidade(boolean turismoHospitalidade) {
        this.turismoHospitalidade = turismoHospitalidade;
    }
}
