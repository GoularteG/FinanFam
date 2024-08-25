package model.receitas;


import java.time.LocalDate;

public record DadosListagemReceitas(
        String descricao,
        String valor,
        LocalDate data) {

    public DadosListagemReceitas(Receitas receita) {
        this(receita.getDescricao(), receita.getValor(), LocalDate.from(receita.getData()));
    }
}

