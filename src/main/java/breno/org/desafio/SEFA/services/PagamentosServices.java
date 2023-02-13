package breno.org.desafio.SEFA.services;

import breno.org.desafio.SEFA.DTO.FiltroDTO;
import breno.org.desafio.SEFA.DTO.PagamentosDTO;
import breno.org.desafio.SEFA.DTO.StatusDTO;
import breno.org.desafio.SEFA.model.Pagamentos;
import breno.org.desafio.SEFA.repository.PagamentosRepository;
import breno.org.desafio.SEFA.utils.EnumMetodosDePagamentos;
import breno.org.desafio.SEFA.utils.EnumStatusPagamento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
	
	public ResponseEntity<?> listarPagamentos ( FiltroDTO filtro){
		
		try{
		if( filtro.codigoDebito() != null){
			List<Pagamentos> response = pagamentosRepository.findAllByCodigoDebito(Integer.parseInt(filtro.codigoDebito().trim()));
			return new ResponseEntity<>(response,HttpStatus.OK);
			}
		
			if ( filtro.CpfCnpj().isPresent() ){
				List<Pagamentos> response =  pagamentosRepository.findAllByCpfCnpj(filtro.CpfCnpj().get());
				
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
			if(filtro.statusPagamento().isPresent()){
				List<Pagamentos> response =pagamentosRepository.findAllByStatus(filtro.statusPagamento().get());
				return new ResponseEntity<>(response,HttpStatus.OK);
			}
		return new ResponseEntity<>(pagamentosRepository.findAll(),HttpStatus.OK);
		}catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.OK);
		}
		
	}
	
	public ResponseEntity<?> exclusaoPagamento(UUID idPagamento){
		try{
			
			Optional<Pagamentos> processandoPagamento = pagamentosRepository.findById(idPagamento);
			if(processandoPagamento.get().getStatus() == EnumStatusPagamento.processando){
			pagamentosRepository.deleteById(idPagamento);
			return new ResponseEntity<>("Ação concluida com sucesso!",HttpStatus.OK);
			}
			return new ResponseEntity<>("Não foi possivel concluir essa operação",HttpStatus.OK);
			
		} catch ( Exception exception ){
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
}
