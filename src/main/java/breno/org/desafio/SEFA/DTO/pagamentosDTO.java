package breno.org.desafio.SEFA.DTO;

import breno.org.desafio.SEFA.utils.EnumMetodosDePagamentos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record pagamentosDTO (
		@NotNull(message = "o campo não pode ser nulo (código de débito)") @Min(value = 1,message = "valor não pode ser inferior a 1") int codigoDebito,
		@NotNull(message = "o campo não pode ser nulo (CPF ou CNPJ)") @NotBlank(message = "o campo não pode ser vazio (CPF ou CNPJ)") @Length(max = 12,min = 11) String CpfCnpj,
		@NotNull(message = "o campo não pode ser nulo (Metodos de pagamento)") EnumMetodosDePagamentos metodosDePagamentos,
		@NotNull(message = "o campo não pode ser nulo (valor do pagamento)") @Min( value = 1 ,message = "valor mínimo para pagamento é R$1") Double valorPagamento
		
							)
{
}
