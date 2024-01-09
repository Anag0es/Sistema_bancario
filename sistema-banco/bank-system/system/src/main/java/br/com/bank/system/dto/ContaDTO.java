package br.com.bank.system.dto;

import br.com.bank.system.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {

    private Long id;
    private double saldo;
    private String tipoConta;
    private String dataAbertura;
    private ClientDTO titular;
    private List<TransacaoDTO> transacoes;
    private int numeroDaConta;
    private Long clienteId;

    public void setDataAberturaAtualFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataAbertura = LocalDate.now().format(formatter);
    }

    // convert
    public static ContaDTO convert(Conta conta) {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setId(conta.getId());
        contaDTO.setSaldo(conta.getSaldo());
        contaDTO.setTipoConta(conta.getTipoConta().getDescricao());
        contaDTO.setDataAbertura(conta.getDataAbertura());
        contaDTO.setTitular(ClientDTO.convert(conta.getTitular().getTitular()));
        contaDTO.setTransacoes(TransacaoDTO.convert(conta.getTransacoes()));
        contaDTO.setNumeroDaConta(conta.getNumeroDaConta());
        contaDTO.setClienteId(conta.getId());

        if (conta.getTitular() != null) {
            contaDTO.setTitular(ClientDTO.convert(conta.getTitular().getTitular()));
        }

        if (conta.getTransacoes() != null) {
            contaDTO.setTransacoes(TransacaoDTO.convert(conta.getTransacoes()));
        } else {
            contaDTO.setTransacoes(new ArrayList<>());
        }

        return contaDTO;
    }


}
