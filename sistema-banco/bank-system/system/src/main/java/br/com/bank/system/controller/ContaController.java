package br.com.bank.system.controller;

import br.com.bank.system.dto.ContaDTO;
import br.com.bank.system.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContaController {

    @Autowired
    private ContaService service;

    // retorna todas as contas
    @GetMapping("/contas")
    public List<ContaDTO> listAll() {
        return service.ListAll();
    }

    // retorna uma conta pelo seu numero
    @GetMapping("/contas/{numero}")
    public ContaDTO findByNumero(@PathVariable int numero){
        return service.findByNumero(numero);
    }

    // retorna contas por tipo
    @GetMapping("/contas/tipo/{tipoConta}")
    public List<ContaDTO> findByTipoConta(@PathVariable String tipoConta){
        return service.findByTipoConta(tipoConta);
    }

    // retorna contas com saldo maior que um valor
    @GetMapping("/contas/saldo/{saldo}")
    public List<ContaDTO> findBySaldoGreaterThan(@PathVariable double saldo){
        return service.findBySaldoGreaterThan(saldo);
    }

    // retorna contas com transacoes em um intervalo de datas
    @GetMapping("/contas/transacoes/{dataInicio}/{dataFim}")
    public List<ContaDTO> findByTransacoes_Data(@PathVariable String dataInicio, @PathVariable String dataFim) {
        return service.findByTransacoes_Data(dataInicio, dataFim);
    }   

    // retorna contas por id do cliente
    @GetMapping("/contas/cliente/{clienteId}")
    public List<ContaDTO> findByClienteId(@PathVariable Long clienteId){
        return service.findByClienteId(clienteId);
    }

    // salvar uma nova conta
    @PostMapping("/contas")
    public ContaDTO save(@Valid @RequestBody ContaDTO contaDTO) {
        return service.save(contaDTO);
    }

    // deleta um conta
    @DeleteMapping("/contas/delete/{numero}")
    public ContaDTO delete(@PathVariable int numero){
        return service.delete(numero);
    }

    // edita uma conta
    @PutMapping("/contas/edit/{numero}")
    public ContaDTO editConta(@PathVariable int numero, @RequestBody ContaDTO contaDTO){
        return service.editConta((long) numero, contaDTO);
    }
}
