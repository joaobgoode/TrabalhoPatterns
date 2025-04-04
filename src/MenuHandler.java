import java.util.Objects;
import java.util.Scanner;

public class MenuHandler {
    Funcionario user;
    UserService userService;
    Scanner sc = new Scanner(System.in);

    public MenuHandler(){
        handleStart();
        userService = new UserService(user);
        while (true) {
            menuHome();
        }
    }

    private void showStartMenu(){
        System.out.println("""
                ==========   MENU INICIAL   ==========
                
                [ 1 ] Funcionário
                [ 2 ] Gerente
                [ 3 ] Dono
                
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
            int opc = sc.nextInt();
            sc.nextLine();
            switch (opc) {
                case 1:
                    showMenuProduto();
                    return;
                case 2:
                    showMenuFuncionario();
                    return;
                case 3:
                    showMenuCliente();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida");
                    System.out.print("Opção: ");
            }
        }
    }


    private void showMenuFuncionario() {
        if (user instanceof Dono) {
            System.out.println(
                    """
                            ==========   MENU FUNCIONÁRIO   ==========
                
                            [ 1 ] Procurar Funcionário por ID
                            [ 2 ] Listar Funcionários
                            [ 3 ] Criar Funcionário
                            [ 4 ] Editar Funcionário
                            [ 5 ] Deletar Funcionário
                            [ 6 ] Listar Gerentes
                            [ 3 ] Criar Gerente
                            [ 4 ] Editar Gerente
                            [ 5 ] Deletar Gerente
                            
                            [ 0 ] Voltar""");
        } else if (user instanceof Gerente) {
            System.out.println(
                    """
                            ==========   MENU FUNCIONÁRIO   ==========
                            
                            [ 1 ] Procurar Funcionário por ID
                            [ 2 ] Listar Funcionários
                            [ 3 ] Criar Funcionário
                            [ 4 ] Editar Funcionário
                            [ 5 ] Deletar Funcionário
                            
                            [ 0 ] Voltar""");
        } else if (user != null){
            System.out.println(
                    """
                            ==========   MENU FUNCIONÁRIO   ==========
                            
                            [ 1 ] Procurar Funcionário por ID
                            [ 2 ] Listar Funcionários
                            
                            [ 0 ] Voltar""");
        }
    }

    private void showMenuCliente(){
        if (user instanceof Gerente) {
            System.out.println(
                    """
                            ==========   MENU CLIENTE   ==========
                            
                            [ 1 ] Procurar Cliente por CPF
                            [ 2 ] Listar Clientes
                            [ 3 ] Criar Cliente
                            [ 4 ] Editar Cliente
                            [ 5 ] Deletar Cliente
                            
                            [ 0 ] Voltar""");
        } else if (user != null){
            System.out.println(
                    """
                            ==========   MENU CLIENTE   ==========
                            
                            [ 1 ] Procurar Cliente por CPF
                            [ 2 ] Listar Clientes
                            [ 3 ] Criar Cliente
                            
                            [ 0 ] Voltar""");
        }
    }
    private void showMenuProduto(){
        if (user instanceof Gerente) {
            System.out.println(
                    """
                            ==========   MENU PRODUTO   ==========
                            
                            [ 1 ] Procurar Produto por ID
                            [ 2 ] Listar Produtos
                            [ 3 ] Criar Produto
                            [ 4 ] Editar Produto
                            [ 5 ] Deletar Produto
                            
                            [ 0 ] Voltar""");
        } else if (user != null){
            System.out.println(
                    """
                            ==========   MENU PRODUTO   ==========
                            
                            [ 1 ] Procurar Produto por ID
                            [ 2 ] Listar Produtos
                            
                            [ 0 ] Voltar""");
        }
    }

    public void handleStart(){
        while (true) {
            showStartMenu();
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                case 2:
                    user = getFuncionario();
                    if (user == null){
                        System.out.println("Usuário Inválido");
                        continue;
                    }
                    System.out.printf("Entrando como: %s\n", user.getNome());
                    return;
                case 3:
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
