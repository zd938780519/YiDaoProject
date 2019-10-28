package com.ruoyi.system.service.impl;

import com.ruoyi.common.json.Body;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.YdFoodOrderRemark;
import com.ruoyi.system.mapper.YdFoodOrderRemarkMapper;
import com.ruoyi.system.service.YdFoodOrderRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class YdFoodOrderRemarkServiceImpl implements YdFoodOrderRemarkService {
    @Autowired(required = false)
    private YdFoodOrderRemarkMapper orderRemarkMapper;

    /**
     * 根据用户id查询备注
     * @param userId
     * @return
     */
    @Override
    public Body selectAll(Integer userId) {
        return Body.newInstance(orderRemarkMapper.selectAll(userId));
    }
    /**
     *用户备注新增
     * @return
     */
    @Override
    public Body insert(YdFoodOrderRemark record) {
        if(record.getContent() == null)record.setContent("");
        //备注去空格，验证是否为无意义字符
        String content = record.getContent().replaceAll("\\s*","");
        if(StringUtils.isNotBlank(content)){
            record.setContent(content);
            //验证评论是否存在
            if(orderRemarkMapper.selectExistRemark(record) == 0){
                //执行新增
                orderRemarkMapper.insert(record);
                //查询评论数量，大于6个就删除最老的一个
                if(orderRemarkMapper.selectRemarkNum(record.getUserId()) > 6){
                    orderRemarkMapper.deleteLastRemark(record.getUserId());
                }
            }
        }
        return Body.BODY_200;
    }
    /**
     * 用户备注删除
     * @return
     */
    @Override
    public Body delete(Integer id,Integer userId) {
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        param.put("userId",userId);
        int num = orderRemarkMapper.deleteById(param);
        if(num == 0)return Body.newInstance(400,"id或用户id不正确，删除失败");
        return Body.BODY_200;
    }
}
