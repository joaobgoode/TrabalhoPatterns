import java.io.FileWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public class ClienteDBHandler extends DBHandler<String, Cliente> {
    public static ClienteDBHandler instance;


    private ClienteDBHandler(String endereco){
        super(endereco);
    }

    public static ClienteDBHandler getInstance() {
        if (instance == null){
            instance = new ClienteDBHandler("clientes.csv");
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

                String nome = campos[0].trim();
                String cpf = campos[1].trim();
                String dataNascimento = campos[2].trim();
                String telefone = campos[3].trim();
                Cliente cliente = new Cliente(nome, cpf, dataNascimento, telefone);
                todos.put(cpf, cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvar() {
        try (FileWriter writer = new FileWriter(endereco)) {
            writer.append("nome;cpf;dataNascimento;telefone\n");

            for (Cliente Cliente : todos.values()) {
                writer.write(Cliente.toCSV() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void criar(Cliente objeto) {
        String cpf = objeto.getCpf();
        if (contem(cpf)){
            return;
        }
        todos.put(objeto.getCpf(), objeto);
        salvar();
    }

    @Override
    public Cliente atualizar(Cliente objeto) {
        String cpf = objeto.getCpf();
        System.out.println(cpf);
        if (contem(cpf)){
            todos.replace(cpf, todos.get(cpf), objeto);
            salvar();
            return objeto;
        }
        return null;
    }


    public void imprimirClientes() {
        if (super.todos.isEmpty()) {
            System.out.println("Nenhum Cliente encontrado.");
            return;
        }

        for (Cliente c : super.todos.values()) {
            System.out.println(
                            "Nome: " + c.getNome()
                            + ", CPF: " + c.getCpf()
                            + ", Data de Nascimento: " + c.getDataNascimento()
                            + ", Telefone: " + c.getTelefone());
        }
    }
}