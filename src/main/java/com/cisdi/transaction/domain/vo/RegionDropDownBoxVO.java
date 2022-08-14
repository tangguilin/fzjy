package com.cisdi.transaction.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/10 11:05
 */
@Data
@ApiModel(description = "国家/省/市下拉框")
@Accessors(chain = true)
public class RegionDropDownBoxVO {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "下级地区列表")
    private List<RegionDropDownBoxVO> voList;
}
