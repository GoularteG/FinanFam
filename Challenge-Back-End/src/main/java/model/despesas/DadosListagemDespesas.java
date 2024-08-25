package model.despesas;

import java.time.LocalDate;

public record DadosListagemDespesas(
        String descricao,
        String valor,
        LocalDate data) {

    public DadosListagemDespesas(Despesas despesas) {
        this(despesas.getDescricao(), despesas.getValor(), LocalDate.from(despesas.getData()));
    }
}

