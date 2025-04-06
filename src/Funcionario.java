import java.util.Scanner;

public  class Funcionario {
    private int id;
    private String nome;
    private int cod_cargo;
    public boolean isGerente = false;
    public boolean isDono = false;
    public ProdutoController produtoController;
    public ClienteController clienteController;

    public Funcionario(int id, String nome, int cod_cargo) {
        this.id = id;
        this.nome = nome;
        this.cod_cargo = cod_cargo;
        produtoController = new ProdutoController();
        clienteController = new ClienteController();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCod_cargo() {
        return cod_cargo;
    }

    public void setCod_cargo(int cod_cargo) {
        this.cod_cargo = cod_cargo;
    }

    public String toCSV(){
        return id + ";" + nome + ";" + cod_cargo;
    }
}







