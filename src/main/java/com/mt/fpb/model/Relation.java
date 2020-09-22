package com.mt.fpb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 亲属表(Relation)实体类
 *
 * @author makejava
 * @since 2020-09-22 15:42:19
 */
@Table(name = "`relation`")
public class Relation implements Serializable {
    private static final long serialVersionUID = 339647446792579362L;

    /**
     * 主键自增id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "`id`")
    private Integer id;

    /**
     * 亲属姓名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 亲属电话号码
     */
    @Column(name = "`tel`")
    private String tel;

    /**
     * 身份证号
     */
    @Column(name = "`id_card`")
    private String idCard;

    /**
     * 老人id
     */
    @Column(name = "`oldman_id`")
    private Integer oldmanId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdcard() {
        return idCard;
    }

    public void setIdcard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getOldmanId() {
        return oldmanId;
    }

    public void setOldmanId(Integer oldmanId) {
        this.oldmanId = oldmanId;
    }

}