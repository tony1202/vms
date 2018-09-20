package com.gfx.vms.base.dto;

import com.gfx.vms.common.entity.Goods;

/**
 * @author tony
 * @date 2018/9/19
 */
public class GoodsDto extends Goods {
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
