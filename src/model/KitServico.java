package model;

public class KitServico {

    private String nomeServico;
    private String descricao;
    private String categoriaServico;
    private Produto produto;
    private double precoServico;
    private double valorKit;
    private boolean ativo;
    private Fornecedor fornecedor;

    public KitServico() {
    }

    public KitServico(String nomeServico, String descricao, String categoriaServico, Produto produto, double precoServico, double valorKit, double qtidadeProdutoKit, boolean ativo) {
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.categoriaServico = categoriaServico;
        this.produto = produto;
        this.precoServico = precoServico;
        this.valorKit = valorKit;
       
        this.ativo = ativo;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoriaServico() {
        return categoriaServico;
    }

    public void setCategoriaServico(String categoriaServico) {
        this.categoriaServico = categoriaServico;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(double precoServico) {
        this.precoServico = precoServico;
    }

    public double getValorKit() {
        return valorKit;
    }

    public void setValorKit(double valorKit) {
        this.valorKit = valorKit;
    }



    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    


}