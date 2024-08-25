package model;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroDespesas(
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotBlank
        String valor,
        @NotBlank
        String data
) {
}
