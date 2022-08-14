package com.cisdi.transaction.service;

import com.cisdi.transaction.domain.model.GbBasicInfo;
import com.cisdi.transaction.domain.model.GbOrgInfo;

import java.util.List;

/**
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/5 23:47
 */
public interface GbOrgService {

    public List<GbOrgInfo> selectGbOrgInfon(List<GbBasicInfo> gbList);
}
