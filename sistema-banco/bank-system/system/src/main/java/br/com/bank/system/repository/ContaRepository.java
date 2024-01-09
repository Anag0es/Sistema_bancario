package br.com.bank.system.repository;

import br.com.bank.system.model.Conta;
import br.com.bank.system.model.TipoConta;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends Neo4jRepository<Conta, Long> {

    // buscar conta por numero
    Conta findByNumero(int numeroDaConta);

    List<Conta> findByClienteId(Long clienteId);

    // busca contas por tipo
    List<Conta> findByTipoConta(TipoConta tipoConta);

    // busca contas com saldo maior que um valor
    List<Conta> findBySaldoGreaterThan(double saldo);

    // busca contas com transacoes em um intervalo de datas
    List<Conta> findByTransacoes_Data(String dataInicio, String dataFim);
}
