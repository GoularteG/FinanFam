package controller;

import jakarta.transaction.Transactional;
import model.despesas.DadosAtualizacaoDespesas;
import model.despesas.DadosCadastroDespesas;
import model.despesas.DadosListagemDespesas;
import model.despesas.Despesas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.DespesasRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("despesas")
public class DespesasController {

    @Autowired
    DespesasRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarDespesas(@RequestBody DadosCadastroDespesas dados) {
        var despesas = new Despesas(dados);
        repository.save(despesas);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemDespesas>> listarDespesas() {
        List<Despesas> despesas = repository.findAll();
        despesas.stream()
                .map(DadosListagemDespesas::new)
                .toList();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<List<DadosListagemDespesas>> listarDespesasPorId(@PathVariable Long id) {
        Optional<Despesas> despesas = repository.findById(id);
        despesas.stream()
                .map(DadosListagemDespesas::new)
                .toList();
        return ResponseEntity.ok().build();
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
        repository.deleteById(id);


        return ResponseEntity.noContent().build();
    }
}
