package com.gfx.vms.common.dao.mapper;

import com.gfx.vms.common.entity.Dir;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DirMapper extends Mapper<Dir> {
    List<Dir> getListDirByType(String type);
}