package com.gfx.vms.app.baseData.cotroller;

import com.gfx.vms.app.baseData.service.RepositoryService;
import com.gfx.vms.base.dto.Pagination;
import com.gfx.vms.base.dto.VMSResponse;
import com.gfx.vms.base.dto.VMSResponseFactory;
import com.gfx.vms.common.entity.Repository;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 仓库基础数据
 * @author tony
 * @date 2018/9/20
 */
@RestController
@RequestMapping("/repository")
public class RepositoryController {

    private final static Logger log = LoggerFactory.getLogger(RepositoryController.class);
    private RepositoryService repositoryService;

    @Autowired
    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    /**
     * 仓库数据列表
     * @return
     */
    @GetMapping("/getRepositoryList")
    public Map<String,Object> getRepositoryList(Pagination pagination){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);

        try {
            Map<String, Object> repositoryList = repositoryService.getRepositoryList(pagination);
            if (MapUtils.isNotEmpty(repositoryList)){
                vmsResponse.setResponseBodyTotal((Long) repositoryList.get("total"));
                List<Repository> rows = (List<Repository>) repositoryList.get("data");
                vmsResponse.setCustomerInfo("rows",rows);
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("repository list in error -->{}",e);
        }


        return vmsResponse.generateResponseBody();
    }


    /**
     * 新增仓库数据
     * @return 添加结果
     */
    @PostMapping("/addRepository")
    public Map<String,Object> addRepository(@RequestBody Repository repository){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            String res = repositoryService.addRepository(repository);
            if (StringUtils.equalsIgnoreCase("ok",res)){
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("add repository error -->{}",e);
        }
        return vmsResponse.generateResponseBody();
    }
}
