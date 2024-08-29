package FanFam.api.model.despesas;


import java.time.LocalDateTime;

public record DadosDetalhamentoDespesas(Long id,
                                        String nome,
                                        String descricao,
                                        String valor,
                                        LocalDateTime data) {

    public DadosDetalhamentoDespesas(Despesas despesas){
        this(despesas.getId(), despesas.getNome(), despesas.getDescricao(), despesas.getValor(),despesas.getData());
    }
}
