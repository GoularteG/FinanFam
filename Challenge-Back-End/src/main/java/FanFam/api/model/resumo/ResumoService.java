package FanFam.api.model.resumo;

import FanFam.api.model.despesas.DespesasRepository;
import FanFam.api.model.receitas.ReceitasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResumoService {

    @Autowired
    private ReceitasRepository receitaRepository;
    @Autowired
    private DespesasRepository despesaRepository;



    public ResumoMesDTO ResumoDoMes(int ano, int mes) {
        var totalReceitas = receitaRepository.sumByAnoAndMes(ano, mes);
        var totalDespesas = despesaRepository.sumByAnoAndMes(ano, mes);
        var saldoFinal = totalReceitas.subtract(totalDespesas);
        var totalGastoPorCategoria = despesaRepository.sumByCategoriaAndAnoAndMes(ano, mes);
        ObjectMapper mapper = new ObjectMapper();
        mapper.getSerializerProvider().setNullKeySerializer(new NullKeySerializer());
        totalGastoPorCategoria.entrySet().removeIf(entry -> entry.getKey() == null);
        ResumoMesDTO resumo = new ResumoMesDTO();
        resumo.setTotalReceitas(totalReceitas);
        resumo.setTotalDespesas(totalDespesas);
        resumo.setSaldoFinal(saldoFinal);
        resumo.setTotalGastoPorCategoria(totalGastoPorCategoria);


        return resumo;
    }
}
