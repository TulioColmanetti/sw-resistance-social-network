package br.com.tulio.swresistancesocialnetwork.repository;

import br.com.tulio.swresistancesocialnetwork.model.DenounceTraitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenounceTraitorRepository extends JpaRepository<DenounceTraitor, Long> {}
