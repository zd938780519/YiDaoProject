package com.ruoyi.web.controller.backstage.report;

import com.ruoyi.common.json.JSONObject;
import com.ruoyi.system.domain.YdReportingRecords;
import com.ruoyi.system.domain.YdUserAppeal;
import com.ruoyi.system.service.YdReportService;
import com.ruoyi.system.service.YdUserAppealService;
import com.ruoyi.system.service.YdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yd/backstage/report/personal")
public class YdBackstageReportPersonalInfoController {
    @Autowired
    private YdReportService ydReportService;
    @Autowired
    private YdUserService ydUserService;
    @Autowired
    private YdUserAppealService ydUserAppealService;


    /**
     * 警告
     * @param id
     * @return
     */
    @PostMapping("warning")
    public String warning(@RequestParam("id") long id){
        JSONObject json =new JSONObject();
        YdReportingRecords ydReportingRecords = new YdReportingRecords();
        ydReportingRecords.setId(id);
        ydReportingRecords.setProcessingStatus(2);
        ydReportingRecords.setProcessingStatusShow("已警告");
        int updateReportInfo = ydReportService.updateReportInfo(ydReportingRecords);
        json.put("status",updateReportInfo == 1?1:-1);
        return json.toString();
    }

    /**
     * 逻辑删除举报数据
     * @param id
     * @return
     */
    @PostMapping("remove")
    public String remove(@RequestParam("id") long id){
        JSONObject json =new JSONObject();
        YdReportingRecords ydReportingRecords = new YdReportingRecords();
        ydReportingRecords.setId(id);
        ydReportingRecords.setStatus(-1);
        int updateReportInfo = ydReportService.updateReportInfo(ydReportingRecords);
        json.put("status",updateReportInfo == 1?1:-1);
        return json.toString();
    }

    /**
     * 封号
     * @param id 举报记录id
     * @param toId 被举报人id
     * @return
     */
    @PostMapping("prohibition")
    public String prohibition(@RequestParam("id") long id,@RequestParam("toId") long toId){
        JSONObject json =new JSONObject();
        int prohibition = ydUserService.prohibition(toId);
        YdReportingRecords ydReportingRecords = new YdReportingRecords();
        ydReportingRecords.setId(id);
        ydReportingRecords.setProcessingStatus(3);
        ydReportingRecords.setProcessingStatusShow("已封号");
        int updateReportInfo = ydReportService.updateReportInfo(ydReportingRecords);
        json.put("status",prohibition == 1 && updateReportInfo == 1?1:-1);
        return json.toString();
    }

    /**
     * 解封
     * @param id 举报记录id
     * @param toId 被举报人id
     * @return
     */
    @PostMapping("relieveProhibition")
    public String relieveProhibition(@RequestParam("id") long id,@RequestParam("toId") long toId){
        JSONObject json =new JSONObject();
        int relieveProhibition = ydUserService.relieveProhibition(toId);
        YdReportingRecords ydReportingRecords = new YdReportingRecords();
        ydReportingRecords.setId(id);
        ydReportingRecords.setStatus(-1);
        int updateReportInfo = ydReportService.updateReportInfo(ydReportingRecords);
        removeAppeal(toId);
        json.put("status",relieveProhibition == 1 && updateReportInfo == 1?1:-1);
        return json.toString();
    }

    /**
     * 删除申诉数据
     * @param userId
     */
    private void removeAppeal(long userId){
        YdUserAppeal ydUserAppeal= new YdUserAppeal();
        ydUserAppeal.setUserId(userId);
        ydUserAppeal.setStatus(-1);
        ydUserAppealService.updateUserAppeal(ydUserAppeal);
    }

}
