package controller;

import jakarta.transaction.Transactional;
import model.receitas.DadosAtualizacaoReceitas;
import model.receitas.DadosCadastroReceitas;
import model.receitas.DadosListagemReceitas;
import model.receitas.Receitas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.ReceitasRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/receitas")
public class ReceitasController {

    @Autowired
    ReceitasRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarReceitas(@RequestBody DadosCadastroReceitas dados){
        var receita= new Receitas(dados);
        repository.save(receita);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity <List<DadosListagemReceitas>> listarReceitas() {
        List<Receitas> receitas = repository.findAll();
                receitas.stream()
                .map(DadosListagemReceitas::new)
                .toList();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<List<DadosListagemReceitas>> listarReceitasPorId(@PathVariable Long id) {
        Optional<Receitas> receitas = repository.findById(id);
                 receitas.stream()
                .map(DadosListagemReceitas::new)
                .toList();
        return ResponseEntity.ok().build();
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
    public ResponseEntity exclir(@PathVariable Long id){
        repository.deleteById(id);


        return ResponseEntity.noContent().build();
    }

    

}
