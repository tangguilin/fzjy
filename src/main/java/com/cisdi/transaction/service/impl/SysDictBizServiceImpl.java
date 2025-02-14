package com.cisdi.transaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cisdi.transaction.domain.model.SysDictBiz;
import com.cisdi.transaction.mapper.master.SysDictBizMapper;
import com.cisdi.transaction.service.SysDictBizService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/9 11:25
 */
@Service
public class SysDictBizServiceImpl extends ServiceImpl<SysDictBizMapper, SysDictBiz> implements SysDictBizService {

   /* @Value("${appId}")
    private String appId;*/

    public List<SysDictBiz> selectList(){
        QueryWrapper<SysDictBiz> queryWrapper = new QueryWrapper();
        //queryWrapper.lambda().eq(SysDictBiz::getAppId,appId)
        queryWrapper.lambda().eq(SysDictBiz::getDelStatus,"0");
        return this.list(queryWrapper);
    }

    @Override
    public SysDictBiz selectById(String id, List<SysDictBiz> dictList) {
        return dictList.stream().filter(e -> e.getId().toString().equals(id)).findAny().orElse(null);
    }

    @Override
    public String getDictValue(String id, List<SysDictBiz> dictList) {
        SysDictBiz dict =  this.selectById(id,dictList);
        if(Objects.isNull(dict)){
            return null;
        }
        return dict.getValue();
    }

    @Override
    public String getDictId(String value, List<SysDictBiz> dictList) {
        SysDictBiz dict = dictList.stream().filter(e -> e.getValue().equals(value)).findAny().orElse(null);
        if(Objects.isNull(dict)){
            return null;
        }
        return dict.getId().toString();
    }
}
