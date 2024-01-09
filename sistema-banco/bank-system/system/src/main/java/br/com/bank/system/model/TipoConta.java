package br.com.bank.system.model;

public enum TipoConta {
    CONTA_CORRENTE("Conta Corrente"),
    CONTA_POUPANCA("Conta Poupança");

    private String descricao;

    TipoConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
