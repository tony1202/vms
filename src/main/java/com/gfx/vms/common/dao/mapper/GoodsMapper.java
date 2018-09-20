package com.gfx.vms.common.dao.mapper;

import com.gfx.vms.base.dto.GoodsDto;
import com.gfx.vms.common.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@Repository
public interface GoodsMapper extends Mapper<Goods> {
    /**
     * 分页查询货物
     * @param params 分页条件
     * @return 货物集合
     */
    List<GoodsDto> getGoodsByPage(@Param("params") Map<String, Object> params);

    /**
     * 检查goods是否已经存储
     * @param goods
     * @return
     */
    Long getGoods(@Param("goods") Goods goods);
}