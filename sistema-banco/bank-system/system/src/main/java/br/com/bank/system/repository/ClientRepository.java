package br.com.bank.system.repository;

import br.com.bank.system.model.Client;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends Neo4jRepository<Client, Long> {
}
