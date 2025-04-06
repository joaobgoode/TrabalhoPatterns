import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public class ProdutoDBHandler extends DBHandler<Integer, Produto> {
    public static ProdutoDBHandler instance;
    private ProdutoDBHandler(String endereco){
        super(endereco);
    }

    public static ProdutoDBHandler getInstance() {
        if (instance == null){
            instance = new ProdutoDBHandler("produtos.csv");
        }
        return instance;
    }

    @Override
    public void lerCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(endereco))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] campos = linha.split(";");

                int id = Integer.parseInt(campos[0].trim());
                String tipo = campos[1].trim();
                String nome = campos[2].trim();
                String descricao = campos[3].trim();
                double preco = Double.parseDouble(campos[4].trim());
                int quantidade = Integer.parseInt(campos[5].trim());
                Produto produto = new Produto(id, tipo, nome, descricao, preco, quantidade);
                todos.put(id, produto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvar() {
        try (FileWriter writer = new FileWriter(endereco)) {
            writer.append("id;tipo;nome;descricao;preco;quantidade\n");

            for (Produto produto : todos.values()) {
                writer.write(produto.toCSV() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void criar(Produto objeto) {
        Integer id = objeto.getId();
        if (contem(id)){
            return;
        }
        todos.put(objeto.getId(), objeto);
        salvar();
    }

    @Override
    public Produto atualizar(Produto objeto) {
        Integer id = objeto.getId();
        if (contem(id)){
            todos.replace(id, todos.get(id), objeto);
            salvar();
            return objeto;
        }
        return null;
    }


    public void imprimirProdutos() {
        if (super.todos.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return;
        }

        for (Produto c : super.todos.values()) {
            System.out.println(
                    "Id: " + c.getId()
                            + ", tipo: " + c.getTipo()
                            + ", Nome: " + c.getNome()
                            + ", Descrição: " + c.getDescricao()
                            + ", Preço: " + c.getPreco()
                            + ", quantidade: " + c.getQuantidade());
        }
    }

}