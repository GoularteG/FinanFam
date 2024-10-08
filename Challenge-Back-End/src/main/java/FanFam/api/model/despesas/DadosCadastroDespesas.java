package FanFam.api.model.despesas;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosCadastroDespesas(
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotBlank
        String valor,
        @NotBlank
        LocalDateTime data,

        Categoria categoria
) {
        public DadosCadastroDespesas {
                if (categoria == null) {
                        categoria = Categoria.OUTRAS;
                }
        }
}
