package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.formatUtil.DictUtil;
import com.ruoyi.common.utils.formatUtil.RefundUtil;
import com.ruoyi.common.utils.formatUtil.SellerOrderUtil;
import com.ruoyi.system.domain.YdSellerOrder;
import com.ruoyi.system.domain.YdSellerOrderRefund;
import com.ruoyi.system.domain.YdUser;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.YdSellerOrderRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YdSellerOrderRefundServiceImpl implements YdSellerOrderRefundService {
    @Autowired(required = false)
    private YdSellerOrderRefundMapper orderRefundMapper;
    @Autowired(required = false)
    private YdUserMapper userMapper;
    @Autowired(required = false)
    private YdSellerOrderMapper sellerOrderMapper;
    @Autowired(required = false)
    private YdSellerDictMapper sellerDictMapper;
    @Autowired(required = false)
    private YdSellerOrderItemMapper sellerOrderItemMapper;
    /**
     *订单退款
     * @param orderNum
     * @param dictIds
     * @return
     */
    @Override
    public Body refundOrder(Long orderNum, List<Integer> dictIds,String dictContent) {
        //根据id查询订单信息
        YdSellerOrder sellerOrder = sellerOrderMapper.selectByOrderNum(orderNum);
        if(sellerOrder == null)return Body.newInstance(400,"检测到异常数据：订单不存在");
        if(sellerOrder.getStatus() != SellerOrderUtil.UNUSE)return Body.newInstance(400,"检测到异常数据：订单状态异常");
        //根据id查询用户
        YdUser user = userMapper.selectByIdOfOrder((long)sellerOrder.getUserId());
        //查询字典,拼接退款理由
        String content = "";
        if(dictIds != null && !dictIds.isEmpty()){
            //查询字典
            Map<String,Object> param = new HashMap<>();
            param.put("type",DictUtil.SELLER_DICT);
            param.put("ids",dictIds);
            List<String> dicts = sellerDictMapper.getByIdAndType(param);
            //拼接退款理由
            content = StringUtils.join(dicts.toArray(),"|");
        }
        //如果dictContent不为null，继续拼接
        if(dictContent != null)content = content + "#" + dictContent.replaceAll("(\\\r\\\n|\\\r|\\\n|\\\n\\\r|\\s*)", "");

        //TODO 调用支付退款接口
        if(true){
            //构建退款表实体
            YdSellerOrderRefund orderRefund = new YdSellerOrderRefund();
            orderRefund.setOrderId(sellerOrder.getId());
            orderRefund.setUserId((int)user.getId());
            orderRefund.setUserName(user.getUserName());
            orderRefund.setRefundProject(sellerOrder.getAmountPayable());
            orderRefund.setReason(content);
            //状态定为已退款，并且添加退款完成时间
            orderRefund.setCompleteTime(new Date());
            orderRefund.setOrderRefundType(RefundUtil.seller.COMPLETE);
            orderRefundMapper.insert(orderRefund);

            //订单主表改为已退款
            YdSellerOrder updateOrder = new YdSellerOrder();
            updateOrder.setId(sellerOrder.getId());
            updateOrder.setStatus(SellerOrderUtil.REFUND);
            updateOrder.setRemark(content);
            sellerOrderMapper.updateStatus(updateOrder);
        }
        return Body.newInstance(200,"退款成功");
    }
    /**
     *订单退款
     * @param orderNum
     * @return
     */
    @Override
    public Body refundOrder(Long orderNum,String dictContent) {
        return refundOrder(orderNum,null,dictContent);
    }
}
