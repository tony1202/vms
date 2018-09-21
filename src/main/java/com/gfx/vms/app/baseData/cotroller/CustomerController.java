package com.gfx.vms.app.baseData.cotroller;

import com.gfx.vms.app.baseData.service.CustomerService;
import com.gfx.vms.app.constant.CommonConstant;
import com.gfx.vms.base.context.UserContextHolder;
import com.gfx.vms.base.dto.Pagination;
import com.gfx.vms.base.dto.UserInfoDto;
import com.gfx.vms.base.dto.VMSResponse;
import com.gfx.vms.base.dto.VMSResponseFactory;
import com.gfx.vms.common.entity.Customer;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/21
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 获取客户列表
     * @param pagination 分页数据
     * @return 客户列表
     */
    @GetMapping("/getCustomerList")
    @RequiresAuthentication
    public Map<String,Object> getCustomerList(Pagination pagination){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        String isAdmin = "N";
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            UserInfoDto userInfo = UserContextHolder.getUserInfo();
            String userId = userInfo.getUserId();
            List<String> roles = userInfo.getRoles();
            if (roles.contains(CommonConstant.RoleConstant.ADMIN)){
                isAdmin = "Y";
            }
            Map<String,Object> customers = customerService.getCustomerList(pagination,userId,isAdmin);
            if (MapUtils.isNotEmpty(customers)){
                List<Customer> rows = (List<Customer>) customers.get("data");
                vmsResponse.setCustomerInfo("rows",rows);
                vmsResponse.setResponseBodyTotal((Long) customers.get("total"));
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("customer list is error ->{}",e);
        }

        return vmsResponse.generateResponseBody();
    }

}
