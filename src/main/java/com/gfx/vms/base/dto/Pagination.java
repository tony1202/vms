package com.gfx.vms.base.dto;

import java.io.Serializable;

/**
 * @author tony
 * @date 2018/9/18
 */
public class Pagination implements Serializable {

    private static final long serialVersionUID = -131128495747201581L;
    /**分页大小*/
    private int limit;
    /**偏移量*/
    private int offset;
    /**查询类型*/
    private String searchType;
    /**查询关键字*/
    private String keyWord;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
