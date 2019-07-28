package org.ylf.repository;

import org.ylf.domain.SheBaoHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SheBaoHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SheBaoHistoryRepository extends JpaRepository<SheBaoHistory, Long> {

}
