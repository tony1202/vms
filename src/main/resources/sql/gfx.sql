-- 用户表
CREATE TABLE gfx_user(
    user_code VARCHAR(20) PRIMARY KEY COMMENT '用户主键',
    user_name VARCHAR(30) NOT NULL COMMENT '用户信息名',
    pass_word VARCHAR(50) NOT NULL COMMENT '密码',
    create_date DATE NOT NULL COMMENT '创建时间',
    updata_date DATE NOT NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='用户表';

-- 角色表

CREATE TABLE gfx_role(
    role_code VARCHAR(20) PRIMARY KEY COMMENT '角色主键',
    role_name VARCHAR(30) NOT NULL COMMENT '角色名称',
    role_desc VARCHAR(50) NOT NULL COMMENT '角色描述',
    create_date DATE NOT NULL COMMENT '创建时间',
    updata_date DATE NOT NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='角色表';

-- 权限表

