package br.com.bank.system.service;

import br.com.bank.system.dto.ClientDTO;
import br.com.bank.system.model.Client;
import br.com.bank.system.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    // lista todos os clientes
    public List<ClientDTO> ListAll() {
        List<Client> clients = repository.findAll();
        return clients.stream().map(ClientDTO::convert)
                .collect(Collectors.toList());
    }

    // lista um cliente pelo seu id
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        return ClientDTO.convert(client);
    }

    // salva um novo cliente
    public ClientDTO save(ClientDTO clientDTO){
        Client client = Client.converter(clientDTO);
        client = repository.save(client);
        return ClientDTO.convert(client);
    }

    // deleta um cliente pelo seu id
    public ClientDTO delete(long id){
        Client client = repository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        repository.delete(client);
        return ClientDTO.convert(client);
    }

    // edita um cliente pelo seu id
    public ClientDTO editClient(Long id, ClientDTO clientDTO){
        Client client = repository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        if(clientDTO.getNome() != null && !clientDTO.getNome().isEmpty()){
            client.setNome(clientDTO.getNome());
        }
        if(clientDTO.getCpf() != null && !clientDTO.getCpf().isEmpty()){
            client.setCpf(clientDTO.getCpf());
        }
        if (clientDTO.getApelido() != null && !clientDTO.getApelido().isEmpty()){
            client.setApelido(clientDTO.getApelido());
        }
        return ClientDTO.convert(repository.save(client));
    }





}
