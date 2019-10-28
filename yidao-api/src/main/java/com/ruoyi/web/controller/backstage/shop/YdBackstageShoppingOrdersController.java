package com.ruoyi.web.controller.backstage.shop;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.YdShopOrders;
import com.ruoyi.system.service.YdShopOrdersService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/yd/backstage/shopping/orders")
public class YdBackstageShoppingOrdersController extends BaseController {
    @Autowired
    private YdShopOrdersService ydShopOrdersService;

    private String prefix = "backstage/shoppingMall";

    /**
     * 跳转订单列表
     * @return
     */
    @RequiresPermissions("shopping:orders:view")
    @GetMapping("/view")
    public String build()
    {
        return prefix + "/ordersList.html";
    }

    /**
     * 跳转订单详情
     * @return
     */
    @GetMapping("/ordersInfo")
    public String ordersInfoForm() {
        return prefix + "/ordersInfoForm.html";
    }

    /**
     * 跳转到发货页面
     * @return
     */
    @GetMapping("/ordersDelivery")
    public String ordersDelivery() {
        return prefix + "/ordersDelivery.html";
    }

    /**
     * 跳转到物流跟踪页面
     * @return
     */
    @GetMapping("/logisticsTracking")
    public String logisticsTracking() {
        return prefix + "/logisticsTracking.html";
    }

    /**
     * 待付款订单列表
     * @return
     */
    @GetMapping("/orderIsPaymentToBeMade")
    public String orderIsPaymentToBeMade() {
        return prefix + "/orderIsPaymentToBeMade.html";
    }

    /**
     * 全部订单列表
     * @return
     */
    @GetMapping("/orderAll")
    public String orderAll(Model model,long shopId,Integer type) {
        model.addAttribute("shopId", shopId);
        model.addAttribute("type", type);
        return prefix + "/orderAll";
    }

    /**
     * 订单列表内容
     * @param model
     * @return
     */
    @PostMapping("list")
    @ResponseBody
    public TableDataInfo getList(YdShopOrders model){
        long shopId = model.getShopId();
        int type = model.getType();
        List<YdShopOrders> ydShopOrders = new ArrayList<YdShopOrders>();
        if(type==0){
            ydShopOrders = ydShopOrdersService.selectALl(shopId);
        }else{
            ydShopOrders = ydShopOrdersService.selectByType(shopId,type);
        }
        TableDataInfo rspData = new TableDataInfo();
        List<YdShopOrders> userList = new ArrayList<YdShopOrders>(Arrays.asList(new YdShopOrders[ydShopOrders.size()]));
        Collections.copy(userList, ydShopOrders);
        // 查询条件过滤
        if (StringUtils.isNotEmpty(model.getUserName()))
        {
            userList.clear();
            for (YdShopOrders user : ydShopOrders)
            {
                if (user.getUserName().indexOf(model.getUserName())>-1)
                {
                    userList.add(user);
                }
            }
        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize())
        {
            rspData.setRows(userList);
            rspData.setTotal(userList.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * pageDomain.getPageSize();
        Integer pageSize = pageDomain.getPageNum() * pageDomain.getPageSize();
        if (pageSize > userList.size())
        {
            pageSize = userList.size();
        }
        rspData.setRows(userList.subList(pageNum, pageSize));
        rspData.setTotal(userList.size());
        return rspData;
    }








}
