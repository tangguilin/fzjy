package com.cisdi.transaction.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/4 8:32
 */
@Data
@Accessors(chain = true)
public class ResponseMsgDTO {
    private String data;
}
