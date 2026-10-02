package model;

import java.sql.Timestamp;

public class MovimentoDeEstoque {

    private int idEstoque;
    private String tipo;
    private int qtidadeProduto;
    private String motivo;
    private int usuarioId;
    private Integer agendaId;
    private int produtoId;
    private Timestamp dataMovimentacao;

    private String nomeProduto;
    private String nomeUsuario;

    public MovimentoDeEstoque() {
    }

    public MovimentoDeEstoque(int idEstoque, String tipo, int qtidadeProduto, String motivo, int usuarioId, Integer agendaId, int produtoId, Timestamp dataMovimentacao, String nomeProduto, String nomeUsuario) {
        this.idEstoque = idEstoque;
        this.tipo = tipo;
        this.qtidadeProduto = qtidadeProduto;
        this.motivo = motivo;
        this.usuarioId = usuarioId;
        this.agendaId = agendaId;
        this.produtoId = produtoId;
        this.dataMovimentacao = dataMovimentacao;
        this.nomeProduto = nomeProduto;
        this.nomeUsuario = nomeUsuario;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtidadeProduto() {
        return qtidadeProduto;
    }

    public void setQtidadeProduto(int qtidadeProduto) {
        this.qtidadeProduto = qtidadeProduto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(Integer agendaId) {
        this.agendaId = agendaId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public Timestamp getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Timestamp dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

}
