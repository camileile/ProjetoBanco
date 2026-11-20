/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobanco;

/**
 *
 * @author Pichau
 */
public class ContaCorrente extends Conta implements Tributavel {

    private final double taxa = 2.0; 

    public ContaCorrente(String titular) {
        super(titular);
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("o valor deve ser maior que zero!");
        }
        if (valor + taxa > saldo) {
            throw new SaldoInsuficienteException("saldo insuficiente! (taxa de R$ " + taxa + ")");
        }
        saldo -= valor + taxa;
    }

    @Override
    public double calcularImposto() {
        return saldo * 0.01; 
    }
}
