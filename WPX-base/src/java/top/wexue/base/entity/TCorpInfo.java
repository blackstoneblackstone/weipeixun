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
@Table(name = "auth_corp_info")
public class TCorpInfo {
    private String permanentCode;// varchar(256) NOT NULL COMMENT '永久授权码',
    @Id
    private String corpId;// varchar(128) NOT NULL COMMENT '授权方企业号id',
    private String corpName;// varchar(256) DEFAULT NULL COMMENT '授权方企业号名称',
    private String corpType;// varchar(64) DEFAULT NULL COMMENT '授权方企业号类型，认证号：verified, 注册号：unverified，体验号：test',
    private String corpRoundLogoUrl;// varchar(256) DEFAULT NULL COMMENT '授权方企业号圆形头像',
    private String corpSquareLogoUrl;// varchar(256) DEFAULT NULL COMMENT '授权方企业号方形头像',
    private String corpUserMax;// varchar(64) DEFAULT NULL COMMENT '授权方企业号用户规模',
    private String corpAgentMax;// varchar(64) DEFAULT NULL COMMENT '授权方企业号应用规模',
    private String corpWxqrcode;// varchar(128) DEFAULT NULL COMMENT '授权方企业号二维码',
    private String email;// varchar(128) DEFAULT NULL COMMENT '管理员邮箱',
    private String mobile;// varchar(128) DEFAULT NULL COMMENT '管理员手机号',
    private String suiteid;// varchar(128) NOT NULL,
}
