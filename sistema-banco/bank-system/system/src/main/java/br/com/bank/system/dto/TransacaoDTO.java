package br.com.bank.system.dto;

import br.com.bank.system.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO {
    private String tipo;
    private double valor;
    private Long contaOrigemId;
    private Long contaDestinoId;
    private Date data;

    public static TransacaoDTO convert(Transacao transacao) {
        if (transacao == null) {
            return null;
        }

        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setTipo(transacao.getTipo());
        transacaoDTO.setValor(transacao.getValor());
        transacaoDTO.setData(transacao.getData());
        transacaoDTO.setContaOrigemId(transacao.getContaOrigemId());
        transacaoDTO.setContaDestinoId(transacao.getContaDestinoId());
        return transacaoDTO;
    }
}
