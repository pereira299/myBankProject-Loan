package br.edu.utfpr.tsi.td.mybankprojectloan.enums;

public enum ParcelStatusEnum {
    PENDENTE(1),
    PAGO(2),
    VENCIDO(3);

    private final int valor;

    ParcelStatusEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
