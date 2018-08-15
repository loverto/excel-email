package com.sx.excelemail.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class MailType {

    @Id
    private String id;
    private String name;
    private String typeOrder;
    private LocalDateTime createTime;

}
