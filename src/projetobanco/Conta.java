/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobanco;

/**
 *
 * @author Pichau
 */
public abstract class Conta {

    protected String titular;
    protected double saldo;

    public Conta(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }

    public void depositar(double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("o valor deve ser maior que zero!");
        }
        saldo += valor;
    }

    public abstract void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException;

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}
