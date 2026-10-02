package model;

public class Telefone {

    private int idTelefone;
    private String telefoneTipo;
    private String ddd;
    private String telefone;
    private Integer fkFuncionario;
    private Integer fkCliente;
    private Integer fkFornecedor;

    public Telefone() {
    }

    public Telefone(int idTelefone, String telefoneTipo, String ddd, String telefone, Integer fkFuncionario, Integer fkCliente, Integer fkFornecedor) {
        this.idTelefone = idTelefone;
        this.telefoneTipo = telefoneTipo;
        this.ddd = ddd;
        this.telefone = telefone;
        this.fkFuncionario = fkFuncionario;
        this.fkCliente = fkCliente;
        this.fkFornecedor = fkFornecedor;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getTelefoneTipo() {
        return telefoneTipo;
    }

    public void setTelefoneTipo(String telefoneTipo) {
        this.telefoneTipo = telefoneTipo;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getFkFuncionario() {
        return fkFuncionario;
    }

    public void setFkFuncionario(Integer fkFuncionario) {
        this.fkFuncionario = fkFuncionario;
    }

    public Integer getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Integer fkCliente) {
        this.fkCliente = fkCliente;
    }

    public Integer getFkFornecedor() {
        return fkFornecedor;
    }

    public void setFkFornecedor(Integer fkFornecedor) {
        this.fkFornecedor = fkFornecedor;
    }

}
