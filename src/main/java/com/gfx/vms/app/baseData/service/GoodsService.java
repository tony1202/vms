package com.gfx.vms.app.baseData.service;

import com.gfx.vms.base.dto.Pagination;
import com.gfx.vms.common.entity.Goods;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author tony
 * @date 2018/9/18
 */
public interface GoodsService {

    /**
     * 新增货物
     * @param goods 货物
     */
    String addGoods(Goods goods);

    /**
     * 查询货物列表
     * @param pagination 查询参数
     * @return 分页结果 key:data-货物列表;key:total-查询总数
     */
    Map<String,Object> getGoodsList(Pagination pagination);

    /**
     * 更新货物信息
     * @param goods 货物
     * @return 更新结果
     */
    String updateGoods(Goods goods);
}
