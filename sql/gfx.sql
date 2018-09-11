CREATE TABLE USER(
    user_id VARCHAR(50) PRIMARY KEY COMMENT '用户主键',
    user_name VARCHAR(50) NOT NULL COMMENT '用户信息名',
    pass_word VARCHAR(50) NOT NULL COMMENT '密码',
    create_date DATE NULL COMMENT '创建时间',
    update_date DATE NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='用户表';

CREATE TABLE role(
    role_id VARCHAR(50) PRIMARY KEY COMMENT '角色主键',
    role_name VARCHAR(50) NOT NULL COMMENT '角色名称',
    role_desc VARCHAR(50) NOT NULL COMMENT '角色描述',
    create_date DATE NULL COMMENT '创建时间',
    update_date DATE NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='角色表';

CREATE TABLE permission(
    permission_id VARCHAR(50) PRIMARY KEY COMMENT '权限id',
    permission_name VARCHAR(50) NOT NULL COMMENT '权限名称',
    permission_desc VARCHAR(50) NOT NULL COMMENT '权限描述',
    p_id VARCHAR(50) NULL COMMENT '父id',
    create_date DATE NULL COMMENT '创建时间',
    update_date DATE NULL COMMENT '更新时间'
)CHARSET=utf8 COMMENT='权限表';

CREATE TABLE user_role(
    u_id VARCHAR(50) NOT NULL COMMENT '用户id',
    r_id VARCHAR(50) NOT NULL COMMENT '角色id',
    create_date DATE NULL COMMENT '创建时间',
    update_date DATE NULL COMMENT '更新时间',
    CONSTRAINT pk_id PRIMARY KEY (u_id,r_id),
    CONSTRAINT fk__u_id FOREIGN KEY (u_id) REFERENCES USER(user_id),
    CONSTRAINT fk_fav_r_id FOREIGN KEY (r_id) REFERENCES role(role_id)
)CHARSET=utf8 COMMENT='用户角色表';

 CREATE TABLE role_permission(
    p_id VARCHAR(50) NOT NULL COMMENT '权限id',
    r_id VARCHAR(50) NOT NULL COMMENT '角色id',
    create_date DATE NULL COMMENT '创建时间',
    update_date DATE NULL COMMENT '更新时间',
    CONSTRAINT pk_rp_id PRIMARY KEY (p_id,r_id),
    CONSTRAINT fk_rp_p_id FOREIGN KEY (p_id) REFERENCES permission(permission_id),
    CONSTRAINT fk_rp_r_id FOREIGN KEY (r_id) REFERENCES role(role_id)
 )CHARSET=utf8 COMMENT='角色权限表';

 -- 用户登录登出表
 CREATE TABLE access_record(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    user_id VARCHAR(50) NOT NULL COMMENT '登录用户id',
    access_type VARCHAR(20) NOT NULL COMMENT '登录类型:登入或登出',
    access_ip VARCHAR(50) COMMENT '用户访问ip',
    access_date DATE COMMENT '访问时间'
)CHARSET=utf8 COMMENT='用户登录登出记录表';

 --用户操作记录表
CREATE TABLE operation_record(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '记录id',
    user_id VARCHAR(50) NOT NULL COMMENT '用户id',
    operation_name VARCHAR(50) NOT NULL COMMENT '操作的名称',
    operation_reslut VARCHAR(50) NOT NULL COMMENT '操作结果',
    operation_date DATE COMMENT '操作时间'
)CHARSET=utf8 COMMENT '用户操作记录表';

