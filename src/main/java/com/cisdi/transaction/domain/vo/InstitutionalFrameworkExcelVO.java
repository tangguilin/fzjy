package com.cisdi.transaction.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yuw
 * @version 1.0 组织机构信息导出
 * @date 2022/8/9 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class InstitutionalFrameworkExcelVO {

    @ExcelProperty(value = "层级")
    private String asglevel;

    @ExcelProperty(value = "层级码")
    private String asgpathcode;

    @ExcelProperty(value = "组织名称链")
    private String asgpathname;

    @ExcelProperty(value = "组织编码链")
    private String asgpathnamecode;

    @ExcelProperty(value = "组织编码")
    private String asgorgancode;

    @ExcelProperty(value = "组织名称")
    private String asgorganname;

    @ExcelProperty(value = "是否分管")
    private String asgleadfg;

    @ExcelProperty(value = "是否领导班子")
    private String asglead;

    @ExcelProperty(value = "组织类型")
    private String asgtype;

    @ExcelProperty(value = "组织名称链")
    private String asgpathnameall;

    @ExcelProperty(value = "组织简称")
    private String asgorgannameshort;

    @ExcelProperty(value = "负责人")
    private String asgqyfzr;

    @ExcelProperty(value = "经营类型")
    private String asgzzzlxbm;

    @ExcelProperty(value = "经营状态")
    private String asgjzyzt;

}
