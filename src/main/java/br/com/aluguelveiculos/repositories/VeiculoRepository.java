package br.com.aluguelveiculos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aluguelveiculos.entities.VeiculoEntity;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Integer> {
    List<VeiculoEntity> findAllByReservado(Boolean reservado);
}
