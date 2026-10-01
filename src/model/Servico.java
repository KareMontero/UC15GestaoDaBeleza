package model;

public class Servico {

    private int idServico;
    private String servico;
    private String descricao;
    private double preco;
    private String categoria; // Pode usar String ou um ENUM em Java
    private boolean estoqueProduto;
    private int qtidadeProduto;
    private boolean ativo;

    public Servico() {
    }

    public Servico(int idServico, String servico, String descricao, double preco, String categoria, boolean estoqueProduto, int qtidadeProduto, boolean ativo) {
        this.idServico = idServico;
        this.servico = servico;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.estoqueProduto = estoqueProduto;
        this.qtidadeProduto = qtidadeProduto;
        this.ativo = ativo;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isEstoqueProduto() {
        return estoqueProduto;
    }

    public void setEstoqueProduto(boolean estoqueProduto) {
        this.estoqueProduto = estoqueProduto;
    }

    public int getQtidadeProduto() {
        return qtidadeProduto;
    }

    public void setQtidadeProduto(int qtidadeProduto) {
        this.qtidadeProduto = qtidadeProduto;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
}
