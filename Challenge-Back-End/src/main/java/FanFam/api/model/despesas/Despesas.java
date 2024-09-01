package FanFam.api.model.despesas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

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
    @Column(name = "data", nullable = false)
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private Categoria categoria= Categoria.OUTRAS;

    public Despesas(DadosCadastroDespesas dados) {
        this.nome=dados.nome();
        this.descricao= dados.descricao();
        this.valor=dados.valor();
        this.data=dados.data();
        this.categoria= dados.categoria();
    }

    public void atualizarInformacoes(DadosAtualizacaoDespesas dados){
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
