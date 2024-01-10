package br.com.bank.system.service;

import br.com.bank.system.dto.TransacaoDTO;
import br.com.bank.system.model.Conta;
import br.com.bank.system.model.Transacao;
import br.com.bank.system.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    @Autowired
    private ContaService contaService;

    // listar todas as transacoes
    public List<TransacaoDTO> listAll(){
        List<Transacao> transacoes = repository.findAll();
        return transacoes.stream().map(TransacaoDTO::convert)
                .collect(Collectors.toList());
    }

    // listar conta origem
    public List<TransacaoDTO> listOrigem(Long origem){
        List<Transacao> transacoes;
        transacoes = repository.findByContaOrigemId(origem);
        return transacoes.stream()
                .map(TransacaoDTO::convert)
                .collect(Collectors.toList());

    }

    // listar conta destino
    public List<TransacaoDTO> listDestino(Long destino){
        List<Transacao> transacoes;
        transacoes = repository.findByContaDestinoId(destino);
        return transacoes.stream().map(TransacaoDTO::convert)
                .collect(Collectors.toList());
    }

    // listar pelo tipo
    public List<TransacaoDTO> listTipo(String tipo){
        List<Transacao> transacoes;
        transacoes = repository.findByTipo(tipo);
        return transacoes.stream().map(TransacaoDTO::convert)
                .collect(Collectors.toList());
    }

    // listar pelo valor
    public List<TransacaoDTO> listByValor(double valor){
        List<Transacao> transacoes;
        transacoes = repository.findByValor(valor);
        return transacoes.stream().map(TransacaoDTO::convert)
                .collect(Collectors.toList());
    }

    // criar transacao
    public TransacaoDTO save(TransacaoDTO dto){
        Transacao transacao = (Transacao) Transacao.convert((List<TransacaoDTO>) dto);
        transacao = repository.save(transacao);
        return TransacaoDTO.convert(transacao);
    }

    // atualizar transacao
    public TransacaoDTO editTransacao(Long id, TransacaoDTO dto){
        Transacao transacao;
        transacao = repository.findById(id).orElseThrow(() -> new RuntimeException("transação não encontrada"));
        if(dto.getData() != null){
            transacao.setData(dto.getData());
        }

        if(dto.getTipo() != null && dto.getTipo().isEmpty()){
            transacao.setTipo(dto.getTipo());
        }

        if(dto.getContaOrigemId() != null){
            transacao.setContaOrigemId(dto.getContaOrigemId());
        }

        if(dto.getContaDestinoId() != null){
            transacao.setContaDestinoId(dto.getContaDestinoId());
        }

        if(dto.getValor() != 0){
            transacao.setValor(dto.getValor());
        }

        if(dto.getData() != null && dto.getData().toString().isEmpty()){
            transacao.setData(dto.getData());
        }

        return TransacaoDTO.convert(repository.save(transacao));
    }

    // deletar uma transacao
    public TransacaoDTO delete(long id){
        Transacao transacao;
        transacao = repository.findById(id).orElseThrow(() -> new RuntimeException("Transação não encontrada"));
        return TransacaoDTO.convert(transacao);
    }
}
