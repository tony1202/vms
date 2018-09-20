package com.gfx.vms.app.baseData.service;

import com.gfx.vms.common.entity.Dir;

import java.util.List;

/**
 * @author tony
 * @date 2018/9/18
 */
public interface DirectoryService {
    /**
     * 根据字典类型获取字典数据集合
     * @param type 字典集合
     * @return
     */
    List<Dir> getListDir(String type);
}
