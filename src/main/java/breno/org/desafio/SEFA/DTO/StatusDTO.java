package breno.org.desafio.SEFA.DTO;

import breno.org.desafio.SEFA.utils.EnumStatusPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StatusDTO(
		@NotNull(message = "o campo não pode ser nulo")  UUID id,
		@NotNull(message = "o campo não pode ser nulo")  EnumStatusPagamento statusPagamento
		) {
}
