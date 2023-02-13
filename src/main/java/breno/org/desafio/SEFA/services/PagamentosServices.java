package breno.org.desafio.SEFA.services;

import breno.org.desafio.SEFA.DTO.PagamentosDTO;
import breno.org.desafio.SEFA.model.Pagamentos;
import breno.org.desafio.SEFA.repository.PagamentosRepository;
import breno.org.desafio.SEFA.utils.EnumMetodosDePagamentos;
import breno.org.desafio.SEFA.utils.EnumStatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PagamentosServices {
	@Autowired
	private PagamentosRepository pagamentosRepository;
	
	public ResponseEntity<?> salvarPagamentos( PagamentosDTO pagamento){
		Pagamentos responseId;
		Pagamentos teste = new Pagamentos(pagamento);
		
		if(pagamento.numeroCartao().isEmpty()){
			 teste.setStatus(EnumStatusPagamento.processando);
			System.out.println(teste);
			if ( pagamento.metodosDePagamentos().equals(EnumMetodosDePagamentos.boleto) ||
					     pagamento.metodosDePagamentos().equals(EnumMetodosDePagamentos.pix) ){
				responseId = pagamentosRepository.save(teste);
				return new ResponseEntity<>(responseId.getId(), HttpStatus.CREATED);
			}
			
			
		}
		if(pagamento.numeroCartao().isPresent()) {
			if ( pagamento.metodosDePagamentos().equals(EnumMetodosDePagamentos.cartao_credito) ||
			pagamento.metodosDePagamentos().equals(EnumMetodosDePagamentos.cartao_debito) ) {
				
				responseId = pagamentosRepository.save(new Pagamentos(pagamento));
				return new ResponseEntity<>(responseId.getId(), HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>("Não foi possivel criar um pagamento", HttpStatus.FORBIDDEN);
	}
	
	public ResponseEntity<?> atualizarStatusPagamento() {
	
	}
}
