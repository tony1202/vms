package com.gfx.vms.base.service.impl;

import com.gfx.vms.base.service.SystemLogService;
import com.gfx.vms.common.dao.mapper.AccessRecordMapper;
import com.gfx.vms.common.entity.AccessRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tony
 * @date 2018/9/6
 * @Description:
 */
@Service
public class SystemLogServiceImpl implements SystemLogService{

    @Autowired
    private AccessRecordMapper accessRecordMapper;

    /**
     * 新增用户访问记录
     *
     * @param userId     用户id
     * @param accessIp   用户ip
     * @param accessType 访问类型
     */
    @Override
    public void addAccessRecord(String userId, String accessIp, String accessType) {
        AccessRecord accessRecord = new AccessRecord();
        accessRecord.setAccessDate(new Date());
        accessRecord.setAccessIp(accessIp);
        accessRecord.setAccessType(accessType);
        accessRecord.setUserId(userId);
        accessRecordMapper.insertSelective(accessRecord);
    }
}
