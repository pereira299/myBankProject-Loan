package br.edu.utfpr.tsi.td.mybankprojectloan.domains;

import br.edu.utfpr.tsi.td.mybankprojectloan.enums.ParcelStatusEnum;

import java.util.Calendar;

public class Parcel {
    private int id;
    private float value;
    private double tax;
    private Calendar expirationDate;
    private int loanId;
    private int parcelStatusId;

    public Parcel(int id, float value, double tax, Calendar expirationDate, int loanId, int parcelStatusId) {
        this.id = id;
        this.value = value;
        this.tax = tax;
        this.expirationDate = expirationDate;
        this.loanId = loanId;
        this.parcelStatusId = parcelStatusId;
    }

    public int getId() {return id;}
    public float getValue() {return value;}
    public double getTax() {return tax;}
    public Calendar getExpirationDate() {return expirationDate;}
    public int getLoanId() {return loanId;}
    public int getParcelStatusId() {return parcelStatusId;}

    public void setParcelStatusId(int parcelStatusId) {this.parcelStatusId = parcelStatusId;}
}
