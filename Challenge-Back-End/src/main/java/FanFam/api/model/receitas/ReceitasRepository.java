package FanFam.api.model.receitas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReceitasRepository extends JpaRepository<Receitas,Long> {

    List<Receitas> findByDescricaoContainingIgnoreCase(String descricao);
}