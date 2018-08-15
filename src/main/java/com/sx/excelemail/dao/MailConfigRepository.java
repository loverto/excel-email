package com.sx.excelemail.dao;

import com.sx.excelemail.po.MailConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailConfigRepository extends JpaRepository<MailConfig,String> {
    //MailConfig findFirstByOrderByInsertDateDesc();
    MailConfig findFirstByOrderByInsertDateDesc();
}
