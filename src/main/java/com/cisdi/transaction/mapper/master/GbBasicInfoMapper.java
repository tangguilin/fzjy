package com.cisdi.transaction.mapper.master;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cisdi.transaction.domain.model.GbBasicInfo;
import com.cisdi.transaction.domain.model.GbOrgInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/1 16:53
 */
public interface GbBasicInfoMapper extends BaseMapper<GbBasicInfo> {
    /**
     * 插入单条
     * @param gb
     * @return
     */
 //   public int insert(GbBasicInfo gb);

    /**
     * 批量插入
     * @param gbs
     * @return
     */
   // public int insertBatch(List<GbBasicInfo> gbs);


//    public List<GbBasicInfo> selectByName(String name);

    /**
     * 根据身份证查询干部的基本信息和组织信息
     * @param ids
     * @return
     */
    @Select("<script>select  a.name,a.card_id,a.unit,\n" +
            " \n" +
            "  c.asgorgancode \"unit_code\",\n" +
            "  c.asglevel \"level\",\n" +
            "department,\n" +
            "(select  asgorgancode from `69654103_org` b where b.asgorganname like concat(\"%\",a.unit,\"_\", a.department)) \"department_code\",\n" +
            "post,post_type from `69654103_gb_basic_info` a left join \n" +
            "(select  asgorgancode,asgorganname,asglevel from `69654103_org`) c\n" +
            "\n" +
            "\n" +
            "on c.asgorganname =a.unit where a.card_id in\n" +
            "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>\n" +
            "  #{item}\n" +
            "</foreach>\n</script>")
    public List<GbOrgInfo> selectByCardIds(@Param("ids") List<String> ids);
}
