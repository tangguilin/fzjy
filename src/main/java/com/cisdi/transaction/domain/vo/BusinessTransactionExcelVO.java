package com.cisdi.transaction.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author yuw  企业交易导出
 * @version 1.0
 * @date 2022/8/9 11:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessTransactionExcelVO {
    @ExcelProperty(value = "身份证号")
    private String cardId;

    @ExcelProperty(value = "干部姓名")
    private String name;

    @ExcelProperty(value = "单位")
    private String unit;

    @ExcelProperty(value = "职务")
    private String post;

    @ExcelProperty(value = "职务类型")
    private String postType;

    @ExcelProperty(value = "禁业职务类型")
    private String banPostType;

    @ExcelProperty(value = "家人姓名")
    private String familyName;

    @ExcelProperty(value = "家人关系")
    private String relation;

    @ExcelProperty(value = "经商类型")
    private String engageType;

    @ExcelProperty(value = "经商类型详细描述")
    private String engageInfo;

    @ExcelProperty(value = "经营范围")
    private String operatScope;

    @ExcelProperty(value = "供应商名称")
    private String supplier;

    @ExcelProperty(value = "统一社会信用代码")
    private String code;

    @ExcelProperty(value = "采购单位名称")
    private String purchaseName;

    @ExcelProperty(value = "采购业务编码")
    private String businessCode;

    @ExcelProperty(value = "采购业务名称")
    private String businessName;

    @ExcelProperty(value = "信息类型")
    private String infoType;

    @ExcelProperty(value = "供应商报价")
    private String supplierPrice;

    @ExcelProperty(value = "是否为中标供应商")
    private String bidder;

    @ExcelProperty(value = "采购合同编码")
    private String contractCode;

    @ExcelProperty(value = "采购合同名称")
    private String contractName;

    @ExcelProperty(value = "采购合同金额")
    private String contractPrice;

    @ExcelProperty(value = "采购合同签订日期")
    private Date contractTime;

    @ExcelProperty(value = "信息提示")
    private String infoTips;
}
