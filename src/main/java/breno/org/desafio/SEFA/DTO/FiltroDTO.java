package breno.org.desafio.SEFA.DTO;

import breno.org.desafio.SEFA.utils.EnumStatusPagamento;

import java.util.Optional;
import java.util.OptionalInt;

public record FiltroDTO (
		Optional<String> CpfCnpj,
		String codigoDebito,
		Optional<EnumStatusPagamento> statusPagamento
){
}
