package breno.org.desafio.SEFA.repository;

import breno.org.desafio.SEFA.model.Pagamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PagamentosRepository extends JpaRepository<Pagamentos, UUID> {
}
