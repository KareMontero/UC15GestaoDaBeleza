package model;

public class Produto {

    private int idProduto;
    private String nomeProduto;
    private String descricao;
    private double precoVenda;
    private int estoqueAtual;
    private boolean ativo;

    private int fkFornecedor;

    public Produto() {
    }

    public Produto(int idProduto, String nomeProduto, String descricao, double precoVenda, int estoqueAtual, boolean ativo, int fkFornecedor) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.estoqueAtual = estoqueAtual;
        this.ativo = ativo;
        this.fkFornecedor = fkFornecedor;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getFkFornecedor() {
        return fkFornecedor;
    }

    public void setFkFornecedor(int fkFornecedor) {
        this.fkFornecedor = fkFornecedor;
    }

}
