package com.cisdi.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cisdi.transaction.domain.model.GlobalCityInfo;
import com.cisdi.transaction.domain.vo.RegionDropDownBoxVO;

import java.util.List;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/9 17:09
 */
public interface GlobalCityInfoService extends IService<GlobalCityInfo> {
    /**
     * 国家/省/市下拉框
     * @return
     */
    List<RegionDropDownBoxVO> regionDropDownBox();

    /**
     * 获取所有国家下拉列表
     * @return
     */
    List<RegionDropDownBoxVO> getCountryDropDownBox();

    /**
     * 省份下拉框
     * @return
     */
    List<RegionDropDownBoxVO> getProvinceDropDownBox(String countryId);
}
