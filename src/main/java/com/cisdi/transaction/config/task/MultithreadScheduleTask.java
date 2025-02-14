package com.cisdi.transaction.config.task;

import com.cisdi.transaction.service.GbBasicInfoService;
import com.cisdi.transaction.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/8 0:20
 */
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class MultithreadScheduleTask {

    @Autowired
    private OrgService orgService;

    @Autowired
    private GbBasicInfoService gbBasicInfoService;

    //@PostConstruct      //项目启动执行1次
    @Scheduled(fixedDelay = 7200000) //两小时执行一次
    public  void syncOrgInfo(){
        System.out.println("执行组织同步定时任务");
        //orgService.syncDa();
        System.out.println("执行组织同步定时任务完成");
    }

   // @PostConstruct      //项目启动执行一次
    @Scheduled(fixedDelay = 7200000) //两小时执行一次
    public  void syncGbBasicInfo(){
        System.out.println("执行干部信息定时任务");
       // gbBasicInfoService.syncData();
    }
}
