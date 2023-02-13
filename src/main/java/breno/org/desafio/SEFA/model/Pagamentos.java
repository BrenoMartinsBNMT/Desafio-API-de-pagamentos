package breno.org.desafio.SEFA.model;

import breno.org.desafio.SEFA.DTO.PagamentosDTO;
import breno.org.desafio.SEFA.utils.EnumMetodosDePagamentos;
import breno.org.desafio.SEFA.utils.EnumStatusPagamento;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "pagamentos")
public class Pagamentos {
	public Pagamentos( PagamentosDTO pagamentosDTO ){
	this.CodigoDebito = pagamentosDTO.codigoDebito();
	this.CpfCnpj = pagamentosDTO.CpfCnpj();
	this.metodosDePagamentos = pagamentosDTO.metodosDePagamentos();
	this.valorPagamento = pagamentosDTO.valorPagamento();
	this.numeroCartao = pagamentosDTO.numeroCartao().orElse("");
	this.status = pagamentosDTO.status();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;
	@Column(name = "Codigo_debito",nullable = false,updatable = false)
	int CodigoDebito;
	@Column(name = "cpf_cnpj",nullable = false,updatable = false,length = 18)
	String CpfCnpj;
	@Enumerated(EnumType.STRING)
	EnumMetodosDePagamentos metodosDePagamentos;
	@Column(name = "numero_cartao",updatable = false,length = 16)
	String numeroCartao;
	@Column(name = "valor_pagamento",nullable = false,updatable = false)
	Double valorPagamento;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	EnumStatusPagamento status ;
	public Pagamentos() {
	
	}
}
