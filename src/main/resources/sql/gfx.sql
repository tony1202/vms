CREATE TABLE USER(
    user_id VARCHAR(20) PRIMARY KEY COMMENT '用户主键',
    user_name VARCHAR(30) NOT NULL COMMENT '用户信息名',
    pass_word VARCHAR(50) NOT NULL COMMENT '密码',
    create_date DATE NULL COMMENT '创建时间',
    updata_date DATE NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='用户表';

CREATE TABLE role(
    role_id VARCHAR(20) PRIMARY KEY COMMENT '角色主键',
    role_name VARCHAR(30) NOT NULL COMMENT '角色名称',
    role_desc VARCHAR(50) NOT NULL COMMENT '角色描述',
    create_date DATE NULL COMMENT '创建时间',
    updata_date DATE NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='角色表';

CREATE TABLE permission(
    permission_id VARCHAR(20) PRIMARY KEY COMMENT '权限id',
    permission_name VARCHAR(30) NOT NULL COMMENT '权限名称',
    permission_desc VARCHAR(50) NOT NULL COMMENT '权限描述',
    p_id VARCHAR(20) NULL COMMENT '父id',
    create_date DATE NULL COMMENT '创建时间',
    updata_date DATE NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='权限表';

CREATE TABLE user_role(
    u_id VARCHAR(20) NOT NULL COMMENT '用户id',
    r_id VARCHAR(20) NOT NULL COMMENT '角色id',
    create_date DATE NULL COMMENT '创建时间',
    updata_date DATE NULL COMMENT '更新时间',
    CONSTRAINT pk_id PRIMARY KEY (u_id,r_id),
    CONSTRAINT fk__u_id FOREIGN KEY (u_id) REFERENCES USER(user_id),
    CONSTRAINT fk_fav_r_id FOREIGN KEY (r_id) REFERENCES role(role_id)
    )CHARSET=utf8 COMMENT='用户角色表';

 CREATE TABLE role_permission(
    p_id VARCHAR(20) NOT NULL COMMENT '权限id',
    r_id VARCHAR(20) NOT NULL COMMENT '角色id',
    create_date DATE NULL COMMENT '创建时间',
    updata_date DATE NULL COMMENT '更新时间',
    CONSTRAINT pk_rp_id PRIMARY KEY (p_id,r_id),
    CONSTRAINT fk_rp_p_id FOREIGN KEY (p_id) REFERENCES permission(permission_id),
    CONSTRAINT fk_rp_r_id FOREIGN KEY (r_id) REFERENCES role(role_id)
    )CHARSET=utf8 COMMENT='角色权限表';

