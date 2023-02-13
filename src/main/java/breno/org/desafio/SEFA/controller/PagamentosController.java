package breno.org.desafio.SEFA.controller;

import breno.org.desafio.SEFA.DTO.PagamentosDTO;

import breno.org.desafio.SEFA.DTO.StatusDTO;
import breno.org.desafio.SEFA.services.PagamentosServices;
import breno.org.desafio.SEFA.utils.EnumStatusPagamento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentosController {
	@Autowired
	private PagamentosServices pagamentosServices;

	@PostMapping("")
	public ResponseEntity<?> receberPagamento  (@Valid @RequestBody PagamentosDTO body){
		
		try{
			return new ResponseEntity<>(pagamentosServices.salvarPagamentos(body),HttpStatus.CREATED);
		}catch ( Exception exception ){
		 return new ResponseEntity<>(exception.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	@PutMapping("/atualizarStatus")
	public ResponseEntity<?> atualizarStatusPagamento( @Valid @RequestBody StatusDTO statusPagamento){
		
		try {
			return new ResponseEntity<>(pagamentosServices.atualizarStatusPagamento(statusPagamento),HttpStatus.CREATED);
		}catch ( Exception exception ){
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("")
	public ResponseEntity<?> listarPagamentos( @Param ("filtro") Object filtro ){
		try {
			System.out.println(filtro);
			return new ResponseEntity<>(pagamentosServices.listarPagamentos(filtro), HttpStatus.OK);
		}catch ( Exception exception ) {
			return new ResponseEntity<>("dsadsa",HttpStatus.OK);
		}
	}
	
}
