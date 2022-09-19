package br.edu.utfpr.tsi.td.mybankprojectloan.models;

import br.edu.utfpr.tsi.td.mybankprojectloan.domains.Loan;

import java.util.List;

public interface ILoanDAO {
    Loan criar(Loan loan);
    void atualizar(int id, Loan loan);
    void remover(int id);
    Loan buscar(int id);
    List<Loan> buscarPorCliente(int id);
    List<Loan> buscar();
}
