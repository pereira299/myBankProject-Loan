package br.edu.utfpr.tsi.td.mybankprojectloan.models;

import br.edu.utfpr.tsi.td.mybankprojectloan.domains.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoanDAO implements ILoanDAO {
    private ArrayList<Loan> lista;

    public LoanDAO() {
        lista = new ArrayList<>();
    }

    public Loan criar(Loan loan) {
        lista.add(loan);
        return loan;
    }

    public void atualizar(int id, Loan loan) {
        lista.stream()
                .filter(l -> l.getId() == id)
                .forEach(l -> {
                    l.setLoanStatusId(loan.getLoanStatusId() > -1 ? loan.getLoanStatusId() : l.getLoanStatusId());
                });
    }

    public void remover(int id) {
        lista.removeIf(loan -> loan.getId() == id);
    }

    public ArrayList<Loan> buscar() {
        return lista;
    }

    public Loan buscar(int id) {
        return lista.stream().filter(loan -> loan.getId() == id).findFirst().orElse(null);
    }

    public List<Loan> buscarPorCliente(int id) {
        return lista.stream().filter(loan -> loan.getClientId() == id).collect(Collectors.toList());
    }
}
