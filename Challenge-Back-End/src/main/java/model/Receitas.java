package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    String data;

    public Receitas(DadosCadastroReceitas dados) {
        this.nome=dados.nome();
        this.descricao= dados.descricao();
        this.valor=dados.valor();
        this.data=dados.data();
    }
}
