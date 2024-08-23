package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Despesas")
@Table(name = "despesas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Despesas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String descricao;
    String valor;
    String data;

    public Despesas(DadosCadastroDespesas dados) {
        this.nome=dados.nome();
        this.descricao= dados.descricao();
        this.valor=dados.valor();
        this.data=dados.data();
    }
}
