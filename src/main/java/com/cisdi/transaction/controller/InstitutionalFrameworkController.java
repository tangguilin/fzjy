package com.cisdi.transaction.controller;

import cn.hutool.core.util.StrUtil;
import com.cisdi.transaction.config.base.ResultMsgUtil;
import com.cisdi.transaction.config.utils.ExportExcelUtils;
import com.cisdi.transaction.config.utils.MinIoUtil;
import com.cisdi.transaction.constant.SqlConstant;
import com.cisdi.transaction.domain.dto.BusinessTransactionDTO;
import com.cisdi.transaction.domain.dto.CadreFamilyExportDto;
import com.cisdi.transaction.domain.dto.InstitutionalFrameworkDTO;
import com.cisdi.transaction.domain.model.Org;
import com.cisdi.transaction.domain.vo.BusinessTransactionExcelVO;
import com.cisdi.transaction.domain.vo.InstitutionalFrameworkExcelVO;
import com.cisdi.transaction.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @author yuw
 * @version 1.0
 * @date 2022/8/4 9:56
 */
@Slf4j
@RestController
@RequestMapping("/institutional/framework")
@Api(tags ="组织机构管理")
@Validated
public class InstitutionalFrameworkController {

    @Autowired
    private OrgService orgService;
    @Autowired
    private MinIoUtil minIoUtil;

    @Value("${minio.bucketName}")
    private String bucketName;

    @ApiOperation("新增组织机构信息")
    @PostMapping("/saveInfo")
    public ResultMsgUtil<Object> saveInfo(@RequestBody @Valid InstitutionalFrameworkDTO dto) {
        int count = orgService.countByAncodeAndPathNamecode(dto.getAsgorgancode(),dto.getAsgpathnamecode());
        if(count>0){
            return ResultMsgUtil.failure("组织不能重复添加");
        }
        orgService.saveInfo(dto);
        return ResultMsgUtil.success();
    }

    @ApiOperation("编辑组织机构信息")
    @PostMapping("/editOrgInfo")
    public ResultMsgUtil<Object> editOrgInfo(@RequestBody  InstitutionalFrameworkDTO dto) {
         String id = dto.getId();
         if(StrUtil.isEmpty(id)){
             return ResultMsgUtil.failure("数据不存在");
         }
         orgService.editOrgInfo(dto);
        return ResultMsgUtil.success();
    }

    @ApiOperation("导出功能")
    @PostMapping("/institutionalFrameworkExport")
    public ResultMsgUtil<Object> institutionalFrameworkExport(@RequestBody @Valid CadreFamilyExportDto dto,
                                                           HttpServletResponse response) {
        String url = null;
        try {
            String fileName = new String("组织机构信息".getBytes(), StandardCharsets.UTF_8);
            List<InstitutionalFrameworkExcelVO> list=orgService.export(dto.getIds());
            MultipartFile multipartFile = ExportExcelUtils.exportExcel(response, fileName, InstitutionalFrameworkExcelVO.class, list);
            url = minIoUtil.downloadByMinio(multipartFile, bucketName, null);
        } catch (UnsupportedEncodingException e) {
            log.error("导出Excel编码异常", e);
        } catch (Exception e) {
            log.error("文件处理异常", e);
        }
        return ResultMsgUtil.success(url);
    }
}
