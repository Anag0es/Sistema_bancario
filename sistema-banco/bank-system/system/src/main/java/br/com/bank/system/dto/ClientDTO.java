package br.com.bank.system.dto;

import br.com.bank.system.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
        if (client == null) {
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setCpf(client.getCpf());
        clientDTO.setNome(client.getNome());
        clientDTO.setId(client.getId());
        clientDTO.setApelido(client.getApelido());
        return clientDTO;
    }


}
