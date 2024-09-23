package FanFam.api.model.despesas;

import FanFam.api.model.receitas.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Map;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas,Long> {

    List<Despesas> findByDescricaoContainingIgnoreCase(String descricao);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Despesas d WHERE d.descricao = :descricao AND FUNCTION('MONTH', d.data) = :mes AND FUNCTION('YEAR', d.data) = :ano")
    boolean existsByDescricaoAndDataMonthAndDataYear(@Param("descricao") String descricao, @Param("mes") int mes, @Param("ano") int ano);

    @Query("SELECT COALESCE(SUM(d.valor), 0) FROM Despesas d WHERE YEAR(d.data) = :ano AND MONTH(d.data) = :mes")
    BigDecimal sumByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);



}