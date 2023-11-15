package br.com.aluguelveiculos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aluguelveiculos.entities.VeiculoEntity;
import br.com.aluguelveiculos.repositories.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<VeiculoEntity> buscarVeiculos(Boolean reservado) {
        List<VeiculoEntity> veiculos = new ArrayList<>();
        if (reservado == null) {
            veiculos = veiculoRepository.findAll();
        } else {
            veiculos = veiculoRepository.findAllByReservado(reservado);
        }
        return veiculos;
    }

    public void inserirAlterarVeiculo(VeiculoEntity veiculo) {
        veiculoRepository.save(veiculo);
    }

    public void apagarVeiculo(Integer id) {
        veiculoRepository.deleteById(id);
    }
}
