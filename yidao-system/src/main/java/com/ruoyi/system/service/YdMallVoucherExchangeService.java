package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdMallVoucherExchange;

import java.util.List;

public interface YdMallVoucherExchangeService {
    int insert(YdMallVoucherExchange record);

    /**
     * 获取优惠券列表
     * @param userId
     * @param currIndex
     * @param pageSize
     * @param status
     * @return
     */
    List<YdMallVoucherExchange> selectAllByUser(long userId,int currIndex,int pageSize,int status);

    /**
     * 获取可用抵用券数量
     * @param userId
     * @param ids
     * @return
     */
    int getVoucherNum(long userId,List<Long> ids);

    /**
     * 获取可用优惠券列表
     * @param userId
     * @param currIndex
     * @param pageSize
     * @param ids
     * @return
     */
    List<YdMallVoucherExchange> getAvailableVoucherList(long userId,int currIndex,int pageSize, List<Long> ids);

    /**
     * 修改优惠券状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(long id,int status);
}
