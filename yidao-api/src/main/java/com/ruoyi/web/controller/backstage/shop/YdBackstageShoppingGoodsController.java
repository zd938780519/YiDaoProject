package com.ruoyi.web.controller.backstage.shop;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.YdShopGoods;
import com.ruoyi.system.service.YdShopGoodsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/yd/backstage/shopping/goods")
public class YdBackstageShoppingGoodsController extends BaseController {
    @Autowired
    private YdShopGoodsService ydShopGoodsService;

    private String prefix = "backstage/shoppingMall";

    /**
     * 跳转商品列表
     * @return
     */
    @RequiresPermissions("shopping:goods:view")
    @GetMapping("/view")
    public String build()
    {
        return prefix + "/goodsList.html";
    }

    /**
     * 商品列表内容
     * @param model
     * @return
     */
    @PostMapping("list")
    @ResponseBody
    public TableDataInfo getList(YdShopGoods model){
        List<YdShopGoods> ydShopGoodsServiceAll = ydShopGoodsService.getAll(model);
        TableDataInfo rspData = new TableDataInfo();
        List<YdShopGoods> userList = new ArrayList<YdShopGoods>(Arrays.asList(new YdShopGoods[ydShopGoodsServiceAll.size()]));
        Collections.copy(userList, ydShopGoodsServiceAll);
        // 查询条件过滤
        if (StringUtils.isNotEmpty(model.getName()))
        {
            userList.clear();
            for (YdShopGoods user : ydShopGoodsServiceAll)
            {
                if (user.getName().indexOf(model.getName())>-1)
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

    /**
     * 跳转上货
     * @return
     */
    @GetMapping("/upperShelfForm")
    public String upperShelfForm()
    {
        return prefix + "/upperShelfForm";
    }

    /**
     * 跳转修改上货信息
     * @return
     */
    @GetMapping("/updateUpperShelfForm")
    public String updateUpperShelfForm()
    {
        return prefix + "/updateUpperShelfForm";
    }

}
