package br.com.bank.system.repository;

import br.com.bank.system.model.Transacao;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends Neo4jRepository<Transacao, Long> {

    List<Transacao> findByContaOrigemId(Long contaOrigemId);

   List<Transacao> findByContaDestinoId(Long contaDestinoId);

    List<Transacao> findByTipo(String tipo);

    List<Transacao> findByValor(double valor);


}
