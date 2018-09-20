package com.gfx.vms.app.baseData.service;

import com.gfx.vms.base.dto.Pagination;
import com.gfx.vms.common.entity.Repository;

import java.util.Map;

/**
 * @author tony
 * @date 2018/9/20
 */
public interface RepositoryService {
    /**
     * 分页查询仓库数据列表
     * @param pagination 分页数据
     * @return 数据列表 key:data-货物列表;key:total-查询总数
     */
    Map<String,Object> getRepositoryList(Pagination pagination);

    /**
     * 新增仓库数据
     * @param repository 仓库数据
     * @return 新增结果
     */
    String addRepository(Repository repository);
}
