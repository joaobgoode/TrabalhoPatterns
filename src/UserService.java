import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserService {

    public ProdutoController produtoController;
    public ClienteController clienteController;
    public FuncionarioController funcionarioController;
    FuncionarioFactory funcionarioFactory = new FuncionarioFactory();
    Scanner sc = new Scanner(System.in);
    public Funcionario user;

    public UserService(Funcionario user) {
        this.user = user;
        produtoController = new ProdutoController();
        clienteController = new ClienteController();
        funcionarioController = new FuncionarioController();
    }


    public Cliente addCliente(){

        System.out.println("Cadastrando cliente");
        ClienteBuilder clienteBuilder = new ClienteBuilder();

        System.out.println("Digite o nome do cliente: ");
        String nome = sc.nextLine();
        clienteBuilder.setNome(nome);

        System.out.println("Digite o cpf do cliente:");
        String cpf = sc.nextLine();
        clienteBuilder.setCpf(cpf);

        System.out.println("Digite a data de nascimento do cliente:");
        String dataNascimento = sc.nextLine();
        clienteBuilder.setDataNascimento(dataNascimento);

        System.out.println("Digite o telefone do cliente:");
        String telefone = sc.nextLine();
        clienteBuilder.setTelefone(telefone);

        Cliente cliente = clienteBuilder.criar();

        System.out.println("Cliente criado");
        clienteController.addCliente(cliente);

        return cliente;
    }


    public void removerCliente(){
        System.out.println("Qual o CPF do cliente que você quer deletar? ");
        String cpf = sc.nextLine();

        clienteController.removerClientePorCpf(cpf);
        System.out.println("Cliente deletado");
    }

    public void ListarClientes(){
        clienteController.listarClientes();
    }

    public void atualizarCliente(){
        ClienteBuilder clienteBuilderAtualizado = new ClienteBuilder();

        System.out.println("Qual o CPF do cliente que você quer atualizar?");
        String cpf = sc.nextLine();
        clienteBuilderAtualizado.setCpf(cpf);

        System.out.println("Digite o nome do cliente: ");
        String nome = sc.nextLine();
        clienteBuilderAtualizado.setNome(nome);

        System.out.println("Digite a data de nascimento do cliente:");
        String dataNascimento = sc.nextLine();
        clienteBuilderAtualizado.setDataNascimento(dataNascimento);

        System.out.println("Digite o telefone do cliente:");
        String telefone = sc.nextLine();
        clienteBuilderAtualizado.setTelefone(telefone);

        Cliente clienteAtualizado = clienteBuilderAtualizado.criar();

        clienteController.atualizarCliente(clienteAtualizado);
        System.out.println("Cliente atualizado");

    }

    public void imprimirClientes(){
        clienteController.imprimirClientes();
    }



    public Produto addProduto(){
        ProdutoBuilder produtoBuilder = new ProdutoBuilder();
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o id do produto:");
        int id = sc.nextInt();
        sc.nextLine();
        produtoBuilder.setId(id);

        System.out.println("Digite o tipo do produto:");
        String tipo = sc.nextLine();
        produtoBuilder.setTipo(tipo);

        System.out.println("Digite o nome do produto: ");
        String nome = sc.nextLine();
        produtoBuilder.setNome(nome);

        System.out.println("Digite a descrição do produto:");
        String descricao = sc.nextLine();
        produtoBuilder.setDescricao(descricao);

        System.out.println("Digite o quantidade do produto:");
        int qtd = sc.nextInt();
        sc.nextLine();
        produtoBuilder.setQuantidade(qtd);

        System.out.println("Digite o valor da produto:");
        double valor = sc.nextDouble();
        sc.nextLine();
        produtoBuilder.setPreco(valor);


        Produto produto = produtoBuilder.criar();
        System.out.println("Produto criado");

        produtoController.addProduto(produto);
        return produto;
    }



    public void atualizarProduto(){
        ProdutoBuilder produtoBuilderAtualizado = new ProdutoBuilder();


        System.out.println("Qual o id do produto que você quer atualizar?");
        int id = sc.nextInt();
        sc.nextLine();
        produtoBuilderAtualizado.setId(id);

        System.out.println("Digite o tipo do produto: ");
        String tipo = sc.nextLine();
        produtoBuilderAtualizado.setTipo(tipo);


        System.out.println("Digite o nome do produto: ");
        String nome = sc.nextLine();
        produtoBuilderAtualizado.setNome(nome);

        System.out.println("Digite a descrição do produto: ");
        String descricao = sc.nextLine();
        produtoBuilderAtualizado.setDescricao(descricao);

        System.out.println("Digite o preço do produto:");
        double preco = sc.nextDouble();
        produtoBuilderAtualizado.setPreco(preco);

        System.out.println("Digite a quantidade do produto:");
        int qtd = sc.nextInt();
        sc.nextLine();
        produtoBuilderAtualizado.setQuantidade(qtd);

        Produto produtoAtualizado = produtoBuilderAtualizado.criar();

        produtoController.atualizarProduto(produtoAtualizado);
        System.out.println("Produto atualizado");

    }
    public void buscarProdutoPorId(){
        System.out.println("Id do produto a procurar: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();

        produtoController.buscarProdutoPorid(id);
    }


    public void removerProdutoPorId(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o Id do produto que você quer deletar? ");
        int id = sc.nextInt();
        sc.nextLine();
        produtoController.removerProdutoPorId(id);
        System.out.println("Produto deletado");
    }
    public void imprimirProdutos(){
        produtoController.imprimirProdutos();
    }

    public Funcionario cadastrarFuncionario() {
        try {
            System.out.println("Qual cargo do Funcionario (1-vendedor, 2-gerente)");
            int cargoFunc = sc.nextInt();
            sc.nextLine();

            System.out.println("Nome do Funcionario: ");
            String nome = sc.next();
            sc.nextLine();

            System.out.println("id do Funcionario");
            int id = sc.nextInt();
            sc.nextLine();

            Funcionario funcionario = funcionarioFactory.criarFuncionario(id, nome, cargoFunc);
            funcionarioController.criarFuncionario(funcionario);
            return funcionario;

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite o tipo de dado correto.");
            sc.nextLine(); // Limpa o buffer do scanner
            return null;
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Erro na leitura dos dados: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            return null;
        }
    }

    public void deletarFuncionario(){
        System.out.println("Digite o id do funcionario: ");
        int id_func = sc.nextInt();
        funcionarioController.deletarFuncionarioPorId(id_func);
        System.out.println("Funcionario apagado com sucesso");
    }

    public Funcionario atualizarFuncionario() {
        try {
            System.out.println("Qual o id do funcionario?");
            int idFunc = sc.nextInt();
            sc.nextLine();

            System.out.println("Nome do Funcionario: ");
            String novoNome = sc.next();
            sc.nextLine();

            System.out.println("Novo cargo do funcionario (1-vendedor, 2-gerente): ");
            int novoCargo = sc.nextInt();
            sc.nextLine();

            Funcionario funcionario = new Funcionario(idFunc,novoNome,novoCargo);
            funcionarioController.atualizarFuncionario(funcionario);
            return funcionario;

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite o tipo de dado correto.");
            sc.nextLine();
            return null;
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Erro na leitura dos dados: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            return null;
        }
    }

    public void imprimirFuncionarios(){
        System.out.println("Lista de funcionarios: ");
        funcionarioController.imprimirFuncionarios();
    }
}
