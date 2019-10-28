package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.YdShopReceivingAddress;
import com.ruoyi.system.mapper.YdShopReceivingAddressMapper;
import com.ruoyi.system.service.YdShopReceivingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YdShopReceivingAddressServiceImpl implements YdShopReceivingAddressService {
    @Autowired(required = false)
    YdShopReceivingAddressMapper ydShopReceivingAddressMapper;


    @Override
    public int insert(YdShopReceivingAddress ydShopReceivingAddress) {
        return ydShopReceivingAddressMapper.insert(ydShopReceivingAddress);
    }

    @Override
    public List<YdShopReceivingAddress> selectAllByUserId(long userId) {
        return ydShopReceivingAddressMapper.selectAllByUserId(userId);
    }

    @Override
    public YdShopReceivingAddress selectDefaultByUserId(long userId) {
        return ydShopReceivingAddressMapper.selectDefaultByUserId(userId);
    }

    @Override
    public int updateReceivingAddress(YdShopReceivingAddress ydShopReceivingAddress) {
        return ydShopReceivingAddressMapper.updateReceivingAddress(ydShopReceivingAddress);
    }

    @Override
    public int deleteReceivingAddress(long raId) {
        return ydShopReceivingAddressMapper.deleteReceivingAddress(raId);
    }

    @Override
    public int deleteSomeReceivingAddress(List<Long> list) {
        return ydShopReceivingAddressMapper.deleteSomeReceivingAddress(list);
    }


}
