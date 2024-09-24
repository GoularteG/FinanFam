package FanFam.api.model.despesas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DespesasRepositoryTest {

        @Autowired
        private DespesasRepository repository;
        @Autowired
        private TestEntityManager em;

        @Test
        @DisplayName("Verificar Receitas Duplicadas")
        void existsByDescricaoAndDataMonthAndDataYearCenario1() {
            LocalDateTime dataTeste = LocalDateTime.of(2024, 9, 15, 15, 0);
            cadastrarDespesas("Despesas", "Despesas", "50", dataTeste,Categoria.ALIMENTACAO);
            cadastrarDespesas("Despesas", "Despesas", "50", dataTeste, Categoria.IMPREVISTOS);
            var receitaDuplicada = repository.existsByDescricaoAndDataMonthAndDataYear("Despesas", 9, 2024);

            assertThat(receitaDuplicada).isTrue();
        }


        @Test
        @DisplayName("Verificar que não existe Receita Duplicada")
        void existsByDescricaoAndDataMonthAndDataYearCenario2() {
            LocalDateTime dataTeste = LocalDateTime.of(2024, 9, 15, 0, 0); // 15 de setembro de 2024

            cadastrarDespesas("Outra Despesa", "desc", "50", dataTeste, Categoria.LAZER );

            var receitaUnica = repository.existsByDescricaoAndDataMonthAndDataYear("Receita", 9, 2024);

            assertThat(receitaUnica).isFalse();
        }









        private Despesas cadastrarDespesas(String nome, String descricao, String valor, LocalDateTime data, Categoria categoria){
            var despesas= new Despesas(dadosDespesas(nome,descricao,valor,data,categoria));
            em.persist(despesas);
            em.flush();
            return despesas;
        }
        private DadosCadastroDespesas dadosDespesas(String nome, String descricao, String valor, LocalDateTime data, Categoria categoria){
            return new DadosCadastroDespesas(
                    nome,
                    descricao,
                    "50",
                    data,
                    categoria
            );
        }
    }