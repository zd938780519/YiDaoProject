package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.YdSellerDiscountDraw;
import java.util.List;
import java.util.Map;

public interface YdSellerDiscountDrawMapper {
    /**
     * 点击进入周边主页（查询店铺优惠券）
     * @return
     */
    List<YdSellerDiscountDraw> getDiscountByShopId(Map<String,Integer> param);

    /**
     * 根据用户id和优惠券id查询优惠券
     * @param param
     * @return
     */
    YdSellerDiscountDraw getDiscountDrawByUserIdAndDisId(Map<String,Integer> param);

    /**
     * 根据id查询优惠券
     * @param id
     * @return
     */
    YdSellerDiscountDraw getDiscountById(Integer id);

    /**
     * 根据金额查询可用优惠券
     * @param param
     * @return
     */
    List<YdSellerDiscountDraw> getDiscountByCondition(Map<String,Object> param);

    int insert(YdSellerDiscountDraw record);

    /**
     * 使用优惠券
     * @param record
     * @return
     */
    int update(YdSellerDiscountDraw record);

    List<YdSellerDiscountDraw> selectAll();

    /**
     * 将未使用的优惠券改为：已过期
     * @param list
     * @return
     */
    int updateOfUnuserToPastDue(List<Integer> list);
}