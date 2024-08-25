package model.despesas;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosAtualizacaoDespesas(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotBlank String valor,
        @NotBlank LocalDateTime data) {
}
