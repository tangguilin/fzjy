package com.cisdi.transaction.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/4 11:04
 */
@Data
@ApiModel(description = "组织机构")
public class InstitutionalFrameworkDTO extends  BaseDTO{

    @ApiModelProperty(value = "id,修改时必传")
    private String id;

    @ApiModelProperty(value = "层级")
    @NotBlank(message = "层级不能为空")
    private String asglevel;

    @ApiModelProperty(value = "层级码")
    @NotBlank(message = "层级码不能为空")
    private String asgpathcode;

    @ApiModelProperty(value = "组织名称链")
    @NotBlank(message = "组织名称链不能为空")
    private String asgpathname;

    @ApiModelProperty(value = "组织编码链")
    @NotBlank(message = "组织编码链不能为空")
    private String asgpathnamecode;

    @ApiModelProperty(value = "组织编码")
    @NotBlank(message = "组织编码不能为空")
    private String asgorgancode;

    @ApiModelProperty(value = "组织名称")
    @NotBlank(message = "组织名称不能为空")
    private String asgorganname;

    @ApiModelProperty(value = "是否分管")
    @NotBlank(message = "是否分管不能为空")
    private String asgleadfg;

    @ApiModelProperty(value = "是否领导班子")
    @NotBlank(message = "是否领导班子不能为空")
    private String asglead;

    @ApiModelProperty(value = "组织类型")
    @NotBlank(message = "组织类型不能为空")
    private String asgtype;

    @ApiModelProperty(value = "组织名称链")
    @NotBlank(message = "组织名称链不能为空")
    private String asgpathnameall;

    @ApiModelProperty(value = "组织简称")
    @NotBlank(message = "组织简称不能为空")
    private String asgorgannameshort;

    @ApiModelProperty(value = "负责人")
    @NotBlank(message = "负责人不能为空")
    private String asgqyfzr;

    @ApiModelProperty(value = "经营类型")
    @NotBlank(message = "经营类型不能为空")
    private String asgzzzlxbm;

    @ApiModelProperty(value = "经营状态")
    @NotBlank(message = "经营状态不能为空")
    private String asgjzyzt;

}
