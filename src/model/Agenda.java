package model;

import java.sql.Timestamp;

public class Agenda {

    private int idAgenda;
    private Timestamp dataHora;
    private Cliente cliente;
    private Funcionario funcionario;
    private Servico servico;
    private Usuario usuario;
    private String status;
    private double precoServico;
    private double valorProduto;
    private double desconto;
    private double valorTotal;
    private String observacao;

    public Agenda() {
    }

    public Agenda(int idAgenda, Timestamp dataHora, Cliente cliente, Funcionario funcionario, Servico servico, Usuario usuario, String status, double precoServico, double valorProduto, double desconto, double valorTotal, String observacao) {
        this.idAgenda = idAgenda;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servico = servico;
        this.usuario = usuario;
        this.status = status;
        this.precoServico = precoServico;
        this.valorProduto = valorProduto;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.observacao = observacao;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(double precoServico) {
        this.precoServico = precoServico;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
