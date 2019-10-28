package com.ruoyi.system.domain;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.math.NumberUtils;


import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 订单延迟队列实体
 */
public class YdShopOrderAutoEntity implements Delayed {
    private long orderId;
    private long time;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public YdShopOrderAutoEntity(long orderId, long time) {
        this.orderId = orderId;
        this.time = time;
    }

    public YdShopOrderAutoEntity() {
    }

    /**
     * 需要实现的接口，获得延迟时间   用过期时间-当前时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序   当前时间的延迟时间 - 比较对象的延迟时间
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        YdShopOrderAutoEntity o1 = (YdShopOrderAutoEntity) o;
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o1.getDelay(TimeUnit.MILLISECONDS));
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + (int) (NumberUtils.createInteger(orderId+"") ^ (NumberUtils.createInteger(orderId+"") >>> 32));
        result = prime * result + (int) (time ^ (time >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        YdShopOrderAutoEntity other = (YdShopOrderAutoEntity) obj;
        if (orderId != other.orderId)
            return false;
        if (time != other.time)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
