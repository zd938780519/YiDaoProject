package com.ruoyi.system.mapper;

public interface YdFlagMapper {
    /**
     * 订单尾号，0000-9999循环
     * @return
     */
    int findFlag();
}
