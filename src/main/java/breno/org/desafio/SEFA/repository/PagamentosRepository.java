package breno.org.desafio.SEFA.repository;

import breno.org.desafio.SEFA.model.Pagamentos;
import breno.org.desafio.SEFA.utils.EnumStatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PagamentosRepository extends JpaRepository<Pagamentos, UUID> {
	
	List<Pagamentos> findAllByCodigoDebito( int codigoDebito);
	List<Pagamentos> findAllByStatus(EnumStatusPagamento statusPagamento);
	List<Pagamentos> findAllByCpfCnpj(String CpfCnpj);
	
	
	@Transactional
	@Modifying
	@Query("update Pagamentos set status = ?1 where id= ?2")
	int updateStatus ( EnumStatusPagamento statusPagamento, UUID id);
	
	
}
