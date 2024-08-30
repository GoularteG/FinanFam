package FanFam.api.controller;

import FanFam.api.model.receitas.*;
import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/receitas")
public class ReceitasController {

    @Autowired
    private ReceitasRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarReceitas(@RequestBody DadosCadastroReceitas dados){
        var receita= new Receitas(dados);
        repository.save(receita);

        return ResponseEntity.ok((new DadosDetalhamentoReceitas(receita)));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemReceitas>> listarReceitas(@RequestParam(required = false) String descricao) {
        List<Receitas> receitas;
        if (descricao != null) {
            receitas = repository.findByDescricaoContainingIgnoreCase(descricao);
        } else {
            receitas = repository.findAll();
        }
        List<DadosListagemReceitas> dadosListagem = receitas.stream()
                .map(DadosListagemReceitas::new)
                .toList();
        return ResponseEntity.ok(dadosListagem);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<List<DadosListagemReceitas>> listarReceitasPorId(@PathVariable Long id) {
        Optional<Receitas> receitas = repository.findById(id);
        List<DadosListagemReceitas> dadosListagem = receitas.stream()
                .map(DadosListagemReceitas::new)
                .toList();

        return ResponseEntity.ok(dadosListagem);
    }


    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoReceitas dados){
       var receitaExiste= repository.findById(id);
       if (receitaExiste.isPresent()) {
           Receitas receitas = receitaExiste.get();
           receitas.atualizarInformacoes(dados);
           repository.save(receitas);
           return ResponseEntity.ok("Receita atualizada com sucesso.");
       }
       else {
           return ResponseEntity.notFound().build();
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Receita "+ id+ " Excluída");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    

}
