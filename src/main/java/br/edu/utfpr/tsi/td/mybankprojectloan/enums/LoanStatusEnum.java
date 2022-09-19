package br.edu.utfpr.tsi.td.mybankprojectloan.enums;

public enum LoanStatusEnum {
    PENDENTE(1),
    APROVADO(2),
    CANCELADO(0);

    private final int valor;

    LoanStatusEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
