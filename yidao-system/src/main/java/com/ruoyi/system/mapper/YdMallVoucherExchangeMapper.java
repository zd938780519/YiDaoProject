package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdMallVoucherExchange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商城抵用券和用户关联数据库连接层
 */
public interface YdMallVoucherExchangeMapper {
    int insert(YdMallVoucherExchange record);

    /**
     * 获取优惠券列表
     * @param userId
     * @param currIndex
     * @param pageSize
     * @param status
     * @return
     */
    List<YdMallVoucherExchange> selectAllByUser(@Param("userId") long userId,@Param("currIndex") int currIndex,
                                                @Param("pageSize")  int pageSize,@Param("status") int status);

    /**
     * 修改优惠券状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(@Param("id") long id,@Param("status") int status);

    /**
     * 根据id和用户id查询是否有券
     * @param id
     * @param userId
     * @return
     */
    YdMallVoucherExchange selectVoucherByIdAndUserId(@Param("id") long id,@Param("userId") long userId);


}