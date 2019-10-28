package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdMallVoucher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 商城抵用券数据库连接层
 */
public interface YdMallVoucherMapper {
    int insert(YdMallVoucher record);

    List<YdMallVoucher> selectAll(@Param("currIndex") int currIndex,@Param("pageSize")  int pageSize);

    /**
     *
     * 查询抵用券详细信息
     * @param id
     * @return
     */
    YdMallVoucher selectById(long id);

    /**
     * 抵用券库存减一
     * @param id
     * @return
     */
    int stockReduceOne(long id);
}