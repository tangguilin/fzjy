package com.cisdi.transaction.config.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.cisdi.transaction.config.excel.ExcelImportValid;
import com.cisdi.transaction.config.excel.ExceptionCustom;
import com.cisdi.transaction.domain.dto.CommunityServiceDTO;
import com.cisdi.transaction.service.MechanismInfoService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/6 12:56
 */
@Slf4j
public class ImportCommunityServiceExcelListener extends AnalysisEventListener<CommunityServiceDTO> {
    /**
     * 每隔 1000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;

    // 存款的list对象
    List<CommunityServiceDTO> list = new ArrayList<CommunityServiceDTO>();

    private MechanismInfoService mechanismInfoService;

    public ImportCommunityServiceExcelListener() {
    }

    public ImportCommunityServiceExcelListener(MechanismInfoService mechanismInfoService) {
        this.mechanismInfoService = mechanismInfoService;
    }

    @Override
    public void invoke(CommunityServiceDTO dto, AnalysisContext analysisContext) {
        try {

            //通用方法数据校验
            ExcelImportValid.valid(dto);
        } catch (ExceptionCustom e) {
            System.out.println(e.getMessage());
            //在easyExcel监听器中抛出业务异常
            throw new ExcelAnalysisException(e.getMessage());
        }
        list.add(dto);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            //存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 存储数据
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        if (list.size() > 0) {
            mechanismInfoService.saveBatchInvestInfo(list);
        }
        log.info("存储数据库成功！");
    }

}
