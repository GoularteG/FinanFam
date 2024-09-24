package FanFam.api.model.receitas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ReceitasRepositoryTest {

    @Autowired
    private ReceitasRepository repository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Verificar Receitas Duplicadas")
    void existsByDescricaoAndDataMonthAndDataYearCenario1() {
        LocalDateTime dataTeste = LocalDateTime.of(2024, 9, 15, 15, 0);
        cadastrarReceitas("Receita", "Receita", "50", dataTeste);
        cadastrarReceitas("Receita", "Receita", "50", dataTeste);
        var receitaDuplicada = repository.existsByDescricaoAndDataMonthAndDataYear("Receita", 9, 2024);

        assertThat(receitaDuplicada).isTrue();
    }


    @Test
    @DisplayName("Verificar que não existe Receita Duplicada")
    void existsByDescricaoAndDataMonthAndDataYearCenario2() {
        LocalDateTime dataTeste = LocalDateTime.of(2024, 9, 15, 0, 0); // 15 de setembro de 2024

        cadastrarReceitas("Outra Receita", "desc", "50", dataTeste);

        var receitaUnica = repository.existsByDescricaoAndDataMonthAndDataYear("Receita", 9, 2024);

        assertThat(receitaUnica).isFalse();
    }









    private Receitas cadastrarReceitas(String nome, String descricao, String valor, LocalDateTime data){
        var receita= new Receitas(dadosReceitas(nome,descricao,valor,data));
        em.persist(receita);
        em.flush();
        return receita;
    }
    private DadosCadastroReceitas dadosReceitas(String nome, String descricao, String valor, LocalDateTime data){
        return new DadosCadastroReceitas(
                nome,
                descricao,
                "50",
                data
        );
    }
}