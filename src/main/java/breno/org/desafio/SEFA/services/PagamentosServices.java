package breno.org.desafio.SEFA.services;

import breno.org.desafio.SEFA.DTO.PagamentosDTO;
import breno.org.desafio.SEFA.DTO.StatusDTO;
import breno.org.desafio.SEFA.model.Pagamentos;
import breno.org.desafio.SEFA.repository.PagamentosRepository;
import breno.org.desafio.SEFA.utils.EnumMetodosDePagamentos;
import breno.org.desafio.SEFA.utils.EnumStatusPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class PagamentosServices {
	@Autowired
	private PagamentosRepository pagamentosRepository;
	
	public ResponseEntity<?> salvarPagamentos( PagamentosDTO pagamento){
		Pagamentos responseId;
		Pagamentos teste = new Pagamentos(pagamento);
		
		if(pagamento.numeroCartao().isEmpty()){
			 teste.setStatus(EnumStatusPagamento.processando);
			
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
	
	public ResponseEntity<?> atualizarStatusPagamento( StatusDTO statusPagamento ) {
		UUID id = statusPagamento.id();
		EnumStatusPagamento status = statusPagamento.statusPagamento();
		try{
			Optional<Pagamentos> verificarStatus = pagamentosRepository.findById(id);
			
			if(verificarStatus.get().getStatus().equals(status)){
				return new ResponseEntity<>("não podemos concluir essa operação!",HttpStatus.FORBIDDEN);
			}
			if ( verificarStatus.get().getStatus().equals(EnumStatusPagamento.sucesso)){
				return new ResponseEntity<>("Não podemos alterar o status dessa operação!",HttpStatus.FORBIDDEN);
			}
			if ( verificarStatus.get().getStatus().equals(EnumStatusPagamento.falha)) {
				if(!status.equals(EnumStatusPagamento.processando)){
					return new ResponseEntity<>("Não podemos realizar essa operação!",HttpStatus.FORBIDDEN);
				}
			}
			
			return new ResponseEntity<>(pagamentosRepository.updateStatus(status,id), HttpStatus.OK);
		}catch ( Exception exception ){
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	public ResponseEntity<?> listarPagamentos ( Object filtro){
		
		try{
		filtro
		
		return new ResponseEntity<>(pagamentosRepository.findByStatus(filtro),HttpStatus.OK);
		}catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.OK);
		}
		
	}
}
