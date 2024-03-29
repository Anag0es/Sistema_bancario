package br.com.bank.system.model;

import br.com.bank.system.dto.TransacaoDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Node("Transacao")
public class Transacao {
    @Id @GeneratedValue
    private Long id;
    private String descricao;
    private double valor;
    private String tipo;
    private Long contaOrigemId;
    private Long contaDestinoId;
    private Date data;

    @Relationship(type = "PERTENCE_A", direction = Relationship.Direction.INCOMING)
    private Conta conta;

    public static List<Transacao> convert(List<TransacaoDTO> transacoesDTO) {
        List<Transacao> transacoes = new ArrayList<>();
        for (TransacaoDTO transacaodto : transacoesDTO) {
            Transacao transacao = new Transacao();
            transacao.setValor(transacaodto.getValor());
            transacao.setTipo(transacaodto.getTipo());
            transacao.setData(transacaodto.getData());
            transacao.setConta(Conta.convert(transacaodto.getConta()));
            transacao.setContaOrigemId(transacaodto.getContaOrigemId());
            transacao.setContaDestinoId(transacaodto.getContaDestinoId());
            transacoes.add(transacao);
        }
        return transacoes;
    }
}
