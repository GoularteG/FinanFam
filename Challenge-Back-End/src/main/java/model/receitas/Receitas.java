package model.receitas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Receitas")
@Table (name = "receitas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Receitas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String descricao;
    String valor;
    LocalDateTime data;

    public Receitas(DadosCadastroReceitas dados) {
        this.nome=dados.nome();
        this.descricao= dados.descricao();
        this.valor=dados.valor();
        this.data=dados.data();
    }

    public void atualizarInformacoes(DadosAtualizacaoReceitas dados){
        if (dados.descricao() != null){
                this.descricao= dados.descricao();
        }
        if (dados.data() != null){
            this.data= (dados.data());
        }
        if (dados.nome() != null){
            this.nome= dados.nome();
        }
        if (dados.valor() != null){
            this.valor= dados.valor();
        }
    }
}
