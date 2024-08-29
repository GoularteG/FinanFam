package FanFam.api.model.receitas;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosAtualizacaoReceitas(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotBlank String valor,
        @NotBlank LocalDateTime data) {
}

