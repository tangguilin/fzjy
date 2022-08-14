package com.cisdi.transaction.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yuw
 * @version 1.0  导出干部家属信息
 * @date 2022/8/9 10:33
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CadreFamiliesExcelVO {
    @ExcelProperty(value = "干部姓名")
    private String cadreName;

    @ExcelProperty(value = "干部身份证号")
    private String cadreCardId;

    @ExcelProperty(value = "姓名")
    private String name;

    @ExcelProperty(value = "称谓")
    private String title;

    @ExcelProperty(value = "证件名称")
    private String cardName;

    @ExcelProperty(value = "证件号码")
    private String cardId;
}
