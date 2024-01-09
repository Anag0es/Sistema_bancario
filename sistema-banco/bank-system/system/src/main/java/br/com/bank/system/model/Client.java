package br.com.bank.system.model;

import br.com.bank.system.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Node("Client")
public class Client {

    @Setter
    @Getter
    @Id @GeneratedValue
    private Long id;
    // getters e setters
    @Setter
    @Getter
    private String nome;
    @Setter
    @Getter
    private String cpf;
    @Getter
    @Setter
    private String apelido;

    public static Client converter(ClientDTO clientDTO) {
        Client client = new Client();
        client.setNome(clientDTO.getNome());
        client.setCpf(clientDTO.getCpf());
        client.setId(clientDTO.getId());
        client.setApelido(clientDTO.getApelido());
        return client;
    }
}
