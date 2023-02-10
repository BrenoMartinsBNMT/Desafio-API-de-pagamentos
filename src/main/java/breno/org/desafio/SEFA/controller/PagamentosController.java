package breno.org.desafio.SEFA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/pagamentos")
public class PagamentosController {
	
	
	@PostMapping("")
	public ResponseEntity receberPagamento( String a){
		return ResponseEntity.status(201).build();
	}
}
