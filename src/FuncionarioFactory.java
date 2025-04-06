public class FuncionarioFactory {
    public final int COD_VENDEDOR = 1;
    public final int COD_GERENTE = 2;

    public Funcionario criarFuncionario(int id, String nome,int cod) throws ClassNotFoundException {
        switch (cod){
            case COD_VENDEDOR:
                return new Funcionario(id, nome, cod);
            case COD_GERENTE:
                return new Gerente(id,nome, cod);
            default:
                throw new ClassNotFoundException("Código Inválido");
        }
    }
}