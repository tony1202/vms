package com.gfx.vms.app.baseData.service.impl;

import com.gfx.vms.app.baseData.service.CustomerService;
import com.gfx.vms.app.constant.CommonConstant;
import com.gfx.vms.base.dto.Pagination;
import com.gfx.vms.common.dao.mapper.CustomerMapper;
import com.gfx.vms.common.entity.Customer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/21
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    /**
     * 分页查询客户信息
     *
     * @param pagination 分页条件
     * @param userId     用户id
     * @param isAdmin    是否admin
     * @return 分页数据 key:total - 总条数;key:data - 数据列
     */
    @Override
    public Map<String, Object> getCustomerList(Pagination pagination, String userId, String isAdmin) {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("isAdmin",isAdmin);
        params.put("userId",userId);
        if (pagination.getOffset()<0||pagination.getLimit()<0){
            log.warn("pagination is error ->{}",pagination);
            return null;
        }

        switch (pagination.getSearchType()){
            case CommonConstant.CustomerConstant.SEARCH_TYPE_ALL:
                break;
            case CommonConstant.CustomerConstant.SEARCH_TYPE_ID:
                params.put("id",pagination.getKeyWord());
                break;
            case CommonConstant.CustomerConstant.SEARCH_TYPE_NAME:
                params.put("name",pagination.getKeyWord());
                break;
            default: break;
        }
        Page<Object> page = PageHelper.startPage(pagination.getOffset(), pagination.getLimit(), true);
        List<Customer> list = customerMapper.getCustomerList(params);
        result.put("total",page.getTotal());
        result.put("data",list);
        return result;
    }
}
