package com.cisdi.transaction.domain.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author yuw
 * @version 1.0  投资企业或担任高级职务情况
 * @date 2022/8/3 11:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("69654103_invest_info")
public class InvestInfo {
    /**
     * id
     */
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date updateTime;
    /**
     * 干部姓名
     */
    private String gbName;
    /**
     * 身份证号
     */
    private String cardId;
    /**
     * 工作单位
     */
    private String company;
    /**
     * 现任职务
     */
    private String post;
    /**
     * 职务层次
     */
    private String gradation;
    /**
     * 标签
     */
    private String label;
    /**
     * 职级
     */
    private String gbRank;
    /**
     * 人员类型
     */
    private String userType;
    /**
     * 政治面貌
     */
    private String face;
    /**
     * 在职状态
     */
    private String jobStatus;
    /**
     * 姓名
     */
    private String name;
    /**
     * 称谓
     */
    private String title;
    /**
     * 统一社会信用代码
     */
    private String code;
    /**
     * 企业或其他市场主体名称
     */
    private String enterpriseName;
    /**
     * 成立日期
     */
    private String establishTime;
    /**
     * 经营范围
     */
    private String operatScope;
    /**
     * 注册地（国家）
     */
    private String registerCountry;
    /**
     * 注册地（省份）
     */
    private String registerProvince;
    /**
     * 注册地（市）
     */
    private String city;
    /**
     * 经营地
     */
    private String operatAddr;
    /**
     * 企业或其他市场主体类型
     */
    private String enterpriseType;
    /**
     * 注册资本（金）或资金数额（出资额）（人民币万元）
     */
    private String registerCapital;
    /**
     * 企业状态
     */
    private String enterpriseState;
    /**
     * 是否为股东（合伙人、所有人）
     */
    private String shareholder;
    /**
     * 个人认缴出资额或个人出资额（人民币万元
     */
    private String personalCapital;
    /**
     * 个人认缴出资比例或个人出资比例（%）
     */
    private String personalRatio;
    /**
     * 投资时间
     */
    private String investTime;
    /**
     * 是否担任高级职务
     */
    private String seniorPosition;
    /**
     * 担任高级职务名称
     */
    private String seniorPositionName;
    /**
     * 担任高级职务的开始时间
     */
    private String seniorPositionStartTime;
    /**
     * 担任高级职务的结束时间
     */
    private String seniorPositionEndTime;
    /**
     * 该企业或其他市场主体是否与报告人所在单位
     */
    private String isRelation;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 填报类型
     */
    private String tbType;
    /**
     * 年度
     */
    private String year;
    /**
     * 状态
     */
    private String state;
    /**
     * 有无此类情况
     */
    private String isSituation;
    /**
     * 创建人
     */
    private String creatorId;
    /**
     * 修改人
     */
    private String updaterId;

    /**
     * 创建人姓名
     */
    private  String createName;

    /**
     * 创建人账号
     */
    private  String createAccount;

    /**
     * 组织名称
     */
    private  String orgName;

    /**
     * 组织代码
     */
    private  String orgCode;


}
