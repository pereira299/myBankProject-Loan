package br.edu.utfpr.tsi.td.mybankprojectloan.controllers;

import br.edu.utfpr.tsi.td.mybankprojectloan.domains.Loan;
import br.edu.utfpr.tsi.td.mybankprojectloan.domains.Parcel;
import br.edu.utfpr.tsi.td.mybankprojectloan.enums.*;
import br.edu.utfpr.tsi.td.mybankprojectloan.enums.LoanTaxEnum;
import br.edu.utfpr.tsi.td.mybankprojectloan.models.ILoanDAO;
import br.edu.utfpr.tsi.td.mybankprojectloan.models.LoanDAO;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class LoanController {
    private static int nextId = 1;
    private ILoanDAO loanDAO;
    public LoanController() {
        this.loanDAO = new LoanDAO();
    }

    public Loan criar(int clientId, float value, int parcels, Calendar startDate, int loanType) {
        if(parcels <= LoanConfigEnum.MIN_PARCEL.getValor() || parcels > LoanConfigEnum.MAX_PARCEL.getValor()) {
            throw new IllegalArgumentException("Número de parcelas inválido");
        }
        if(value < LoanConfigEnum.MIN_VALUE.getValor() || value > LoanConfigEnum.MAX_VALUE.getValor()) {
            throw new IllegalArgumentException("Valor do empréstimo inválido");
        }
        double totalValue = calculateTotalValueWithTax(value, parcels, loanType );
        if(totalValue/parcels < LoanConfigEnum.MIN_PARCEL_VALUE.getValor()) {
            throw new IllegalArgumentException("Valor da parcela inválido");
        }
        double parcelValue = totalValue/parcels;
        List<Parcel> parcelList = createParcelList(parcelValue, parcels, startDate, loanType);
        Loan loan = new Loan(nextId, value, clientId, loanType, LoanStatusEnum.PENDENTE.getValor(), parcelList);

        nextId++;
        loanDAO.criar(loan);
        return loan;
        //generate loan
    }

    public void atualizar(int id, Loan loan) {
        loanDAO.atualizar(id, loan);
    }

    public void remover(int id) {
        loanDAO.remover(id);
    }

    public Loan buscar(int id) {
        return loanDAO.buscar(id);
    }

    public List<Loan> buscarPorCliente(int id) {
        return loanDAO.buscarPorCliente(id);
    }

    public List<Loan> buscar() {
        return loanDAO.buscar();
    }

    public void pagarParcela(int id) {
        //verificar parcelas pendententes
        List<Parcel> parcels = this.getParcelasPendentes(id);
        if(parcels.size() == 0) {
            throw new IllegalArgumentException("Não há parcelas pendentes");
        }
        Parcel parcel = parcels.get(0);
        parcel.setParcelStatusId(ParcelStatusEnum.PAGO);
    }

    public void pagarParcela(int qtd, int id) {
        //verificar parcelas pendententes
        List<Parcel> parcels = this.getParcelasPendentes(id);
        if(parcels.size() == 0) {
            throw new IllegalArgumentException("Não há parcelas pendentes");
        } else if (qtd > parcels.size()) {
            throw new IllegalArgumentException("Quantidade de parcelas inválida");
        }
        for(int i = 0; i < qtd; i++) {
            Parcel parcel = parcels.get(i);
            parcel.setParcelStatusId(ParcelStatusEnum.PAGO);
        }
    }

    public void quitar(int id) {
        //verificar parcelas pendententes
        List<Parcel> parcels = this.getParcelasPendentes(id);
        if(parcels.size() == 0) {
            throw new IllegalArgumentException("Não há parcelas pendentes");
        }
        for(Parcel parcel : parcels) {
            parcel.setParcelStatusId(ParcelStatusEnum.PAGO);
        }
    }

    public List<Parcel> getParcelasPendentes(int id) {
        Loan loan = this.loanDAO.buscar(id);
        return loan.getParcels().stream()
                .filter(parcel -> parcel.getParcelStatusId() == ParcelStatusEnum.PENDENTE)
                .collect(Collectors.toList());
    }

    public List<Parcel> getParcelasPagas(int id) {
        Loan loan = this.loanDAO.buscar(id);
        return loan.getParcels().stream()
                .filter(parcel -> parcel.getParcelStatusId() == ParcelStatusEnum.PAGO)
                .collect(Collectors.toList());
    }

    public double getValorParcela(int id) {
        Loan loan = this.loanDAO.buscar(id);
        return loan.getParcels().get(0).getValue();
    }

    public double getValorTotal(int id) {
        Loan loan = this.loanDAO.buscar(id);
        return loan.getValue();
    }

    public double getValorPago(int id) {
        Loan loan = this.loanDAO.buscar(id);
        return loan.getParcels().stream()
                .filter(parcel -> parcel.getParcelStatusId() == ParcelStatusEnum.PAGO)
                .mapToDouble(Parcel::getValue)
                .sum();
    }

    public double getValorPendente(int id) {
        Loan loan = this.loanDAO.buscar(id);
        return loan.getParcels().stream()
                .filter(parcel -> parcel.getParcelStatusId() == ParcelStatusEnum.PENDENTE)
                .mapToDouble(Parcel::getValue)
                .sum();
    }


    private List<Parcel> createParcelList(double parcelValue, int parcels, Calendar startDate, double tax) {
        List<Parcel> parcelList = null;
        Calendar expirationDate = startDate;
        for(int i = 0; i < parcels; i++) {
            Parcel parcel = new Parcel(i, (float) parcelValue, tax, expirationDate, nextId, ParcelStatusEnum.PENDENTE);
            expirationDate.add(Calendar.MONTH, 1);
        }
        return parcelList;
    }
    private double calculateTotalValueWithTax(float value, int parcels, int loanType) {
        double tax = this.taxFromLoantype(loanType);
        return value * Math.pow(1+tax, parcels);
    }

    private double taxFromLoantype(int loanType) {
        switch (loanType) {
            case 1:
                return LoanTaxEnum.EMPRESTIMO_PESSOAL.ordinal();
            case 2:
                return LoanTaxEnum.EMPRESTIMO_IMOBILIARIO.ordinal();
            case 3:
                return LoanTaxEnum.EMPRESTIMO_CONSIGNADO.ordinal();
            default:
                return 0.0;
        }
    }

}
