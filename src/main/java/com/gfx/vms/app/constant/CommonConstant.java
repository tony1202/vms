package com.gfx.vms.app.constant;

/**
 * 通用常量
 * @author tony
 * @date 2018/9/19
 */
public abstract class CommonConstant {
    /**
     * 货物常量
     */
    public interface GoodsConstant{
        /**查询所有*/
        String SEARCH_TYPE_ALL = "searchAll";
        /**根据id*/
        String SEARCH_TYPE_ID = "searchByID";
        /**根据name*/
        String SEARCH_TYPE_NAME = "searchByName";
    }

    /**
     * 仓库常量
     */
    public interface RepositoryConstant{
        /**查询所有*/
        String SEARCH_TYPE_ALL = "searchAll";
        /**根据id*/
        String SEARCH_TYPE_ID = "searchByID";
        /**根据name*/
        String SEARCH_TYPE_Address = "searchByAddress";
    }

    /**
     * 角色常量
     */
    public interface RoleConstant{
        /**系统管理员*/
        String ADMIN = "admin";
    }

    /**
     * 客户常量
     */
    public interface CustomerConstant{
        /**查询所有*/
        String SEARCH_TYPE_ALL = "searchAll";
        /**根据id*/
        String SEARCH_TYPE_ID = "searchByID";
        /**根据客户名字*/
        String SEARCH_TYPE_NAME = "searchByName";
    }
}
