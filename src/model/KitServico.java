package model;

public class KitServico {

    private int servicoId;
    private int produtoId;
    private int qtidadeProdutoPorServico;
    private String nomeProduto;
    private String nomeServico;
    private boolean ativo;

    public KitServico() {
    }

    public KitServico(int servicoId, int produtoId, int qtidadeProdutoPorServico, String nomeProduto, String nomeServico, boolean ativo) {
        this.servicoId = servicoId;
        this.produtoId = produtoId;
        this.qtidadeProdutoPorServico = qtidadeProdutoPorServico;
        this.nomeProduto = nomeProduto;
        this.nomeServico = nomeServico;
        this.ativo = ativo;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQtidadeProdutoPorServico() {
        return qtidadeProdutoPorServico;
    }

    public void setQtidadeProdutoPorServico(int qtidadeProdutoPorServico) {
        this.qtidadeProdutoPorServico = qtidadeProdutoPorServico;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

   
}
