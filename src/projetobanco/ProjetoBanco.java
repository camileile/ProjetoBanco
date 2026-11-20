/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetobanco;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Pichau
 */
public class ProjetoBanco {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Conta> contas = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n===== banco =====");
            System.out.println("1 - criar conta");
            System.out.println("2 - depositar");
            System.out.println("3 - sacar");
            System.out.println("4 - ver contas");
            System.out.println("0 - sair");
            opcao = lerInt("escolha: ");

            switch (opcao) {
                case 1: criarConta(); break;
                case 2: depositar(); break;
                case 3: sacar(); break;
                case 4: listarContas(); break;
                case 0: System.out.println("saindo..."); break;
                default: System.out.println("opção inválida!");
            }
        } while (opcao != 0);
    }

   
    static int lerInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int n = sc.nextInt();
                sc.nextLine(); 
                return n;
            } catch (InputMismatchException e) {
                System.out.println("digite apenas números!");
                sc.nextLine(); 
            }
        }
    }

 
    static double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                double n = sc.nextDouble();
                sc.nextLine();
                return n;
            } catch (InputMismatchException e) {
                System.out.println("valor inválido! digite um número (use vírgula nos centavos).");
                sc.nextLine();
            }
        }
    }

    static void criarConta() {
        System.out.print("nome do titular: ");
        String nome = sc.nextLine();
        if (nome.isBlank()) {
            System.out.println("o nome não pode ficar vazio!");
            return;
        }

        int tipo = lerInt("tipo (1 - corrente | 2 - poupança): ");
        if (tipo == 1) {
            contas.add(new ContaCorrente(nome));
        } else if (tipo == 2) {
            contas.add(new ContaPoupanca(nome));
        } else {
            System.out.println("tipo inválido!");
            return;
        }
        System.out.println("conta criada! número da conta: " + contas.size());
    }

   
    static Conta escolherConta() {
        if (contas.isEmpty()) {
            System.out.println("nenhuma conta cadastrada!");
            return null;
        }
        listarContas();
        int num = lerInt("número da conta: ");
        if (num < 1 || num > contas.size()) {
            System.out.println("conta não encontrada!");
            return null;
        }
        return contas.get(num - 1);
    }

    static void depositar() {
        Conta c = escolherConta();
        if (c == null) return;

        double valor = lerDouble("valor do depósito: ");
        try {
            c.depositar(valor);
            System.out.println("depósito realizado! saldo: R$ " + c.getSaldo());
        } catch (ValorInvalidoException e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    static void sacar() {
        Conta c = escolherConta();
        if (c == null) return;

        double valor = lerDouble("valor do saque: ");
        try {
            c.sacar(valor);
            System.out.println("saque realizado! saldo: R$ " + c.getSaldo());
        } catch (SaldoInsuficienteException e) {
            System.out.println("erro: " + e.getMessage());
        } catch (ValorInvalidoException e) {
            System.out.println("erro: " + e.getMessage());
        }
    }

    static void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("nenhuma conta cadastrada!");
            return;
        }
        for (int i = 0; i < contas.size(); i++) {
            Conta c = contas.get(i);
            String tipo = (c instanceof ContaCorrente) ? "corrente" : "poupança";
            System.out.print((i + 1) + " - " + c.getTitular() + " | " + tipo + " | saldo: R$ " + c.getSaldo());

            if (c instanceof Tributavel) {
                System.out.print(" | imposto: R$ " + ((Tributavel) c).calcularImposto());
            }
            System.out.println();
        }
    }
}
