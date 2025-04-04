import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public class FuncionarioDBHandler extends DBHandler<Integer, Funcionario> {
    public static FuncionarioDBHandler instance;


    private FuncionarioDBHandler(String endereco){
        super(endereco);
    }

    public static FuncionarioDBHandler getInstance() {
        if (instance == null){
            instance = new FuncionarioDBHandler("funcionarios.csv");
        }
        return instance;
    }



    @Override
    public void carregar() {
        try (BufferedReader br = new BufferedReader(new FileReader(endereco))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] campos = linha.split(";");

                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                int cod_cargo = Integer.parseInt(campos[2]);
                Funcionario Funcionario = new Funcionario(id, nome, cod_cargo);
                todos.put(id, Funcionario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvar() {
        try (FileWriter writer = new FileWriter(endereco)) {
            writer.append("id;tipo;nome;descricao;preco;quantidade\n");

            for (Funcionario Funcionario : todos.values()) {
                writer.write(Funcionario.toString() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void criar(Funcionario objeto) {
        Integer id = objeto.getId();
        if (contem(id)){
            return;
        }
        todos.put(objeto.getId(), objeto);
        salvar();
    }

    @Override
    public Funcionario atualizar(Funcionario objeto) {
        Integer id = objeto.getId();
        if (contem(id)){
            todos.replace(id, todos.get(id), objeto);
            salvar();
            return objeto;
        }
        return null;
    }


    public void imprimirFuncionarios() {
        if (super.todos.isEmpty()) {
            System.out.println("Nenhum Funcionario encontrado.");
            return;
        }

        for (Funcionario c : super.todos.values()) {
            System.out.println(
                    "ID: " + c.getId()
                            + ", Nome: " + c.getNome()
                            + ", Cargo: " + ((c.getCod_cargo() == 2) ? "Gerente" : "Vendedor"));
        }
    }
}