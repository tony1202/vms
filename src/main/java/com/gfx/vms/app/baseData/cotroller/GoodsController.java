package com.gfx.vms.app.baseData.cotroller;

import com.gfx.vms.app.baseData.service.GoodsService;
import com.gfx.vms.base.dto.GoodsDto;
import com.gfx.vms.base.dto.Pagination;
import com.gfx.vms.base.dto.VMSResponse;
import com.gfx.vms.base.dto.VMSResponseFactory;
import com.gfx.vms.common.entity.Goods;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/18
 * @Description:
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);
    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 查询商品列表
     * @return 分页数据
     */
    @GetMapping("/getGoodsList")
    public Map<String,Object> getGoodsList(Pagination pagination){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            Map<String, Object> goodsList = goodsService.getGoodsList(pagination);
            if (goodsList!=null){
                List<GoodsDto> rows = (List<GoodsDto>) goodsList.get("data");
                vmsResponse.setCustomerInfo("rows",rows);
                vmsResponse.setResponseBodyTotal((Long) goodsList.get("total"));
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("goodsList is error -->{}",e.getMessage());
        }
        return vmsResponse.generateResponseBody();
    }


    @PostMapping("/addGoods")
    public Map<String, Object> addGoods(@RequestBody Goods goods){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        try {
            String res = goodsService.addGoods(goods);
            if (StringUtils.equalsIgnoreCase("ok",res)){
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }else {
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
            }
        } catch (Exception e) {
            log.info("add goods error ->{}",e);
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        }

        return vmsResponse.generateResponseBody();
    }

    /**
     * 更新货物
     * @param goods 货物
     * @return
     */
    @PostMapping("/updateGoods")
    public Map<String,Object> updateGoods(@RequestBody Goods goods){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        if (goods!=null){
            String res = goodsService.updateGoods(goods);
            if (StringUtils.equalsIgnoreCase("ok",res)){
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }else {

                vmsResponse.setResponseBodyMsg("货物已存在");
            }
        }
        return vmsResponse.generateResponseBody();
    }

}
