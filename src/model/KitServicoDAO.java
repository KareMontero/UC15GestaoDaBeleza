package model;

public class KitServicoDAO {
    private Produto produto;
    private KitServico servico;
    
     public double valorKitProduto(int qtidade) {
        return produto.getPrecoVenda() * qtidade;
        }
     
     public double calculoParcial(int qtidade) {
            return  valorKitProduto(qtidade) + servico.getPrecoServico();
}
}
