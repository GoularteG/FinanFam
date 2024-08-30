package FanFam.api.controller;

import FanFam.api.model.despesas.*;
import FanFam.api.model.receitas.DadosListagemReceitas;
import FanFam.api.model.receitas.Receitas;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

    @Autowired
    private DespesasRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarDespesas(@RequestBody DadosCadastroDespesas dados) {
        var despesas = new Despesas(dados);
        repository.save(despesas);

        return ResponseEntity.ok(new DadosDetalhamentoDespesas(despesas));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemDespesas>> listarDespesas(@RequestParam(required = false) String descricao) {
        List<Despesas> despesas;
        if (descricao != null) {
            despesas = repository.findByDescricaoContainingIgnoreCase(descricao);
        } else {
            despesas = repository.findAll();
        }
        List<DadosListagemDespesas> dadosListagem = despesas.stream()
                .map(DadosListagemDespesas::new)
                .toList();
        return ResponseEntity.ok(dadosListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemDespesas> listarDespesasPorId(@PathVariable Long id) {
        Optional<Despesas> despesas = repository.findById(id);

        if (despesas.isPresent()) {
            DadosListagemDespesas dadosListagem = new DadosListagemDespesas(despesas.get());
            return ResponseEntity.ok(dadosListagem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDespesas(@PathVariable Long id, @RequestBody DadosAtualizacaoDespesas dados) {
        var despesasExiste = repository.findById(id);
        if (despesasExiste.isPresent()) {
            Despesas despesas = despesasExiste.get();
            despesas.atualizarInformacoes(dados);
            repository.save(despesas);
            return ResponseEntity.ok("Despesas atualizada com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity exclirDespesas(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
                return ResponseEntity.ok("Despesa " + id + " Excluída");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

