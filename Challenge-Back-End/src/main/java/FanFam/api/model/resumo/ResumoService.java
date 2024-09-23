package FanFam.api.model.resumo;

import FanFam.api.model.despesas.Categoria;
import FanFam.api.model.despesas.DespesasRepository;
import FanFam.api.model.receitas.ReceitasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;


@Service
public class ResumoService {

    @Autowired
    private ReceitasRepository receitaRepository;
    @Autowired
    private DespesasRepository despesaRepository;



    public ResumoMesDTO ResumoDoMes(int ano, int mes) {
        BigDecimal totalReceitas = receitaRepository.sumByAnoAndMes(ano, mes);
        BigDecimal totalDespesas = despesaRepository.sumByAnoAndMes(ano, mes);
        BigDecimal saldoFinal = totalReceitas.subtract(totalDespesas);

        ResumoMesDTO resumo = new ResumoMesDTO();
        resumo.setTotalReceitas(totalReceitas);
        resumo.setTotalDespesas(totalDespesas);
        resumo.setSaldoFinal(saldoFinal);


        return resumo;
    }
}
