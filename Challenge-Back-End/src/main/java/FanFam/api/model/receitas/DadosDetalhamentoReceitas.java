package FanFam.api.model.receitas;

import java.time.LocalDateTime;

public record DadosDetalhamentoReceitas(Long id,
        String nome,
        String descricao,
        String valor,
        LocalDateTime data) {

    public DadosDetalhamentoReceitas(Receitas receitas){
        this(receitas.getId(), receitas.getNome(), receitas.getDescricao(), receitas.getValor(),receitas.getData());
    }
}
