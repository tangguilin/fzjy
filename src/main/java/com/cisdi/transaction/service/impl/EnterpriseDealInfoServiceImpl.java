package com.cisdi.transaction.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cisdi.transaction.constant.SqlConstant;
import com.cisdi.transaction.domain.dto.BusinessTransactionDTO;
import com.cisdi.transaction.domain.model.BanDealInfo;
import com.cisdi.transaction.domain.model.EnterpriseDealInfo;
import com.cisdi.transaction.domain.model.SysDictBiz;
import com.cisdi.transaction.domain.vo.BusinessTransactionExcelVO;
import com.cisdi.transaction.mapper.master.EnterpriseDealInfoMapper;
import com.cisdi.transaction.service.BanDealInfoService;
import com.cisdi.transaction.service.EnterpriseDealInfoService;
import com.cisdi.transaction.service.SysDictBizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/3 16:59
 */
@Service
@Slf4j
public class EnterpriseDealInfoServiceImpl extends ServiceImpl<EnterpriseDealInfoMapper, EnterpriseDealInfo> implements EnterpriseDealInfoService {

    @Autowired
    private SysDictBizService sysDictBizService;
    @Autowired
    private BanDealInfoService banDealInfoService;

    @Override
    public void saveInfo(BusinessTransactionDTO dto) {
        List<BanDealInfo> infoList = banDealInfoService.lambdaQuery().eq(BanDealInfo::getCode, dto.getCode()).list();
        if (!infoList.isEmpty()){
            List<EnterpriseDealInfo> collect = infoList.stream().map(t -> {
                EnterpriseDealInfo info = new EnterpriseDealInfo();
                BeanUtils.copyProperties(dto, info);
                return info.setCardId(t.getCardId())
                        .setName(t.getName())
                        .setCompany(t.getCompany())
                        .setPost(t.getPost())
                        .setPostType(t.getPostType())
                        .setBanPostType(t.getBanPostType())
                        .setFamilyName(t.getFamilyName())
                        .setRelation(t.getRelation())
                        .setEngageType(t.getEngageType())
                        .setEngageInfo(t.getEngageInfo())
                        .setOperatScope(t.getOperatScope())
                        .setCreateTime(DateUtil.date())
                        .setUpdateTime(DateUtil.date());
            }).collect(Collectors.toList());
            this.saveBatch(collect);
        }else {
            log.info("禁止交易表中未匹配到对应信息，故舍弃掉数据");
        }

    }

    @Override
    public List<BusinessTransactionExcelVO> export(List<String> ids) {
        List<SysDictBiz> dictList = sysDictBizService.selectList();
        List<BusinessTransactionExcelVO> list = this.baseMapper.selectBatchIds(ids).stream().map(t -> {
            BusinessTransactionExcelVO vo = new BusinessTransactionExcelVO();
            BeanUtils.copyProperties(t, vo);
            return vo;
        }).collect(Collectors.toList());
        list = this.replaceDictValue(list,dictList);
        return list;
    }

    private List<BusinessTransactionExcelVO> replaceDictValue(List<BusinessTransactionExcelVO> list,List<SysDictBiz> dictList){
        list.parallelStream().forEach(vo->{
            String postType = sysDictBizService.getDictValue(vo.getPostType(),dictList);
            String relation = sysDictBizService.getDictValue(vo.getRelation(),dictList);
            String engageType = sysDictBizService.getDictValue(vo.getEngageType(),dictList);
            String infoType = sysDictBizService.getDictValue(vo.getInfoType(),dictList);

            vo.setPostType(postType);
            vo.setBanPostType(postType);
            vo.setRelation(relation);
            vo.setEngageType(engageType);
            vo.setInfoType(infoType);
        });
        return list;
    }
}
