package com.cisdi.transaction.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/6 14:08
 */
@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
public class SpouseBasicInfoVo {

        /**
         * id
         */
        private String id;
        /**
         * 租户ID
         */
        private Date tenantId;
        /**
         * 创建时间
         */
        private Date createTime;
        /**
         * 修改时间
         */
        private Date updateTime;
        /**
         * 干部姓名
         */
        private String gbName;
        /**
         * 干部身份证号
         */
        private String cadreCardId;
        /**
         * 姓名
         */
        private String name;
        /**
         * 称谓
         */
        private String title;
        /**
         * 证件名字
         */
        private String cardName;
        /**
         * 证件号码
         */
        private String cardId;
        /**
         * 创建人
         */
        private String creatorId;
        /**
         * 修改人
         */
        private String updaterId;

}
