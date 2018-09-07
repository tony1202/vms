package com.gfx.vms.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "role_permission")
public class RolePermission {
    /**
     * 权限id
     */
    @Id
    @Column(name = "p_id")
    private String pId;

    /**
     * 角色id
     */
    @Id
    @Column(name = "r_id")
    private String rId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 获取权限id
     *
     * @return p_id - 权限id
     */
    public String getpId() {
        return pId;
    }

    /**
     * 设置权限id
     *
     * @param pId 权限id
     */
    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    /**
     * 获取角色id
     *
     * @return r_id - 角色id
     */
    public String getrId() {
        return rId;
    }

    /**
     * 设置角色id
     *
     * @param rId 角色id
     */
    public void setrId(String rId) {
        this.rId = rId == null ? null : rId.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}