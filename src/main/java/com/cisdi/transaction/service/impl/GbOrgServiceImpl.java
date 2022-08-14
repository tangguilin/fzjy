package com.cisdi.transaction.service.impl;

import com.cisdi.transaction.domain.model.GbBasicInfo;
import com.cisdi.transaction.domain.model.GbOrgInfo;
import com.cisdi.transaction.service.GbOrgService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 干部组织信息
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/5 23:47
 */
@Service
public class GbOrgServiceImpl implements GbOrgService {
    @Override
    public List<GbOrgInfo> selectGbOrgInfon(List<GbBasicInfo> gbList) {
        //查询每个干部对于的单位 部门信息
        //gbList.s

        return null;
    }
}
