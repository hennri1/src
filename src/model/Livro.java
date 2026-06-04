package model;

public class Livro {

    private String titulo;
    private String autor;
    private String isbn;
    private int ano;
    private String editora;
    private boolean disponivel;

    public Livro(String titulo, String autor, String isbn, int ano, String editora) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.editora = editora;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAno() {
        return ano;
    }

    public String getEditora() {
        return editora;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn +
                "\nTítulo: " + titulo +
                "\nAutor: " + autor +
                "\nAno: " + ano +
                "\nEditora: " + editora +
                "\nStatus: " + (disponivel ? "Disponível" : "Emprestado");
    }
}