package org.ylf.repository;

import org.ylf.domain.MailContent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MailContent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MailContentRepository extends JpaRepository<MailContent, Long> {

}
