package com.gfx.vms.common.dao.mapper;

import com.gfx.vms.common.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@Repository
public interface CustomerMapper extends Mapper<Customer> {

    List<Customer> getCustomerList(@Param("params") Map<String, Object> params);
}