package br.edu.utfpr.tsi.td.mybankprojectloan.enums;

public enum LoanConfigEnum {
    MAX_PARCEL(48),
    MIN_PARCEL(2),
    MAX_VALUE(1000000),
    MIN_VALUE(500),
    MIN_PARCEL_VALUE(100);

    private final int valor;

    LoanConfigEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
