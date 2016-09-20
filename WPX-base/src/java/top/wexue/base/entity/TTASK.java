package top.wexue.base.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lihb on 8/28/16.
 */
@Entity
@Table(name = "task")
@Data
public class TTask {
    @Id
    @GeneratedValue
    private Integer id;//int(128) NOT NULL AUTO_INCREMENT,
    private String state;//varchar(128) NOT NULL,
    private String corpid;//varchar(128) NOT NULL,
    private String type;//varchar(128) NOT NULL,
    private String userid;//varchar(128) NOT NULL,
    private Long createtime;//bigint(30) NOT NULL,
}
