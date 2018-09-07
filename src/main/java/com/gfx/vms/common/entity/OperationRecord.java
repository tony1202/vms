package com.gfx.vms.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "operation_record")
public class OperationRecord {
    /**
     * 记录id
     */
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 操作的名称
     */
    @Column(name = "operation_name")
    private String operationName;

    /**
     * 操作结果
     */
    @Column(name = "operation_reslut")
    private String operationReslut;

    /**
     * 操作时间
     */
    @Column(name = "operation_date")
    private Date operationDate;

    /**
     * 获取记录id
     *
     * @return id - 记录id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置记录id
     *
     * @param id 记录id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取操作的名称
     *
     * @return operation_name - 操作的名称
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * 设置操作的名称
     *
     * @param operationName 操作的名称
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    /**
     * 获取操作结果
     *
     * @return operation_reslut - 操作结果
     */
    public String getOperationReslut() {
        return operationReslut;
    }

    /**
     * 设置操作结果
     *
     * @param operationReslut 操作结果
     */
    public void setOperationReslut(String operationReslut) {
        this.operationReslut = operationReslut == null ? null : operationReslut.trim();
    }

    /**
     * 获取操作时间
     *
     * @return operation_date - 操作时间
     */
    public Date getOperationDate() {
        return operationDate;
    }

    /**
     * 设置操作时间
     *
     * @param operationDate 操作时间
     */
    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }
}