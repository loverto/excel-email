package org.ylf.repository;

import org.ylf.domain.MailConfig;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MailConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MailConfigRepository extends JpaRepository<MailConfig, Long> {

}
