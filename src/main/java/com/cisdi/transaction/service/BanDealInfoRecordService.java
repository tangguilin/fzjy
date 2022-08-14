package com.cisdi.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cisdi.transaction.domain.model.BanDealInfo;
import com.cisdi.transaction.domain.model.BanDealInfoRecord;

import java.util.List;

/**
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/4 11:11
 */
public interface BanDealInfoRecordService extends IService<BanDealInfoRecord> {
    /**
     * 新增有效的禁止交易操作记录
     * @param infoList 禁止交易数据
     * @param operateType 操作类型
     * @return
     */
    boolean insertBanDealInfoRecord(List<BanDealInfo> infoList,String operateType);
}
