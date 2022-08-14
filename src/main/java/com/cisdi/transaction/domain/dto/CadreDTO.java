package com.cisdi.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/4 11:18
 */
@Data
@ApiModel(description = "干部信息")
public class CadreDTO extends BaseDTO {
    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "身份证号")
    @NotBlank(message = "身份证号不能为空")
    private String cardId;

    @ApiModelProperty(value = "单位")
    @NotBlank(message = "单位不能为空")
    private String unit;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "职务")
    @NotBlank(message = "职务不能为空")
    private String post;

    @ApiModelProperty(value = "职务类型")
    @NotBlank(message = "职务类型不能为空")
    private String postType;

    @ApiModelProperty(value = "分配类型")
    @NotBlank(message = "分配类型不能为空")
    private String allotType;
}
