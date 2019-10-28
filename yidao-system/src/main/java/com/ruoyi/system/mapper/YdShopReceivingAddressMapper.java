package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdShopReceivingAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收货地址数据库访问层
 */
public interface YdShopReceivingAddressMapper {
    /**
     * 新增收货地址
     * @param YdShopReceivingAddress
     * @return
     */
    int insert(YdShopReceivingAddress YdShopReceivingAddress);

    /**
     *
     * 根据用户id查询收货地址
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
    public int deleteSomeReceivingAddress(@Param("list") List<Long> list);
}