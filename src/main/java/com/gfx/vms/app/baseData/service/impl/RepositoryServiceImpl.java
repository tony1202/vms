package com.gfx.vms.app.baseData.service.impl;

import com.gfx.vms.app.baseData.service.RepositoryService;
import com.gfx.vms.app.constant.CommonConstant;
import com.gfx.vms.base.dto.GoodsDto;
import com.gfx.vms.base.dto.Pagination;
import com.gfx.vms.common.dao.mapper.RepositoryMapper;
import com.gfx.vms.common.entity.Repository;
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
 * @date 2018/9/20
 */
@Service
public class RepositoryServiceImpl implements RepositoryService {

    private static final Logger log = LoggerFactory.getLogger(RepositoryServiceImpl.class);

    private RepositoryMapper repositoryMapper;

    @Autowired
    public RepositoryServiceImpl(RepositoryMapper repositoryMapper) {
        this.repositoryMapper = repositoryMapper;
    }

    /**
     * 分页查询仓库数据列表
     *
     * @param pagination 分页数据
     * @return 数据列表 key:data-货物列表;key:total-查询总数
     */
    @Override
    public Map<String, Object> getRepositoryList(Pagination pagination) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        if (pagination.getLimit() < 0 || pagination.getOffset() < 0) {
            log.warn("the params is illegal --> limit or offset");
            return null;
        }

        switch (pagination.getSearchType()) {
            //查询所有
            case CommonConstant.RepositoryConstant.SEARCH_TYPE_ALL:
                break;
            //根据id查询
            case CommonConstant.RepositoryConstant.SEARCH_TYPE_ID:
                if (!StringUtils.isNumeric(pagination.getKeyWord())) {
                    log.warn("id must be number -->{}", pagination.getKeyWord());
                    return null;
                }
                params.put("id", pagination.getKeyWord());
                break;
            //根据名字查询,支持模糊查询
            case CommonConstant.RepositoryConstant.SEARCH_TYPE_Address:
                params.put("address", pagination.getKeyWord());
                break;
            //其他
            default:
                break;
        }
        Page page = PageHelper.startPage(pagination.getOffset() + 1, pagination.getLimit(), true);
        List<Repository> list = repositoryMapper.getRepositoryList(params);
        result.put("total", page.getTotal());
        result.put("data", list);

        return result;
    }

    /**
     * 新增仓库数据
     *
     * @param repository 仓库数据
     * @return 新增结果
     */
    @Override
    public String addRepository(Repository repository) {
        String result = "no";
        if (!isExists(repository)){
            repositoryMapper.insertSelective(repository);
            result = "ok";
        }
        return result;
    }

    /**
     * 检查是否已经存在了
     *
     * @param repository
     * @return
     */
    private boolean isExists(Repository repository) {
        int num = repositoryMapper.isExists(repository);
        return num > 0;
    }
}
