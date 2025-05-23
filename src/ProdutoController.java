public class ProdutoController {

    public ProdutoDBHandler dbHandler;

    public ProdutoController(){
        dbHandler = ProdutoDBHandler.getInstance();
    }

    public void addProduto(Produto produto) {
        dbHandler.criar(produto);
    }

    public void removerProdutoPorId(int id){
        dbHandler.remover(id);
    }

    public Produto buscarProdutoPorid(int id){
        return dbHandler.procurar(id);
    }
    public void imprimirProdutos(){
        dbHandler.imprimirProdutos();
    }
    public void atualizarProduto(Produto p){
        dbHandler.atualizar(p);
    }

}
