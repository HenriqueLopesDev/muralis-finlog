package org.finlog.finlogbackendspring.business.address.infrastructure.http.dto;

public record ViaCepResponse (
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    String uf,
    String estado,
    String ibge,
    String gia,
    String ddd,
    String siafi,
    String erro
) {}
