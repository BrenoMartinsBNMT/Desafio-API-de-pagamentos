package breno.org.desafio.SEFA.controller;

import breno.org.desafio.SEFA.DTO.PagamentosDTO;

import breno.org.desafio.SEFA.services.PagamentosServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/atualizarStatus")
	public String atualizarStatusPagamento(){
		try {
			return "dsadsa";
		}catch ( Exception exception ){
			return null;
		}
	}
}
