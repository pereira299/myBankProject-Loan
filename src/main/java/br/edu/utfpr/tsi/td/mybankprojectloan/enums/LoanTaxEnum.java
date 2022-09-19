package br.edu.utfpr.tsi.td.mybankprojectloan.enums;

public enum LoanTaxEnum {
    EMPRESTIMO_PESSOAL(0.08),
    EMPRESTIMO_IMOBILIARIO(0.06),
    EMPRESTIMO_CONSIGNADO(0.04);

    private final double valor;

    LoanTaxEnum(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
}
