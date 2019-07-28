package org.ylf.repository;

import org.ylf.domain.GongjijinHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GongjijinHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GongjijinHistoryRepository extends JpaRepository<GongjijinHistory, Long> {

}
