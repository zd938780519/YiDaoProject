package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdSellerDiscountDraw;
import com.ruoyi.system.mapper.YdSellerDiscountDrawMapper;
import com.ruoyi.system.service.YdSellerDiscountDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YdSellerDiscountDrawServiceImpl implements YdSellerDiscountDrawService {
    @Autowired(required = false)
    private YdSellerDiscountDrawMapper discountDrawMapper;
    /**
     * 查询店铺所有优惠券
     * @param shopId
     * @param userId
     * @return
     */
    @Override
    public List<YdSellerDiscountDraw> getDiscountOfShop(Integer shopId, Integer userId) {
        Map<String,Integer> param = new HashMap<>();
        param.put("functionType",1);
        param.put("shopId",shopId);
        param.put("userId",userId);
        //查询店铺优惠券（functionType:null,只显示缩略两个；其他，显示全部）
        List<YdSellerDiscountDraw> discountDrawList = discountDrawMapper.getDiscountByShopId(param);
        for(YdSellerDiscountDraw discountDraw : discountDrawList){
            if(discountDraw.getId() != null)discountDraw.getDiscount().setIsDraw(1);
        }
        return discountDrawList;
    }

    /**
     * 新增
     * @param userId
     * @param discountId
     * @return
     */
    @Override
    public Body insert(Integer userId, Integer discountId) {
        //验证用户是否领取过了
        Map<String,Integer> param = new HashMap<>();
        param.put("userId",userId);
        param.put("discountId",discountId);
        YdSellerDiscountDraw discountDrawTest = discountDrawMapper.getDiscountDrawByUserIdAndDisId(param);
        if(discountDrawTest != null)return Body.newInstance(400,"优惠券已领取过了");
        //TODO 验证优惠券是否可领
        //设置实体参数，执行新增
        YdSellerDiscountDraw discountDraw = new YdSellerDiscountDraw();
        discountDraw.setUserId(userId);
        discountDraw.setDiscountId(discountId);
        discountDraw.setDrawTime(new Date());
        int answer = discountDrawMapper.insert(discountDraw);
        return answer > 0 ? Body.BODY_200 : Body.newInstance(400,"领取失败");
    }
}
