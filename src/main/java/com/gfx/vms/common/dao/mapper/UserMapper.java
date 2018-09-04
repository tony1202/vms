package com.gfx.vms.common.dao.mapper;

import com.gfx.vms.common.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserMapper extends Mapper<User> {
}