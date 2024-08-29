package FanFam.api.model.despesas;

import java.time.LocalDate;

public record DadosListagemDespesas(
        Long id,
        String nome,
        String descricao,
        String valor,
        LocalDate data) {

    public DadosListagemDespesas(Despesas despesas) {
        this(despesas.getId(),despesas.getNome(), despesas.getDescricao(), despesas.getValor(), LocalDate.from(despesas.getData()));
    }
}

