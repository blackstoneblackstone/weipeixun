package top.wexue.base.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lihb on 9/2/16.
 */
@Data
@Entity
@Table(name = "auth_agent_info")
public class TAgentInfo {
    @Id
    private int id;
    private int authType;// int(16) NOT NULL,
    private int appId;// int(16) NOT NULL COMMENT '服务商套件中的对应应用id',
    private String apiGroup;// varchar(256) NOT NULL,
    private int privilegeInfoId;// int(16) NOT NULL,
    private String corpId;// varchar(128) NOT NULL,
    private String suitId;// varchar(128) NOT NULL
}
