package org.ylf.repository;

import org.ylf.domain.Gongjijin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Gongjijin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GongjijinRepository extends JpaRepository<Gongjijin, Long> {

}
