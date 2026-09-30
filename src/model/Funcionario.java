package model;

import java.sql.Timestamp;
import java.util.Date;

public class Funcionario {

    private int idFuncionario;
    private String nomeFuncionario;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private String funcao;
    private String carteiraTrabalho;
    private Timestamp dataContratacao;
    private String disponibilidadeDeHorario;
    private double valorDoServico;
    private boolean ativo;
    private Date dataCadastro;
    private int fkEndereco;
    private int fkTelefone;

    public Funcionario() {
    }

    public Funcionario(int idFuncionario, String nomeFuncionario, String cpf, Date dataNascimento, String email, String funcao, String carteiraTrabalho, Timestamp dataContratacao, String disponibilidadeDeHorario, double valorDoServico, boolean ativo, Date dataCadastro, int fkEndereco, int fkTelefone) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.funcao = funcao;
        this.carteiraTrabalho = carteiraTrabalho;
        this.dataContratacao = dataContratacao;
        this.disponibilidadeDeHorario = disponibilidadeDeHorario;
        this.valorDoServico = valorDoServico;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.fkEndereco = fkEndereco;
        this.fkTelefone = fkTelefone;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getCarteiraTrabalho() {
        return carteiraTrabalho;
    }

    public void setCarteiraTrabalho(String carteiraTrabalho) {
        this.carteiraTrabalho = carteiraTrabalho;
    }

    public Timestamp getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Timestamp dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getDisponibilidadeDeHorario() {
        return disponibilidadeDeHorario;
    }

    public void setDisponibilidadeDeHorario(String disponibilidadeDeHorario) {
        this.disponibilidadeDeHorario = disponibilidadeDeHorario;
    }

    public double getValorDoServico() {
        return valorDoServico;
    }

    public void setValorDoServico(double valorDoServico) {
        this.valorDoServico = valorDoServico;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getFkEndereco() {
        return fkEndereco;
    }

    public void setFkEndereco(int fkEndereco) {
        this.fkEndereco = fkEndereco;
    }

    public int getFkTelefone() {
        return fkTelefone;
    }

    public void setFkTelefone(int fkTelefone) {
        this.fkTelefone = fkTelefone;
    }

}
