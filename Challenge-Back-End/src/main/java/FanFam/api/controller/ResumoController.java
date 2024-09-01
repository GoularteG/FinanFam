package FanFam.api.controller;

import FanFam.api.model.resumo.ResumoMesDTO;
import FanFam.api.model.resumo.ResumoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResumoController {

    @Autowired
    private ResumoService resumoService;

    @GetMapping("/{ano}/{mes}")
    @Transactional
    public ResponseEntity<ResumoMesDTO> ResumoDoMes(@PathVariable int ano, @PathVariable int mes) {
        ResumoMesDTO resumo = resumoService.ResumoDoMes(ano, mes);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resumo);
    }
}