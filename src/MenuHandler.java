import java.util.Objects;
import java.util.Scanner;

public class MenuHandler {
    Funcionario user;
    UserService userService;
    Scanner sc = new Scanner(System.in);
    int opc = 1;

    public MenuHandler(){
        handleStart();
        userService = new UserService(user);
        while (opc != 0) {
            handleHome();
        }
    }

    private void showStartMenu(){
        System.out.println("""
                ==========   MENU INICIAL   ==========
                
                [ 1 ] Funcionário
                [ 2 ] Dono
                
                [ 0 ] Sair
                
                Entrar como:
                """);
    }

    private void menuHome(){
        System.out.print("""
                ==========   HOME   ==========
                
                [ 1 ] Produto
                [ 2 ] Funcionário
                [ 3 ] Cliente
                
                [ 0 ] Sair
                
                Opção:
                """);
    }

    private void handleHome(){
        while (true) {
            if (opc != 0){
                menuHome();
                opc = sc.nextInt();
                sc.nextLine();
            }
            switch (opc) {
                case 1:
                    handleGerenciarProduto();
                    break;
                case 2:
                    handleGerenciarFuncionario();
                    break;
                case 3:
                    handleGerenciarCliente();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void showMenuGerenciarProduto(){
        if (user.isGerente) {
            System.out.println(
                    """
                            ==========   MENU PRODUTO   ==========
                            
                            [ 1 ] Procurar Produto por ID
                            [ 2 ] Listar Produtos
                            [ 3 ] Criar Produto
                            [ 4 ] Editar Produto
                            [ 5 ] Deletar Produto
                            
                            [ 0 ] Voltar""");
        } else {
            System.out.println(
                    """
                            ==========   MENU PRODUTO   ==========
                            
                            [ 1 ] Procurar Produto por ID
                            [ 2 ] Listar Produtos
                            
                            [ 0 ] Voltar""");
        }
    }


    private void handleGerenciarProduto(){
        while (true){
            showMenuGerenciarProduto();
            opc = sc.nextInt();
            sc.nextLine();
            if (user.isGerente) {
                switch (opc) {
                    case 1:
                        userService.buscarProdutoPorId();
                        break;
                    case 2:
                        userService.imprimirProdutos();
                        break;
                    case 3:
                        userService.addProduto();
                        break;
                    case 4:
                        userService.atualizarProduto();
                        break;
                    case 5:
                        userService.removerProdutoPorId();
                        break;
                    case 0:
                        opc = -1;
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        System.out.print("Opção: ");
                }
            }else {
                switch (opc) {
                    case 1:
                        userService.buscarProdutoPorId();
                        break;
                    case 2:
                        userService.imprimirProdutos();
                        break;
                    case 0:
                        opc = -1;
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        System.out.print("Opção: ");
                }
            }
        }
    }

    private void showMenuGerenciarFuncionario() {
       if (user.isGerente) {
            System.out.println(
                    """
                            ==========   MENU FUNCIONÁRIO   ==========
                            
                            [ 1 ] Procurar Funcionário por ID
                            [ 2 ] Listar Funcionários
                            [ 3 ] Criar Funcionário
                            [ 4 ] Editar Funcionário
                            [ 5 ] Deletar Funcionário
                            
                            [ 0 ] Voltar""");
        } else {
            System.out.println(
                    """
                            ==========   MENU FUNCIONÁRIO   ==========
                            
                            [ 1 ] Procurar Funcionário por ID
                            [ 2 ] Listar Funcionários
                            
                            [ 0 ] Voltar""");
        }
    }


    private void handleGerenciarFuncionario(){
        while (true){
            showMenuGerenciarFuncionario();
            opc = sc.nextInt();
            sc.nextLine();
            if (user.isGerente) {
                    switch (opc) {
                        case 1:
                            userService.procurarFuncionarioPorID();
                            break;
                        case 2:
                            userService.imprimirFuncionarios();
                            break;
                        case 3:
                            userService.cadastrarFuncionario();
                            break;
                        case 4:
                            userService.atualizarFuncionario();
                            break;
                        case 5:
                            userService.deletarFuncionario();
                            break;
                        case 0:
                            opc = -1;
                            return;
                        default:
                            System.out.println("Opção inválida!");
                            System.out.print("Opção: ");
                    }
            }else {
                switch (opc) {
                    case 1:
                        userService.procurarFuncionarioPorID();
                        break;
                    case 2:
                        userService.imprimirFuncionarios();
                        break;
                    case 0:
                        opc = -1;
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        System.out.print("Opção: ");
                }
            }
        }
    }

    private void showMenuGerenciarCliente(){
        if (user.isGerente) {
            System.out.println(
                    """
                            ==========   MENU CLIENTE   ==========
                            
                            [ 1 ] Procurar Cliente por CPF
                            [ 2 ] Listar Clientes
                            [ 3 ] Criar Cliente
                            [ 4 ] Editar Cliente
                            [ 5 ] Deletar Cliente
                            
                            [ 0 ] Voltar""");
        } else {
            System.out.println(
                    """
                            ==========   MENU CLIENTE   ==========
                            
                            [ 1 ] Procurar Cliente por CPF
                            [ 2 ] Listar Clientes
                            [ 3 ] Criar Cliente
                            
                            [ 0 ] Voltar""");
        }
    }
    private void handleGerenciarCliente(){
        while (true){
            showMenuGerenciarCliente();
            opc = sc.nextInt();
            sc.nextLine();
            if (user.isGerente) {
                switch (opc) {
                    case 1:
                        userService.procurarClientePorCPF();
                        break;
                    case 2:
                        userService.imprimirClientes();
                        break;
                    case 3:
                        userService.addCliente();
                        break;
                    case 4:
                        userService.atualizarCliente();
                        break;
                    case 5:
                        userService.removerCliente();
                        break;
                    case 0:
                        opc = -1;
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        System.out.print("Opção: ");
                }
            }else {
                switch (opc) {
                    case 1:
                        userService.procurarClientePorCPF();
                        break;
                    case 2:
                        userService.imprimirClientes();
                        break;
                    case 0:
                        opc = -1;
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        System.out.print("Opção: ");
                }
            }
        }
    }

    public void handleStart(){
        while (true) {
            showStartMenu();
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    user = getFuncionario();
                    if (user == null){
                        System.out.println("Usuário Inválido");
                        continue;
                    }
                    System.out.printf("Entrando como: %s\n", user.getNome());
                    return;
                case 2:
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    if (!Objects.equals(senha, "123")){
                        System.out.println("Senha inválida");
                        break;
                    }
                    System.out.println("Entrando como dono");
                    user = new Dono();
                    return;
                case 0:
                    System.exit(0);
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public Funcionario getFuncionario(){
        FuncionarioDBHandler dbHandler = FuncionarioDBHandler.getInstance();
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        return dbHandler.procurar(id);
    }
}
