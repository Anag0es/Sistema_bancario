package br.com.bank.system.dto;

import br.com.bank.system.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;
    private String cpf;
    private String nome;
    private String apelido;

    // convert
    public static ClientDTO convert(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setCpf(client.getCpf());
        clientDTO.setNome(client.getNome());
        clientDTO.setId(client.getId());
        clientDTO.setApelido(client.getApelido());
        return clientDTO;
    }


}
