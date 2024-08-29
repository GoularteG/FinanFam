package FanFam.api.model.receitas;




import java.time.LocalDate;

public record DadosListagemReceitas(
        Long id,
        String nome,
        String descricao,
        String valor,
        LocalDate data) {

    public DadosListagemReceitas(Receitas receita) {
        this(receita.getId(), receita.getNome(), receita.getDescricao(), receita.getValor(), LocalDate.from(receita.getData()));
    }

}

