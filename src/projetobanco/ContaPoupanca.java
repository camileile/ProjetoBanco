/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobanco;

/**
 *
 * @author Pichau
 */
public class ContaPoupanca extends Conta {

    public ContaPoupanca(String titular) {
        super(titular);
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("o valor deve ser maior que zero!");
        }
        if (valor > saldo) {
            throw new SaldoInsuficienteException("saldo insuficiente!");
        }
        saldo -= valor;
    }
}
