package br.com.aluguelveiculos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.aluguelveiculos.entities.VeiculoEntity;

@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoControllerTestes {
    @Autowired
    MockMvc mockMvc;

    @Test
    void buscaTodosVeiculosReservados() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/veiculos?reservado=true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reservado").value(true));
    }

    @Test
    void buscaTodosVeiculosNaoReservados() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/veiculos?reservado=false"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].reservado").value(false));

    }

    @Test
    void insereVeiculoSemId() throws Exception {
        VeiculoEntity veiculo = new VeiculoEntity();
        veiculo.setModelo("Civic");
        veiculo.setMarca("Honda");
        veiculo.setCor("Prata");
        veiculo.setAnoDeFabricacao(2022);
        veiculo.setPlaca("ABC1234");
        veiculo.setReservado(false);

        String veiculoJson = new Gson().toJson(veiculo);

        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.post("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(veiculoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String textoResposta = resultado.getResponse().getContentAsString();
        assert ("Veiculo inserido com sucesso!".equals(textoResposta));
    }

    @Test
    void insereVeiculoComId() throws Exception {
        VeiculoEntity veiculo = new VeiculoEntity();
        veiculo.setId(100);

        String veiculoJson = new Gson().toJson(veiculo);

        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.post("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(veiculoJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        String textoResposta = resultado.getResponse().getContentAsString();
        assert ("Não foi possível inserir veículo.".equals(textoResposta));
    }

    @Test
    void alteraVeiculoComId() throws Exception {
        VeiculoEntity veiculo = new VeiculoEntity();
        veiculo.setId(1);
        veiculo.setModelo("Civic");
        veiculo.setMarca("Honda");
        veiculo.setCor("Prata");
        veiculo.setAnoDeFabricacao(2022);
        veiculo.setPlaca("ABC1234");
        veiculo.setReservado(true);
        String veiculoJson = new Gson().toJson(veiculo);

        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.put("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(veiculoJson))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String textoResposta = resultado.getResponse().getContentAsString();
        assert ("Veiculo alterado com sucesso!".equals(textoResposta));
    }

    @Test
    void alteraVeiculoSemId() throws Exception {
        VeiculoEntity veiculo = new VeiculoEntity();
        veiculo.setModelo("Civic");
        veiculo.setMarca("Honda");
        veiculo.setCor("Prata");
        veiculo.setAnoDeFabricacao(2022);
        veiculo.setPlaca("ABC1234");
        veiculo.setReservado(false);
        String veiculoJson = new Gson().toJson(veiculo);

        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.put("/veiculos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(veiculoJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        String textoResposta = resultado.getResponse().getContentAsString();
        assert ("Não foi possível alterar veículo.".equals(textoResposta));
    }

    @Test
    void apagaUmVeiculoPeloId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/veiculos/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
