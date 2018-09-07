package com.gfx.vms.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "access_record")
public class AccessRecord {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 登录用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 登录类型:登入或登出
     */
    @Column(name = "access_type")
    private String accessType;

    /**
     * 用户访问ip
     */
    @Column(name = "access_ip")
    private String accessIp;

    /**
     * 访问时间
     */
    @Column(name = "access_date")
    private Date accessDate;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录用户id
     *
     * @return user_id - 登录用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置登录用户id
     *
     * @param userId 登录用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取登录类型:登入或登出
     *
     * @return access_type - 登录类型:登入或登出
     */
    public String getAccessType() {
        return accessType;
    }

    /**
     * 设置登录类型:登入或登出
     *
     * @param accessType 登录类型:登入或登出
     */
    public void setAccessType(String accessType) {
        this.accessType = accessType == null ? null : accessType.trim();
    }

    /**
     * 获取用户访问ip
     *
     * @return access_ip - 用户访问ip
     */
    public String getAccessIp() {
        return accessIp;
    }

    /**
     * 设置用户访问ip
     *
     * @param accessIp 用户访问ip
     */
    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp == null ? null : accessIp.trim();
    }

    /**
     * 获取访问时间
     *
     * @return access_date - 访问时间
     */
    public Date getAccessDate() {
        return accessDate;
    }

    /**
     * 设置访问时间
     *
     * @param accessDate 访问时间
     */
    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }
}