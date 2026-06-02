package model;

public class Professor extends Usuario {

    public Professor(String nome, String matricula, String cpf, String email) {
        super(nome, matricula, cpf, email);
    }

    @Override
    public String getTipo() {
        return "Professor";
    }

    @Override
    public int getLimiteEmprestimos() {
        return 5;
    }
}