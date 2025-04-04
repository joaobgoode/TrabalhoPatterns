public class ClienteController {

    public ClienteDBHandler clienteDbHandler;

    public ClienteController(){
        clienteDbHandler = ClienteDBHandler.getInstance();
    }
    public void addCliente(Cliente cliente){
        clienteDbHandler.criar(cliente);
    }
    public void listarClientes(){
        clienteDbHandler.imprimirClientes();
    }
    public void removerClientePorCpf(String cpf){
        clienteDbHandler.remover(cpf);
    }
    public void atualizarCliente(Cliente cliente){clienteDbHandler.atualizar(cliente);}
    public void imprimirClientes(){clienteDbHandler.imprimirClientes();}

}
