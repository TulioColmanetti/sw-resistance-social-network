package br.com.tulio.swresistancesocialnetwork.repository;

import br.com.tulio.swresistancesocialnetwork.model.DenounceTraitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DenounceTraitorRepository extends JpaRepository<DenounceTraitor, Long> {

    Optional<DenounceTraitor> findByInformerIdAndTraitorId(Long informerId, Long traitorId);

    List<DenounceTraitor> findAllByTraitorId(Long traitorId);
}
