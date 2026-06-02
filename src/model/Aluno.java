package model;

public class Aluno extends Usuario {

    public Aluno(String nome, String matricula, String cpf, String email) {
        super(nome, matricula, cpf, email);
    }

    @Override
    public String getTipo() {
        return "Aluno";
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }
}