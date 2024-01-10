package br.com.bank.system.controller;

import br.com.bank.system.dto.TransacaoDTO;
import br.com.bank.system.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    // listar todas as transacoes
    @GetMapping("/transacoes")
    public List<TransacaoDTO> listAll() {
        return service.listAll();
    }

    // listar conta origem
    @GetMapping("/transacoes/origem/{origem}")
    public List<TransacaoDTO> listOrigem(Long origem) {
        return service.listOrigem(origem);
    }

    // listar conta destino
    @GetMapping("/transacoes/destino/{destino}")
    public List<TransacaoDTO> listDestino(Long destino) {
        return service.listDestino(destino);
    }

    // listar pelo tipo
    @GetMapping("/transacoes/tipo/{tipo}")
    public List<TransacaoDTO> listTipo(String tipo) {
        return service.listTipo(tipo);
    }

    // listar pelo valor
    @GetMapping("/transacoes/valor/{valor}")
    public List<TransacaoDTO> listByValor(double valor) {
        return service.listByValor(valor);
    }

    // salvar uma nova transacao
    @GetMapping("/transacoes/save")
    public TransacaoDTO save(TransacaoDTO transacaoDTO) {
        return service.save(transacaoDTO);
    }

    // deletar uma transacao pelo seu id
    @DeleteMapping("/transacoes/delete/{id}")
    public TransacaoDTO delete(Long id) {
        return service.delete(id);
    }

    // editar uma transacao pelo seu id
    @PutMapping("/transacoes/edit/{id}")
    public TransacaoDTO editTransacao(Long id, TransacaoDTO transacaoDTO) {
        return service.editTransacao(id, transacaoDTO);
    }

}
