package com.gfx.vms.app.baseData.service.impl;

import com.gfx.vms.app.baseData.service.GoodsService;
import com.gfx.vms.app.constant.CommonConstant;
import com.gfx.vms.base.dto.GoodsDto;
import com.gfx.vms.base.dto.Pagination;
import com.gfx.vms.common.dao.mapper.GoodsMapper;
import com.gfx.vms.common.entity.Goods;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/18
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    private final static Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);
    private GoodsMapper goodsMapper;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    /**
     * 新增货物
     *
     * @param goods 货物
     */
    @Override
    public String addGoods(Goods goods) {
        String result = "no";
        if (goods!=null){
            if (!checkGoodsExists(goods)){
                goodsMapper.insert(goods);
                result = "ok";
            }
        }
        return result;
    }

    /**
     * 查询货物列表
     *
     * @param pagination 查询参数
     * @return 分页结果 key:data-货物列表;key:total-查询总数
     */
    @Override
    public Map<String, Object> getGoodsList(Pagination pagination) {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        if (pagination.getLimit()<0||pagination.getOffset()<0){
            log.warn("the params is illegal --> limit or offset");
            return null;
        }

        switch (pagination.getSearchType()){
            //查询所有
            case CommonConstant.GoodsConstant.SEARCH_TYPE_ALL :
                break;
            //根据id查询
            case CommonConstant.GoodsConstant.SEARCH_TYPE_ID :
                if (!StringUtils.isNumeric(pagination.getKeyWord())){
                    log.warn("id must be number -->{}",pagination.getKeyWord());
                    return null;
                }
                params.put("id",pagination.getKeyWord());
                break;
            //根据名字查询,支持模糊查询
            case CommonConstant.GoodsConstant.SEARCH_TYPE_NAME :
                params.put("name",pagination.getKeyWord());
                break;
            //其他
            default:break;
        }
        Page page = PageHelper.startPage(pagination.getOffset()+1, pagination.getLimit(), true);
        List<GoodsDto> list = goodsMapper.getGoodsByPage(params);
        result.put("total",page.getTotal());
        result.put("data",list);

        return result;
    }

    /**
     * 更新货物信息
     *
     * @param goods 货物
     * @return 更新结果
     */
    @Override
    public String updateGoods(Goods goods) {
        String result = "no";
        if(!checkGoodsExists(goods)){
            goodsMapper.updateByPrimaryKeySelective(goods);
            result = "ok";
        }
        return result;
    }

    /**
     * 检查货物是否已经存在
     * @param goods 货物
     * @return true 已经存储,false 没有
     */
    private boolean checkGoodsExists(Goods goods){
        long num = goodsMapper.getGoods(goods);
        return num > 0;
    }
}
