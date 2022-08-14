package com.cisdi.transaction.domain.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/9 11:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict_biz")
public class SysDictBiz {

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 父级id
     */
    private String parentId;

    /**
     * 类型名
     */
    private String name;

    /**
     * 类型值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private String sort;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 删除状态（1:是；0:否）
     */
    private String delStatus;

    /**
     * 创建者ID
     */
    private String creatorId;

    /**
     * 更新者ID
     */
    private String updaterId;

    /**
     * 创建时间;默认值：当前时间
     */
    private Date createTime;

    /**
     * 更新时间;默认值：当前时间
     */
    private Date updateTime;
}
