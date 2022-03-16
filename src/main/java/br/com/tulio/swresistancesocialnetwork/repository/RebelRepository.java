package br.com.tulio.swresistancesocialnetwork.repository;

import br.com.tulio.swresistancesocialnetwork.model.Rebel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebelRepository extends JpaRepository<Rebel, Long> {}
