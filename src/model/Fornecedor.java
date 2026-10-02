package model;

import java.sql.Timestamp;

public class Fornecedor {

    private int idFornecedor;
    private String razaoSocial;
    private String cnpj;
    private String pessoaDeContato;
    private String emailFornecedor;
    private boolean ativo;
    private String observacao;
    private Timestamp dataCadastro;
    private int fkEndereco;

    public Fornecedor() {
    }

    public Fornecedor(int idFornecedor, String razaoSocial, String cnpj, String pessoaDeContato, String emailFornecedor, boolean ativo, String observacao, Timestamp dataCadastro, int fkEndereco) {
        this.idFornecedor = idFornecedor;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.pessoaDeContato = pessoaDeContato;
        this.emailFornecedor = emailFornecedor;
        this.ativo = ativo;
        this.observacao = observacao;
        this.dataCadastro = dataCadastro;
        this.fkEndereco = fkEndereco;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPessoaDeContato() {
        return pessoaDeContato;
    }

    public void setPessoaDeContato(String pessoaDeContato) {
        this.pessoaDeContato = pessoaDeContato;
    }

    public String getEmailFornecedor() {
        return emailFornecedor;
    }

    public void setEmailFornecedor(String emailFornecedor) {
        this.emailFornecedor = emailFornecedor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(int fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

}
