package com.gfx.vms.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dir")
public class Dir {
    /**
     * 字典id
     */
    @Id
    private Integer id;

    /**
     * 字典名
     */
    private String name;

    /**
     * 字典类型:00-货物;01--仓库;02--进出仓;03--人事管理
     */
    private String type;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 获取字典id
     *
     * @return id - 字典id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置字典id
     *
     * @param id 字典id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取字典名
     *
     * @return name - 字典名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字典名
     *
     * @param name 字典名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取字典类型:00-货物;01--仓库;02--进出仓;03--人事管理
     *
     * @return type - 字典类型:00-货物;01--仓库;02--进出仓;03--人事管理
     */
    public String getType() {
        return type;
    }

    /**
     * 设置字典类型:00-货物;01--仓库;02--进出仓;03--人事管理
     *
     * @param type 字典类型:00-货物;01--仓库;02--进出仓;03--人事管理
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
}