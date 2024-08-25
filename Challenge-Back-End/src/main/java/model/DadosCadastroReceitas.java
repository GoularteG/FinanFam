package model;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosCadastroReceitas(
       @NotBlank
        String nome,
       @NotBlank
        String descricao,
       @NotBlank
        String valor,
       @NotBlank
       LocalDateTime data
) {
}
