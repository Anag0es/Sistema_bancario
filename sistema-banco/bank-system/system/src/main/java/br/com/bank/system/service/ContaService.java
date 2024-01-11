package br.com.bank.system.service;


import br.com.bank.system.dto.ClientDTO;
import br.com.bank.system.dto.ContaDTO;
import br.com.bank.system.model.Client;
import br.com.bank.system.model.Conta;
import br.com.bank.system.model.TipoConta;
import br.com.bank.system.repository.ContaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository repository;
    @Autowired
    private ClientService clientService;


    // lista todas as contas
    public List<ContaDTO> ListAll(){
        List<Conta> contas = repository.findAll();
        return contas.stream().map(ContaDTO::convert)
                .collect(Collectors.toList());
    }

    // lista uma conta pelo id do cliente
    public List<ContaDTO> findByClienteId(Long clienteId){
        List<Conta> contas = repository.findByClienteId(clienteId);
        return contas.stream().map(ContaDTO::convert)
                .collect(Collectors.toList());
    }

    // lista uma conta pelo seu numero
    public ContaDTO findByNumero(int numeroDaConta){
        Conta conta = repository.findByNumero(numeroDaConta);
        return ContaDTO.convert(conta);
    }

    // lista contas por tipo
    public List<ContaDTO> findByTipoConta(String tipoConta){
        List<Conta> contas = repository.findByByTipoConta(TipoConta.valueOf(tipoConta));
        return contas.stream().map(ContaDTO::convert)
                .collect(Collectors.toList());
    }

    // lista contas com saldo maior que um valor
    public List<ContaDTO> findBySaldoGreaterThan(double saldo){
        List<Conta> contas = repository.findBySaldoGreaterThan(saldo);
        return contas.stream().map(ContaDTO::convert)
                .collect(Collectors.toList());
    }

    // lista contas com transacoes em um intervalo de datas
    public List<ContaDTO> findByTransacoes_Data(String dataInicio, String dataFim){
        List<Conta> contas = repository.findByTransacoes_Data(dataInicio, dataFim);
        return contas.stream().map(ContaDTO::convert)
                .collect(Collectors.toList());
    }

    // salva uma nova conta
    @Transactional
    public ContaDTO save(ContaDTO contaDTO){
        if (contaDTO == null || contaDTO.getClienteId() == null) {
            throw new IllegalArgumentException("ContaDTO e/ou ClienteId não podem ser nulos");
        }
        ClientDTO cliente = clientService.findById(contaDTO.getClienteId());
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado com o ID: " + contaDTO.getClienteId());
        }
        Conta conta = new Conta();
        conta.setTitular(Client.converter(cliente));
        conta.setTipoConta(TipoConta.valueOf(contaDTO.getTipoConta()));
        conta.setSaldo(contaDTO.getSaldo());
        conta.getDataAbertura();
        conta = repository.save(conta);
        return ContaDTO.convert(conta);
    }

    // deleta uma conta pelo numeroDaConta
    public ContaDTO delete(int numeroDaConta) {
        Conta conta = repository.findByNumero(numeroDaConta);
        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada com o numero: " + numeroDaConta);
        }
        repository.delete(conta);
        return ContaDTO.convert(conta);
    }


    // edita uma conta pelo seu id
    public ContaDTO editConta(Long id, ContaDTO dto){
        Conta conta = repository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if(dto.getSaldo() != 0){
            conta.setSaldo(dto.getSaldo());
        }
        if(dto.getTipoConta() != null && !dto.getTipoConta().isEmpty()){
            conta.setTipoConta(TipoConta.valueOf(dto.getTipoConta()));
        }
        if(dto.getDataAbertura() != null && !dto.getDataAbertura().toString().isEmpty()){
            conta.setDataAbertura(dto.getDataAbertura());
        }
        if(dto.getTitular() != null){
            conta.setTitular(Client.converter(ClientDTO.convert(Client.converter(dto.getTitular()))));
        }
        if(dto.getTransacoes() != null){
            conta.setTransacoes(dto.getTransacoes());
        }
        return ContaDTO.convert(repository.save(conta));
    }


}
