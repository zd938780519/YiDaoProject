package com.ruoyi.system.service;

import com.ruoyi.system.domain.YdShopReceivingAddress;

import java.util.List;

public interface YdShopReceivingAddressService {
    /**
     * 新增收货地址
     * @param ydShopReceivingAddress
     * @return
     */
    int insert(YdShopReceivingAddress ydShopReceivingAddress);

    /**
     * 根据用户id查看收货地址
     * @param userId
     * @return
     */
    List<YdShopReceivingAddress> selectAllByUserId(long userId);

    /**
     * 根据用户id查询默认收货地址
     * @param userId
     * @return
     */
    YdShopReceivingAddress selectDefaultByUserId(long userId);

    /**
     * 修改收货地址
     * @param ydShopReceivingAddress
     * @return
     */
    int updateReceivingAddress(YdShopReceivingAddress ydShopReceivingAddress);

    /**
     * 根据id删除收货地址
     * @param raId
     * @return
     */
    int deleteReceivingAddress(long raId);
    /**
     * 批量删除购物车商品
     * @param list
     * @return
     */
    public int deleteSomeReceivingAddress(List<Long> list);
}
