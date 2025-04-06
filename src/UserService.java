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

    public void procurarClientePorCPF(){
        System.out.print("CPF do Cliente: ");
        String cpf = sc.nextLine();
        Cliente cliente = clienteController.procurarClientePorCpf(cpf);
        if(cliente != null){
            System.out.println("Funcionario encontrado: ");
            System.out.printf("""
                   CPF: %s;
                   Nome: %s;
                   Data de Nascimento: %s;
                   Telefone: %s;
                   """, cliente.getCpf(), cliente.getNome(), cliente.getDataNascimento(), cliente.getTelefone());
            return;
        }
        System.out.println("Cliente não encontrado");
    }

    public void addCliente(){

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

    }


    public void removerCliente(){
        System.out.println("Qual o CPF do cliente que você quer deletar? ");
        String cpf = sc.nextLine();

        clienteController.removerClientePorCpf(cpf);
        System.out.println("Cliente deletado");
    }

    public void atualizarCliente(){

        System.out.println("Qual o CPF do cliente que você quer atualizar?");
        String cpf = sc.nextLine();

        Cliente cliente = clienteController.procurarClientePorCpf(cpf);


        System.out.printf("Nome [ %s ]: ", cliente.getNome());
        String nome = sc.nextLine();
        if (nome.isEmpty()){
            nome = cliente.getNome();
        }
        System.out.printf("Data de Nascimento [ %s ]: ", cliente.getDataNascimento());
        String dataNascimento = sc.nextLine();
        if (dataNascimento.isEmpty()){
            dataNascimento = cliente.getDataNascimento();
        }

        System.out.printf("Telefone [ %s ]: ", cliente.getTelefone());
        String telefone = sc.nextLine();
        if (telefone.isEmpty()){
            telefone = cliente.getTelefone();
        }
        ClienteBuilder clienteBuilder = new ClienteBuilder();
        clienteBuilder.setNome(nome);
        clienteBuilder.setCpf(cpf);
        clienteBuilder.setDataNascimento(dataNascimento);
        clienteBuilder.setTelefone(telefone);
        clienteController.atualizarCliente(clienteBuilder.criar());
        System.out.println("Cliente atualizado");
        System.out.printf(
                """
                Novos dados:
                Nome: %s;
                CPF: %s;
                Data de Nascimento: %s;
                Telefone: %s;
                """, nome, cpf, dataNascimento, telefone);
    }

    public void imprimirClientes(){
        clienteController.imprimirClientes();
    }



    public void addProduto(){
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
    }



    public void atualizarProduto(){

        System.out.println("Qual o id do produto que você quer atualizar?");
        int id = sc.nextInt();
        sc.nextLine();
        Produto produto = produtoController.buscarProdutoPorid(id);

        if (produto == null){
            System.out.println("Produto não encontrado");
            return;
        }

        System.out.printf("Tipo [ %s ]: ", produto.getTipo());
        String tipo = sc.nextLine();
        if (tipo.isEmpty()){
            tipo = produto.getTipo();
        }

        System.out.printf("Nome [ %s ]: ", produto.getNome());
        String nome = sc.nextLine();
        if (nome.isEmpty()){
            nome = produto.getNome();
        }

        System.out.printf("Descrição [ %s ]: ", produto.getDescricao());
        String descricao = sc.nextLine();
        if (descricao.isEmpty()){
            descricao = produto.getDescricao();
        }

        System.out.printf("Preço [ %f ] :", produto.getPreco());
        String precoInput = sc.nextLine();
        double preco;
        if (precoInput.isEmpty()){
            preco = produto.getPreco();
        } else {
            preco = Double.parseDouble(precoInput);
        }

        System.out.printf("Quantidade [ %d ]: ", produto.getQuantidade());
        String qtdInput = sc.nextLine();
        int qtd;
        if (qtdInput.isEmpty()){
            qtd = produto.getQuantidade();
        } else {
            qtd = Integer.parseInt(qtdInput);
        }

        ProdutoBuilder produtoBuilder = new ProdutoBuilder();
        produtoBuilder.setId(id);
        produtoBuilder.setNome(nome);
        produtoBuilder.setDescricao(descricao);
        produtoBuilder.setTipo(tipo);
        produtoBuilder.setPreco(preco);
        produtoBuilder.setQuantidade(qtd);
        produtoController.atualizarProduto(produtoBuilder.criar());
        System.out.println("Produto atualizado");

    }
    public void buscarProdutoPorId(){
        System.out.println("Id do produto a procurar: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        Produto produto = produtoController.buscarProdutoPorid(id);
        if (produto == null){
            System.out.println("Produto não encontrado");
            return;
        }
        System.out.printf("""
                Produto Encontrado:
                ID: %d;
                Tipo: %s;
                Nome: %s;
                Descricao: %s;
                Preco: %s;
                Quantidade: %s;
                """, produto.getId(), produto.getTipo(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidade());
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

    public void cadastrarFuncionario() {
        try {

            System.out.println("Cargo do Funcionario (1-Vendedor, 2-Gerente): ");
            int codCargo = sc.nextInt();
            sc.nextLine();

            if (codCargo == 2 && !(user.isDono)){
                System.out.println("Você não tem permissão para criar gerentes, procurar o dono");
                return;
            }

            System.out.println("Nome do Funcionario: ");
            String nome = sc.nextLine();

            System.out.println("id do Funcionario");
            int id = sc.nextInt();
            sc.nextLine();

            Funcionario funcionario = funcionarioFactory.criarFuncionario(id, nome, codCargo);
            funcionarioController.criarFuncionario(funcionario);
            System.out.printf(
                    """
                    Novos dados:
                    ID: %d;
                    Nome: %s;
                    Cargo: %s;
                    """, id, nome, (codCargo==1?"Vendedor":"Gerente"));

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite o tipo de dado correto.");
            cadastrarFuncionario();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Erro na leitura dos dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }


    public void procurarFuncionarioPorID(){
        System.out.print("Id do Funcionario: ");
        int id = sc.nextInt();
        sc.nextLine();
        Funcionario funcionario = funcionarioController.buscarFuncionarioPorId(id);
        if(funcionario != null){
            System.out.println("Funcionario encontrado: ");
            System.out.printf("""
                   ID: %d;
                   Nome: %s;
                   Cargo: %s;
                   """, funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getCod_cargo() == 1?"Vendedor":"Gerente");
            return;
        }
        System.out.println("Funcionário não encontrado");
    }

    public void deletarFuncionario(){
        System.out.println("Digite o id do funcionario: ");
        int id_func = sc.nextInt();
        sc.nextLine();
        funcionarioController.deletarFuncionarioPorId(id_func);
        System.out.println("Funcionario apagado com sucesso");
    }

    public void atualizarFuncionario() {
        try {
            System.out.println("Qual o id do funcionario?");
            int idFunc = sc.nextInt();
            sc.nextLine();
            Funcionario funcionario = funcionarioController.buscarFuncionarioPorId(idFunc);
            if (funcionario == null){
                System.out.println("Funcionário não existe");
                return;
            }
            if (funcionario.getCod_cargo() == 2 && !(user.isDono)){
                System.out.println("Você não pode editar este funcionário");
                return;
            }
            System.out.printf("Nome do funcionario [%s]: ", funcionario.getNome());
            String nome = sc.nextLine();
            if (nome.isEmpty()){
                nome = funcionario.getNome();
            }
            int cod = funcionario.getCod_cargo();
            if (user.isDono) {
                System.out.printf("Cargo do funcionario (1-Vendedor, 2-Gerente) [%d]: ", funcionario.getCod_cargo());
                String cod_entrada = sc.nextLine();

                if (!(cod_entrada.isEmpty())) {
                    cod = Integer.parseInt(cod_entrada);
                }
            }

            System.out.printf(
                    """
                    Novos dados:
                    ID: %d;
                    Nome: %s;
                    Cargo: %s;
                    """, idFunc, nome, (cod==1?"Vendedor":"Gerente"));
            funcionario.setNome(nome);
            funcionario.setCod_cargo(cod);
            funcionarioController.atualizarFuncionario(funcionario);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite o tipo de dado correto.");
            atualizarFuncionario();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Erro na leitura dos dados: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public void imprimirFuncionarios(){
        System.out.println("Lista de funcionarios: ");
        funcionarioController.imprimirFuncionarios();
    }
}
