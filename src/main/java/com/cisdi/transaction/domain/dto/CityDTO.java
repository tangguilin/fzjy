package com.cisdi.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/9 15:45
 */
@Data
@ApiModel(description = "城市列表")
public class CityDTO {

    @ApiModelProperty(value = "电话地区号")
    private String area_code;

    @ApiModelProperty(value = "地区id")
    private String country_id;

    @ApiModelProperty(value = "中文名")
    private String name_cn;
}
