package model;

import java.time.LocalDate;

public class Emprestimo {

    private int numero;
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private boolean ativo;

    public Emprestimo(int numero, Usuario usuario, Livro livro) {
        this.numero = numero;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();
        this.ativo = true;
    }

    public int getNumero() {
        return numero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void finalizarEmprestimo() {
        this.ativo = false;
    }

    @Override
    public String toString() {
        return "Empréstimo Nº: " + numero +
                "\nUsuário: " + usuario.getNome() +
                "\nLivro: " + livro.getTitulo() +
                "\nData: " + dataEmprestimo +
                "\nStatus: " + (ativo ? "Ativo" : "Devolvido");
    }
}