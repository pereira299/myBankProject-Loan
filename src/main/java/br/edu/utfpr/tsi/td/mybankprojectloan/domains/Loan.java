package br.edu.utfpr.tsi.td.mybankprojectloan.domains;

import br.edu.utfpr.tsi.td.mybankprojectloan.enums.LoanStatusEnum;
import br.edu.utfpr.tsi.td.mybankprojectloan.enums.LoanTypeEnum;

import java.util.List;

public class Loan {
    private int id;
    private float value;
    private int clientId;
    private int loanType;
    private int loanStatusId;
    private List<Parcel> parcels;

    public Loan(int id, float value, int clientId, int loanType, int loanStatusId, List<Parcel> parcels) {
        this.id = id;
        this.value = value;
        this.clientId = clientId;
        this.loanType = loanType;
        this.loanStatusId = loanStatusId;
        this.parcels = parcels;
    }

    public int getId() {return id;}
    public float getValue() {return value;}
    public int getClientId() {return clientId;}
    public int getLoanType() {return loanType;}
    public int getLoanStatusId() {return loanStatusId;}
    public List<Parcel> getParcels() {return parcels;}

    public void setLoanStatusId(int loanStatusId) {this.loanStatusId = loanStatusId;}
}
