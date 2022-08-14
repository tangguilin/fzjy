package com.cisdi.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/4 11:19
 */
@Data
@ApiModel(description = "干部家属信息")
public class CadreFamiliesDTO extends BaseDTO{
    @ApiModelProperty(value = "干部姓名")
    @NotBlank(message = "干部姓名不能为空")
    private String cadreName;

    @ApiModelProperty(value = "干部身份证号")
    @NotBlank(message = "干部身份证号不能为空")
    private String cadreCardId;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "称谓")
    @NotBlank(message = "称谓不能为空")
    private String title;

    @ApiModelProperty(value = "证件名字")
    private String cardName;

    @ApiModelProperty(value = "证件号码")
    private String cardId;
}
