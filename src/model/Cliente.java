package model;

import java.sql.Timestamp;
import java.util.Date;

public class Cliente {

    private int idCliente;
    private String nomeCliente;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private Timestamp dataCadastro;
    private String observacao;
    private boolean ativo;
    private int fkEndereco;
    private String telefonesFormatados;
    

    public Cliente() {
    }

    public Cliente(int idCliente, String nomeCliente, String cpf, Date dataNascimento, String email, Timestamp dataCadastro, String observacao, boolean ativo, int fkEndereco, String telefonesFormatados) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.dataCadastro = dataCadastro;
        this.observacao = observacao;
        this.ativo = ativo;
        this.fkEndereco = fkEndereco;
        this.telefonesFormatados = telefonesFormatados;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(int fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public String getTelefonesFormatados() {
        return telefonesFormatados;
    }

    public void setTelefonesFormatados(String telefonesFormatados) {
        this.telefonesFormatados = telefonesFormatados;
    }

   
}
