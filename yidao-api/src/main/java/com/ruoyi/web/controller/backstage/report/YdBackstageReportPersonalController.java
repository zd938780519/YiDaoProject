package com.ruoyi.web.controller.backstage.report;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.YdReportingRecords;
import com.ruoyi.system.service.YdReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/yd/backstage/report/personal")
public class YdBackstageReportPersonalController extends BaseController {
    @Autowired
    private YdReportService ydReportService;

    private String prefix = "backstage/report/personal";

    /**
     * 跳转举报列表
     * @return
     */
    @RequiresPermissions("report:personal:view")
    @GetMapping("/view")
    public String build()
    {
        return prefix + "/reportPersonal.html";
    }

    /**
     * 举报列表信息
     * @param model
     * @return
     */
    @PostMapping("list")
    @ResponseBody
    public TableDataInfo getList(YdReportingRecords model){
        List<YdReportingRecords> ydReportingRecords = ydReportService.selectAllReportInfo();
        TableDataInfo rspData = new TableDataInfo();
        List<YdReportingRecords> userList = new ArrayList<YdReportingRecords>(Arrays.asList(new YdReportingRecords[ydReportingRecords.size()]));
        Collections.copy(userList, ydReportingRecords);
        // 查询条件过滤
        if (StringUtils.isNotEmpty(model.getName()))
        {
            userList.clear();
            for (YdReportingRecords user : ydReportingRecords)
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

}
