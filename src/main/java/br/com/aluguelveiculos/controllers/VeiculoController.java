package br.com.aluguelveiculos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> criarVeiculo(@RequestBody VeiculoEntity veiculo) {
        Map<String, String> response = new HashMap<>();
        if (veiculo.getId() != null) {
            response.put("status", "error");
            response.put("message", "Não foi possível inserir veículo.");
            return ResponseEntity.badRequest().body(response);
        } else {
            veiculoService.inserirAlterarVeiculo(veiculo);
            response.put("status", "success");
            response.put("message", "Veiculo inserido com sucesso!");
            return ResponseEntity.status(201).body(response);
        }
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> atualizarVeiculo(@RequestBody VeiculoEntity veiculo) {
        Map<String, String> response = new HashMap<>();
        if (veiculo.getId() == null) {
            response.put("status", "error");
            response.put("message", "Não foi possível alterar veículo.");
            return ResponseEntity.badRequest().body(response);
        } else {
            veiculoService.inserirAlterarVeiculo(veiculo);
            response.put("status", "success");
            response.put("message", "Veiculo alterado com sucesso!");
            return ResponseEntity.status(200).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagaLivro(@PathVariable("id") Integer id) {
        veiculoService.apagarVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
