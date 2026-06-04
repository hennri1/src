package main;

import model.*;
import service.Biblioteca;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        int opcao;

        do {

            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Cadastrar Usuário");
            System.out.println("3 - Listar Livros");
            System.out.println("4 - Listar Usuários");
            System.out.println("5 - Realizar Empréstimo");
            System.out.println("6 - Realizar Devolução");
            System.out.println("7 - Listar Empréstimos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            // CORREÇÃO 4: trata entrada inválida no menu principal
            opcao = lerInteiro(sc);

            switch (opcao) {

                case 1:
                    // CORREÇÃO 5: apenas Professor pode cadastrar livro
                    System.out.print("Matrícula do professor responsável: ");
                    String matProf = sc.nextLine();
                    Usuario responsavel = biblioteca.buscarUsuarioPorMatricula(matProf);

                    if (responsavel == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }
                    if (!(responsavel instanceof Professor)) {
                        System.out.println("Apenas professores podem cadastrar livros.");
                        break;
                    }

                    System.out.print("Título: ");
                    String titulo = sc.nextLine();

                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Ano: ");
                    int ano = lerInteiro(sc);

                    System.out.print("Editora: ");
                    String editora = sc.nextLine();

                    Livro livro = new Livro(titulo, autor, isbn, ano, editora);
                    biblioteca.cadastrarLivro(livro);
                    break;

                case 2:
                    System.out.println("1 - Aluno");
                    System.out.println("2 - Professor");
                    System.out.print("Tipo: ");

                    int tipo = lerInteiro(sc);

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Matrícula: ");
                    String matricula = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("E-mail: ");
                    String email = sc.nextLine();

                    Usuario usuario;

                    if (tipo == 1) {
                        usuario = new Aluno(nome, matricula, cpf, email);
                    } else if (tipo == 2) {
                        usuario = new Professor(nome, matricula, cpf, email);
                    } else {
                        System.out.println("Tipo inválido. Escolha 1 (Aluno) ou 2 (Professor).");
                        break;
                    }

                    biblioteca.cadastrarUsuario(usuario);
                    break;

                case 3:
                    biblioteca.listarLivros();
                    break;

                case 4:
                    biblioteca.listarUsuarios();
                    break;

                case 5:
                    System.out.print("Matrícula do usuário: ");
                    String matEmprestimo = sc.nextLine();

                    System.out.print("ISBN do livro: ");
                    String isbnEmprestimo = sc.nextLine();

                    biblioteca.realizarEmprestimo(matEmprestimo, isbnEmprestimo);
                    break;

                case 6:
                    System.out.print("Número do empréstimo: ");
                    int numero = lerInteiro(sc);

                    biblioteca.devolverLivro(numero);
                    break;

                case 7:
                    biblioteca.listarEmprestimos();
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida. Digite um número entre 0 e 7.");
            }

        } while (opcao != 0);

        sc.close();
    }

    // CORREÇÃO 4: lê inteiro com tratamento de InputMismatchException
    private static int lerInteiro(Scanner sc) {
        while (true) {
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // limpa o buffer
                return valor;
            } catch (InputMismatchException e) {
                sc.nextLine(); // descarta a entrada inválida
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }
}
