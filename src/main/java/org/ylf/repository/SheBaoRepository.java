package org.ylf.repository;

import org.ylf.domain.SheBao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SheBao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SheBaoRepository extends JpaRepository<SheBao, Long> {

}
