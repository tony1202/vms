package com.gfx.vms.common.dao.mapper;

import com.gfx.vms.common.entity.Repository;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@org.springframework.stereotype.Repository
public interface RepositoryMapper extends Mapper<Repository> {

    List<Repository> getRepositoryList(@Param("params") Map<String, Object> params);

    Integer isExists(@Param("repository") Repository repository);

}