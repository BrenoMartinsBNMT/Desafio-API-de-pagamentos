package breno.org.desafio.SEFA.DTO;

import breno.org.desafio.SEFA.utils.EnumStatusPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StatusDTO(
		@NotNull(message = "o campo não pode ser nulo") @NotBlank(message = "insiria um id válido") String id,
		@NotNull(message = "o campo não pode ser nulo") @NotBlank(message = "insira um tipo de status válido") EnumStatusPagamento status
		) {
}
