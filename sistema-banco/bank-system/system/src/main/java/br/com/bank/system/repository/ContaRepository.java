package br.com.bank.system.repository;

import br.com.bank.system.model.Conta;
import br.com.bank.system.model.TipoConta;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends Neo4jRepository<Conta, Long> {

    // buscar conta por numero
    Conta findByNumero(int numero);

    // buscar pelo id do cliente recuperando suas contas
    List<Conta> findByClienteId(Long clienteId);

    // busca contas por tipo
    @Query("MATCH (c:Conta)-[:TITULAR]->(client:Client) WHERE c.tipoConta = $tipoConta RETURN c, client")
    List<Conta> findByByTipoConta(@Param("tipoConta") TipoConta tipoConta);

    // busca contas com saldo maior que um valor
    List<Conta> findBySaldoGreaterThan(double saldo);

    // busca contas com transacoes em um intervalo de datas
    List<Conta> findByTransacoes_Data(String dataInicio, String dataFim);
}
