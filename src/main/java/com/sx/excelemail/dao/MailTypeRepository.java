package com.sx.excelemail.dao;

import com.sx.excelemail.po.MailConfig;
import com.sx.excelemail.po.MailType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailTypeRepository extends JpaRepository<MailType,String> {
}
