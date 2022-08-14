package com.cisdi.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cisdi.transaction.constant.SqlConstant;
import com.cisdi.transaction.domain.model.GlobalCityInfo;
import com.cisdi.transaction.domain.vo.RegionDropDownBoxVO;
import com.cisdi.transaction.mapper.master.GlobalCityInfoMapper;
import com.cisdi.transaction.service.GlobalCityInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/9 17:10
 */
@Service
@Slf4j
public class GlobalCityInfoServiceImpl extends ServiceImpl<GlobalCityInfoMapper, GlobalCityInfo> implements GlobalCityInfoService {

    @Override
    public List<RegionDropDownBoxVO> regionDropDownBox() {
        List<GlobalCityInfo> list = this.list();
        //第一级
        List<GlobalCityInfo> collect = list.stream().filter(t -> StringUtils.isBlank(t.getParentId())).collect(Collectors.toList());
        List<GlobalCityInfo> infoList = list.stream().filter(t -> StringUtils.isNotBlank(t.getParentId())).collect(Collectors.toList());
        List<RegionDropDownBoxVO> boxVOList = collect.stream().map(t -> {
            RegionDropDownBoxVO vo = new RegionDropDownBoxVO();
            vo.setId(t.getId()).setName(t.getName());
            //第二级
            vo.setVoList(recursion(t.getCountryId(), infoList));
            return vo;
        }).collect(Collectors.toList());
        return boxVOList;
    }


    private List<RegionDropDownBoxVO> recursion(String countryId, List<GlobalCityInfo> infoList) {
        if (StringUtils.isBlank(countryId)) {
            return new ArrayList<>();
        }
        List<RegionDropDownBoxVO> voList = new ArrayList<>();
        for (GlobalCityInfo info : infoList) {
            if (info.getParentId().equals(countryId)) {
                RegionDropDownBoxVO vo = new RegionDropDownBoxVO();
                vo.setId(info.getId()).setName(info.getName());
                //第三级
                List<RegionDropDownBoxVO> list = recursion(info.getCountryId(), infoList);
                vo.setVoList(list);
                voList.add(vo);
            }
        }
        return voList;
    }

    @Override
    public List<RegionDropDownBoxVO> getCountryDropDownBox() {
        List<GlobalCityInfo> list = this.lambdaQuery().isNull(GlobalCityInfo::getParentId).list();
        return list.stream().map(t -> {
            RegionDropDownBoxVO vo = new RegionDropDownBoxVO();
            return vo.setId(t.getName())
                    .setName(t.getName());
        }).collect(Collectors.toList());
    }

    @Override
    public List<RegionDropDownBoxVO> getProvinceDropDownBox(String countryId) {
        GlobalCityInfo info = this.lambdaQuery().eq(GlobalCityInfo::getName,countryId).last(SqlConstant.ONE_SQL).one();
        if (info != null) {
            List<GlobalCityInfo> list = this.lambdaQuery().eq(GlobalCityInfo::getParentId, info.getCountryId()).list();
            if (!list.isEmpty()) {
                return list.stream().map(t -> {
                    RegionDropDownBoxVO vo = new RegionDropDownBoxVO();
                    return vo.setId(t.getName())
                            .setName(t.getName());
                }).collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }


}
