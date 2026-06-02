package service;

import java.util.ArrayList;
import model.*;

public class Biblioteca {

    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;
    private int contadorEmprestimos;

    public Biblioteca() {
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();
        contadorEmprestimos = 1;
    }

    // CADASTRAR LIVRO
    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    // CADASTRAR USUÁRIO
    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // LISTAR LIVROS
    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (Livro livro : livros) {
            System.out.println("--------------------");
            System.out.println(livro);
        }
    }

    // LISTAR USUÁRIOS
    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario usuario : usuarios) {
            System.out.println("--------------------");
            System.out.println(usuario);
        }
    }

    // BUSCAR LIVRO POR ISBN
    public Livro buscarLivroPorISBN(String isbn) {
        for (Livro livro : livros) {
            if (livro.getIsbn().equalsIgnoreCase(isbn)) {
                return livro;
            }
        }
        return null;
    }

    // BUSCAR USUÁRIO POR MATRÍCULA
    public Usuario buscarUsuarioPorMatricula(String matricula) {
        for (Usuario usuario : usuarios) {
            if (usuario.getMatricula().equalsIgnoreCase(matricula)) {
                return usuario;
            }
        }
        return null;
    }

    // CONTAR EMPRÉSTIMOS ATIVOS
    private int contarEmprestimosAtivos(Usuario usuario) {
        int total = 0;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.isAtivo()
                    && emprestimo.getUsuario().getMatricula()
                    .equals(usuario.getMatricula())) {

                total++;
            }
        }

        return total;
    }

    // REALIZAR EMPRÉSTIMO
    public void realizarEmprestimo(String matricula, String isbn) {

        Usuario usuario = buscarUsuarioPorMatricula(matricula);
        Livro livro = buscarLivroPorISBN(isbn);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (!livro.isDisponivel()) {
            System.out.println("Livro indisponível.");
            return;
        }

        if (contarEmprestimosAtivos(usuario)
                >= usuario.getLimiteEmprestimos()) {

            System.out.println("Limite de empréstimos atingido.");
            return;
        }

        Emprestimo emprestimo =
                new Emprestimo(contadorEmprestimos++, usuario, livro);

        emprestimos.add(emprestimo);

        livro.setDisponivel(false);

        System.out.println("Empréstimo realizado com sucesso.");
    }

    // DEVOLVER LIVRO
    public void devolverLivro(int numeroEmprestimo) {

        for (Emprestimo emprestimo : emprestimos) {

            if (emprestimo.getNumero() == numeroEmprestimo
                    && emprestimo.isAtivo()) {

                emprestimo.finalizarEmprestimo();
                emprestimo.getLivro().setDisponivel(true);

                System.out.println("Livro devolvido com sucesso.");
                return;
            }
        }

        System.out.println("Empréstimo não encontrado.");
    }

    // LISTAR EMPRÉSTIMOS
    public void listarEmprestimos() {

        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }

        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("--------------------");
            System.out.println(emprestimo);
        }
    }
}