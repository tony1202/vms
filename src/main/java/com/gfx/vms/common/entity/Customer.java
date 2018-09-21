package com.gfx.vms.common.entity;

import javax.persistence.*;

@Table(name = "customer")
public class Customer {
    /**
     * 客户id
     */
    @Id
    private Integer id;

    /**
     * 客户名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 客户方联系人
     */
    @Column(name = "link_man")
    private String linkMan;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 业务员id
     */
    @Column(name = "sale_man")
    private String saleMan;

    /**
     * 获取客户id
     *
     * @return id - 客户id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置客户id
     *
     * @param id 客户id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取客户名
     *
     * @return NAME - 客户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置客户名
     *
     * @param name 客户名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取客户方联系人
     *
     * @return link_man - 客户方联系人
     */
    public String getLinkMan() {
        return linkMan;
    }

    /**
     * 设置客户方联系人
     *
     * @param linkMan 客户方联系人
     */
    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取电子邮箱
     *
     * @return email - 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取业务员id
     *
     * @return sale_man - 业务员id
     */
    public String getSaleMan() {
        return saleMan;
    }

    /**
     * 设置业务员id
     *
     * @param saleMan 业务员id
     */
    public void setSaleMan(String saleMan) {
        this.saleMan = saleMan == null ? null : saleMan.trim();
    }
}