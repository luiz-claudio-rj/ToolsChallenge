package com.example.desafio.dto.request;

import com.example.desafio.model.Descricao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescricaoRequestDTO {
    private String valor;
    private String dataHora;
    private String estabelecimento;

    public Descricao toEntity() {
        Descricao descricao = new Descricao();

        descricao.setValor(this.getValor());
        descricao.setEstabelecimento(this.getEstabelecimento());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dataHora = LocalDateTime.parse(this.getDataHora(), formatter);
        descricao.setDataHora(dataHora);

        return descricao;
    }
}
