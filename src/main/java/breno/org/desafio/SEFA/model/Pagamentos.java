package breno.org.desafio.SEFA.model;

import breno.org.desafio.SEFA.utils.EnumMetodosDePagamentos;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Pagamentos {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;
	@Column(name = "Codigo_debito",nullable = false,updatable = false)
	int CodigoDebito;
	@Column(name = "cpf_cnpj",nullable = false,updatable = false,length = 18)
	String CpfCnpj;
	@Enumerated(EnumType.STRING)
	EnumMetodosDePagamentos metodosDePagamentos;
	@Column(name = "numero_cartao",nullable = false,updatable = false,length = 16)
	String numeroCartao;
	@Column(name = "valor_pagamento",nullable = false,updatable = false)
	Double valorPagamento;
}
