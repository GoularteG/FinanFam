package FanFam.api.model.despesas;

import FanFam.api.model.receitas.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas,Long> {

    List<Despesas> findByDescricaoContainingIgnoreCase(String descricao);
}