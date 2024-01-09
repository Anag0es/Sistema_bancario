package br.com.bank.system.dto;

import br.com.bank.system.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO {
    private String tipo;
    private double valor;
    private Long contaOrigemId;
    private Long contaDestinoId;
    private String data;

    public static List<TransacaoDTO> convert(List<TransacaoDTO> transacoes) {
        if (transacoes == null) {
            return new ArrayList<>();
        }
        List<TransacaoDTO> transacoesDTO = new ArrayList<>();
        for (TransacaoDTO transacao : transacoes) {
            TransacaoDTO transacaoDTO = new TransacaoDTO();
            transacaoDTO.setTipo(transacao.getTipo());
            transacaoDTO.setValor(transacao.getValor());
            transacaoDTO.setContaOrigemId(transacao.getContaOrigemId());
            transacaoDTO.setContaDestinoId(transacao.getContaDestinoId());
            transacoesDTO.add(transacaoDTO);
        }
        return transacoesDTO;
    }

}
