package br.com.aluguelveiculos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aluguelveiculos.entities.VeiculoEntity;
import br.com.aluguelveiculos.services.VeiculoService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("veiculos")
@CrossOrigin()
public class VeiculoController {
    @Autowired
    VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<VeiculoEntity>> consultarVeiculos(@PathParam("reservado") Boolean reservado) {
        List<VeiculoEntity> veiculos = veiculoService.buscarVeiculos(reservado);
        return ResponseEntity.status(200).body(veiculos);
    }

    @PostMapping
    public ResponseEntity<String> criarVeiculo(@RequestBody VeiculoEntity veiculo) {
        if (veiculo.getId() != null) {
            return ResponseEntity.badRequest().body("Não foi possível inserir veículo.");
        } else {
            veiculoService.inserirAlterarVeiculo(veiculo);
            return ResponseEntity.status(201).body("Veiculo inserido com sucesso!");
        }
    }

    @PutMapping
    public ResponseEntity<String> atualizarVeiculo(@RequestBody VeiculoEntity veiculo) {
        if (veiculo.getId() == null) {
            return ResponseEntity.badRequest().body("Não foi possível alterar veículo.");
        } else {
            veiculoService.inserirAlterarVeiculo(veiculo);
            return ResponseEntity.status(200).body("Veiculo alterado com sucesso!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagaLivro(@PathVariable("id") Integer id) {
        veiculoService.apagarVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
