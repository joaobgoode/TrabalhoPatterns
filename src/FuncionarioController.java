public class FuncionarioController {
    public FuncionarioDBHandler dbHandler;

    public FuncionarioController(){
        dbHandler = FuncionarioDBHandler.getInstance();
    }

    public void criarFuncionario(Funcionario funcionario){
        dbHandler.criar(funcionario);
    }

    public void atualizarFuncionario(Funcionario funcionario){
        dbHandler.atualizar(funcionario);
    }

    public void imprimirFuncionarios(){
        dbHandler.imprimirFuncionarios();
    }

    public void deletarFuncionarioPorId(int id){
        dbHandler.remover(id);
    }

    public Funcionario buscarFuncionarioPorId(int id){
        return dbHandler.procurar(id);
    }
}