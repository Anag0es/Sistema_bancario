package br.com.bank.system.controller;

import br.com.bank.system.dto.ClientDTO;
import br.com.bank.system.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService service;

    // retorna todos os clientes
    @GetMapping("/clients")
    public List<ClientDTO> listAll(){
        return service.ListAll();
    }

    // retorna um cliente pelo seu id
    @GetMapping("/clients/{id}")
    public ClientDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    // salva um novo cliente
    @PostMapping("/clients")
    public ClientDTO save(@Valid @RequestBody ClientDTO clientDTO){
        return service.save(clientDTO);
    }

    // deleta um cliente pelo seu id
    @DeleteMapping("/clients/delete/{id}")
    public ClientDTO delete(@PathVariable Long id){
        return service.delete(id);
    }

    // edita um cliente pelo seu id
    @PutMapping("/clients/edit/{id}")
    public ClientDTO editClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        return service.editClient(id, clientDTO);
    }


}
