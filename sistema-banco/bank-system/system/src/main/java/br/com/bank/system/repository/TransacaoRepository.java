package br.com.bank.system.repository;

import br.com.bank.system.model.Transacao;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends Neo4jRepository<Transacao, Long> {

    Transacao findByContaOrigemId(Long contaOrigemId);

    Transacao findByContaDestinoId(Long contaDestinoId);

    Transacao findByTipo(String tipo);

    Transacao findByValor(double valor);


}
