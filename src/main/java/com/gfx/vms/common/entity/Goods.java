package com.gfx.vms.common.entity;

import javax.persistence.*;

@Table(name = "goods")
public class Goods {
    /**
     * 货物id
     */
    @Id
    private Integer id;

    /**
     * 货物名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 货物类型
     */
    @Column(name = "TYPE")
    private Integer type;

    /**
     * 货物规格
     */
    private String size;

    /**
     * 货物价值
     */
    @Column(name = "VALUE")
    private Double value;

    /**
     * 获取货物id
     *
     * @return id - 货物id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置货物id
     *
     * @param id 货物id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取货物名
     *
     * @return NAME - 货物名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置货物名
     *
     * @param name 货物名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取货物类型
     *
     * @return TYPE - 货物类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置货物类型
     *
     * @param type 货物类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取货物规格
     *
     * @return size - 货物规格
     */
    public String getSize() {
        return size;
    }

    /**
     * 设置货物规格
     *
     * @param size 货物规格
     */
    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    /**
     * 获取货物价值
     *
     * @return VALUE - 货物价值
     */
    public Double getValue() {
        return value;
    }

    /**
     * 设置货物价值
     *
     * @param value 货物价值
     */
    public void setValue(Double value) {
        this.value = value;
    }
}