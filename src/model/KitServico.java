package model;

public class KitServico {

    private int servicoId;
    private int produtoId;
    private int qtidadeProdutoPorServico;

    public KitServico() {
    }

    public KitServico(int servicoId, int produtoId, int qtidadeProdutoPorServico) {
        this.servicoId = servicoId;
        this.produtoId = produtoId;
        this.qtidadeProdutoPorServico = qtidadeProdutoPorServico;
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
    
    

}
