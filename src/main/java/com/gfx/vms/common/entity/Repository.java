package com.gfx.vms.common.entity;

import javax.persistence.*;

@Table(name = "repository")
public class Repository {
    /**
     * 仓库id
     */
    @Id
    private Integer id;

    /**
     * 仓库地址
     */
    private String address;

    /**
     * 仓库状态:0 正常;1 异常
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 仓库描述
     */
    private String description;

    /**
     * 仓库面积:单位平方米
     */
    @Column(name = "AREA")
    private Float area;

    /**
     * 获取仓库id
     *
     * @return id - 仓库id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置仓库id
     *
     * @param id 仓库id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取仓库地址
     *
     * @return address - 仓库地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置仓库地址
     *
     * @param address 仓库地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取仓库状态:0 正常;1 异常
     *
     * @return STATUS - 仓库状态:0 正常;1 异常
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置仓库状态:0 正常;1 异常
     *
     * @param status 仓库状态:0 正常;1 异常
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取仓库描述
     *
     * @return description - 仓库描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置仓库描述
     *
     * @param description 仓库描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取仓库面积:单位平方米
     *
     * @return AREA - 仓库面积:单位平方米
     */
    public Float getArea() {
        return area;
    }

    /**
     * 设置仓库面积:单位平方米
     *
     * @param area 仓库面积:单位平方米
     */
    public void setArea(Float area) {
        this.area = area;
    }
}