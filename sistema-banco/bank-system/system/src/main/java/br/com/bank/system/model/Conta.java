package br.com.bank.system.model;

import br.com.bank.system.dto.ContaDTO;
import br.com.bank.system.dto.TransacaoDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@Node("Conta")
public class Conta {
    @Id
    @GeneratedValue
    private Long id;

    private int numero;

    private Long clienteId;

    private int numeroDaConta;

    private double saldo;
    private TipoConta tipoConta;

    private String dataAbertura;

    @Relationship(type = "TEM_CONTA", direction = Relationship.Direction.OUTGOING)
    private Client titular;

    @Relationship(type = "REALIZA_TRANSACAO", direction = Relationship.Direction.OUTGOING)
    private List<TransacaoDTO> transacoes;

    // gerar numero aleatorio para conta e a dataAtual
    public Conta() {
        this.numeroDaConta = getnumeroAleatorio();
        this.dataAbertura = getDataAtualFormatada();
    }

    public int getnumeroAleatorio() {
        Random rand = new Random();
        return rand.nextInt(90000) + 10000;
    }

    private String getDataAtualFormatada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.now().format(formatter);
    }

    // convert
    public static Conta convert(ContaDTO conta) {
        Conta contaDTO = new Conta();
        contaDTO.setId(conta.getId());
        contaDTO.setSaldo(conta.getSaldo());
        contaDTO.setTipoConta(TipoConta.valueOf(conta.getTipoConta()));
        contaDTO.setDataAbertura(conta.getDataAbertura());
        if (conta.getTitular() != null) {
            contaDTO.setTitular(Client.converter(conta.getTitular()));
        }
        contaDTO.setClienteId(conta.getClienteId());
        contaDTO.setTransacoes(conta.getTransacoes());
        contaDTO.setNumeroDaConta(conta.getNumeroDaConta());
        return contaDTO;
    }


}
