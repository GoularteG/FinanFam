package FanFam.api.model.receitas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ReceitasRepository extends JpaRepository<Receitas,Long> {

    List<Receitas> findByDescricaoContainingIgnoreCase(String descricao);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Receitas r WHERE r.descricao = :descricao AND FUNCTION('MONTH', r.data) = :mes AND FUNCTION('YEAR', r.data) = :ano")
    boolean existsByDescricaoAndDataMonthAndDataYear(@Param("descricao") String descricao, @Param("mes") int mes, @Param("ano") int ano);

    @Query("SELECT COALESCE(SUM(r.valor), 0) FROM Receitas r WHERE YEAR(r.data) = :ano AND MONTH(r.data) = :mes")
    BigDecimal sumByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);


}