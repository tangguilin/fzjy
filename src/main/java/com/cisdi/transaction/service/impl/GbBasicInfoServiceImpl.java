package com.cisdi.transaction.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cisdi.transaction.constant.SqlConstant;
import com.cisdi.transaction.domain.dto.CadreDTO;
import com.cisdi.transaction.domain.dto.CadreFamilyExportDto;
import com.cisdi.transaction.domain.model.*;
import com.cisdi.transaction.domain.vo.BusinessTransactionExcelVO;
import com.cisdi.transaction.domain.vo.CadreExcelVO;
import com.cisdi.transaction.mapper.master.GbBasicInfoMapper;
import com.cisdi.transaction.service.GbBasicInfoService;
import com.cisdi.transaction.service.GbBasicInfoThreeService;
import com.cisdi.transaction.service.SysDictBizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/3 14:36
 */
@Service
@Slf4j
public class GbBasicInfoServiceImpl extends ServiceImpl<GbBasicInfoMapper, GbBasicInfo> implements GbBasicInfoService {

    @Autowired
    private GbBasicInfoThreeService gbBasicInfoThreeService;

    @Autowired
    private SysDictBizService sysDictBizService;

    @Override
    public List<GbBasicInfo> selectByName(String name) {
        List<GbBasicInfo> list = this.lambdaQuery().like(GbBasicInfo::getName, name).list();
        return list;
    }


    @Override
    public void saveInfo(CadreDTO dto) {
        GbBasicInfo entity = this.lambdaQuery().eq(GbBasicInfo::getCardId, dto.getCardId()).last(SqlConstant.ONE_SQL).one();
        if (entity == null) {
            GbBasicInfo info = new GbBasicInfo();
            BeanUtils.copyProperties(dto, info);
            info.setCreateTime(DateUtil.date());
            info.setUpdateTime(DateUtil.date());


            info.setTenantId(dto.getServiceLesseeId());
            info.setCreatorId(dto.getServiceUserId());
            this.save(info);
        }
    }

    @Override
    public List<GbBasicInfo> selectBatchByCardIds(List<String> cardIds) {
        if(CollectionUtil.isEmpty(cardIds)){
            return null;
        }
        List<GbBasicInfo> list = this.lambdaQuery().in(GbBasicInfo::getCardId, cardIds).list();
        return list;
    }

    @Override
    public void syncData() {
        //删除所有数据重新添加

        this.baseMapper.delete(null);
        List<GbBasicInfo> dataList = new ArrayList<>();
        List<GbBasicInfoThree> gbBasicInfoThrees = gbBasicInfoThreeService.selectGbBasicInfo();
        for (GbBasicInfoThree gbBasicInfoThree : gbBasicInfoThrees) {
            GbBasicInfo info = new GbBasicInfo();
            BeanUtil.copyProperties(gbBasicInfoThree, info);
            info.setCreateTime(DateUtil.date());
            info.setUpdateTime(DateUtil.date());
            dataList.add(info);
        }
        List<SysDictBiz> dictList = sysDictBizService.selectList();
        dataList = this.repalceDictId(dataList,dictList);
        this.saveBatch(dataList);
    }

    @Override
    public List<CadreExcelVO> export(List<String> ids) {
        List<SysDictBiz> dictList = sysDictBizService.selectList();
        //字典转换
        List<CadreExcelVO> list =  this.baseMapper.selectBatchIds(ids).stream().map(t -> {
            CadreExcelVO vo = new CadreExcelVO();
            BeanUtils.copyProperties(t, vo);
            return vo;
        }).collect(Collectors.toList());
        list = this.replaceDictValue(list,dictList);
        return list;
    }

    @Override
    public List<GbOrgInfo> selectGbOrgInfoByCardIds(List<String> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return null;
        }
        return  this.baseMapper.selectByCardIds(ids);
    }

    private List<CadreExcelVO> replaceDictValue(List<CadreExcelVO> list, List<SysDictBiz> dictList){
        list.parallelStream().forEach(vo->{
            String postType =sysDictBizService.getDictValue(vo.getPostType(),dictList);
            String allOtType = sysDictBizService.getDictValue(vo.getAllotType(),dictList);
            vo.setPostType(postType);
            vo.setAllotType(allOtType);
        });
        return list;
    }

    private List<GbBasicInfo>  repalceDictId(List<GbBasicInfo> list, List<SysDictBiz> dictList){
        list.parallelStream().forEach(dto->{
            //字典对应项
            String postType = sysDictBizService.getDictId(dto.getPostType(), dictList);

            dto.setPostType(postType);

        });
        return list;
    }
}
