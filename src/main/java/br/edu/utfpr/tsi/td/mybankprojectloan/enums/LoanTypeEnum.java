package br.edu.utfpr.tsi.td.mybankprojectloan.enums;

public enum LoanTypeEnum {
    EMPRESTIMO_PESSOAL(1),
    EMPRESTIMO_IMOBILIARIO(2),
    EMPRESTIMO_CONSIGNADO(3);

    private final int valor;

    LoanTypeEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
