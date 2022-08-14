package com.cisdi.transaction.config.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.cisdi.transaction.config.excel.ExcelImportValid;
import com.cisdi.transaction.config.excel.ExceptionCustom;
import com.cisdi.transaction.domain.dto.InvestmentDTO;
import com.cisdi.transaction.service.InvestInfoService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/6 10:36
 */
@Slf4j
public class ImportInvestmentExcelListener extends AnalysisEventListener<InvestmentDTO> {


    /**
     * 每隔 1000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;

    // 存款的list对象
    List<InvestmentDTO> list = new ArrayList<InvestmentDTO>();

    // ImportInvestmentExcelListener  没有交给Spring进行管理，所有不能在此方法使用    @Autowired 注入
    //  不能使用数据库等操作,可以使用setter和getter方法，在调用方传入
    private InvestInfoService investInfoService;

    public ImportInvestmentExcelListener() {
    }

    public ImportInvestmentExcelListener(InvestInfoService investInfoService) {
        this.investInfoService = investInfoService;
    }

    // 读取Excel内容，一行一行的读
    @Override
    public void invoke(InvestmentDTO dto, AnalysisContext analysisContext) {
        try {
            log.info("dto：==={},{}", dto.getGbName(), dto.getCardId());
            //通用方法数据校验
            ExcelImportValid.valid(dto);
        } catch (ExceptionCustom e) {
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

    // 所有数据解析完成了 都会来调用
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
        if (list.size()>0){
            investInfoService.saveBatchInvestmentInfo(list);
        }
        log.info("存储数据库成功！");
    }

}
