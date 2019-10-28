package com.ruoyi.web.controller.backstage.shop;

import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.oss.OSSFileUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.YdShopGoods;
import com.ruoyi.system.service.YdShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/yd/backstage/shopping/goods")
public class YdBackstageShoppingGoodsInfoController {
    @Autowired
    private YdShopGoodsService ydShopGoodsService;

    private String prefix = "backstage/shoppingMall";

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("imageUpload")
    public Map<String, Object> imageUpload(@RequestParam("file") MultipartFile file){
        Map<String, Object> result = new HashMap<>();
        String fileKey = "image/shop/goods/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(file);
        String path = OSSFileUtil.upload(fileKey, file);
        if(StringUtils.isEmpty(path)){
            result.put("result", "fail");
        }else{
            result.put("result", "success");
            result.put("filePath",path);
            result.put("fileName",fileKey);
        }
        return result;
    }

    /**
     * 删除图片
     * @param fileName
     * @return
     */
    @PostMapping("removeFile")
    public String removeFile(@RequestParam("fileName") String fileName){
        JSONObject json =new JSONObject();
        if(StringUtils.isNotEmpty(fileName) && !fileName.equals("null") && !fileName.equals("underfined")){
            OSSFileUtil.remove(fileName);
            json.put("status", -1);
        }else{
            json.put("status", -1);
        }
        return json.toString();
    }

    /**
     *
     * 商品上架
     * @param shopId
     * @param name
     * @param typeShow
     * @param brief
     * @param originalPrice
     * @param discount
     * @param salePrice
     * @param integralLimit
     * @param storePrice
     * @param isFreeShipping
     * @param storedCount
     * @param producer
     * @param parameter
     * @param filePath
     * @return
     */
    @PostMapping("addGoods")
    public String addGoods(@RequestParam("shopId") String shopId, @RequestParam("name") String name,@RequestParam("typeShow") String typeShow,
                           @RequestParam("brief") String brief,@RequestParam("originalPrice") String originalPrice, @RequestParam("discount") String discount,
                           @RequestParam("salePrice") String salePrice, @RequestParam("integralLimit") String integralLimit,
                           @RequestParam("storePrice") String storePrice, @RequestParam("isFreeShipping") String isFreeShipping,
                           @RequestParam("storedCount") String storedCount, @RequestParam("producer") String producer,
                           @RequestParam("parameter") String parameter, @RequestParam("filePath") String filePath){
        JSONObject json =new JSONObject();
        String msg="";
        long shopIdL = 0l;
        if(StringUtils.isEmpty(shopId)){
            msg+="店铺id 不能为空，";
        }else{
            shopIdL = Long.parseLong(shopId);
        }
        if(StringUtils.isEmpty(name)){
            msg+="商品名称 不能为空，";
        }
        int typeId=1;
        if(StringUtils.isEmpty(typeShow)){
            msg+="商品类型 不能为空，";
        }else{
            typeId=Integer.parseInt(typeShow);
        }
        if(StringUtils.isEmpty(brief)){
            msg+="简介 不能为空，";
        }
        double originalPriceD=0.0;
        if(StringUtils.isEmpty(originalPrice)){
            msg+="原价 不能为空，";
        }else{
            originalPriceD = Double.parseDouble(originalPrice);
        }
        double discountD=0.0;
        if(StringUtils.isEmpty(discount)){
            msg+="折扣 不能为空，";
        }else{
            discountD = Double.parseDouble(discount);
        }
        double salePriceD=0.0;
        if(StringUtils.isEmpty(salePrice)){
            msg+="售价 不能为空，";
        }else{
            salePriceD = Double.parseDouble(salePrice);
        }
        int integralLimitI=0;
        if(StringUtils.isEmpty(integralLimit)){
            msg+="积分限额 不能为空，";
        }else{
            integralLimitI = Integer.parseInt(integralLimit);
        }
        double storePriceD=0.0;
        if(StringUtils.isEmpty(storePrice)){
            msg+="进价 不能为空，";
        }else{
            storePriceD = Double.parseDouble(storePrice);
        }
        boolean isFreeShippingB = false;
        if(StringUtils.isEmpty(isFreeShipping)){
            msg+="是否包邮 不能为空，";
        }else{
            isFreeShippingB = Boolean.parseBoolean(isFreeShipping);
        }
        int storedCountI=0;
        if(StringUtils.isEmpty(storedCount)){
            msg+="库存量 不能为空，";
        }else{
            storedCountI = Integer.parseInt(storedCount);
        }
        if(StringUtils.isEmpty(producer)){
            msg+="生产厂家 不能为空，";
        }
        if(StringUtils.isEmpty(parameter)){
            msg+="参数 不能为空，";
        }
        if(StringUtils.isEmpty(filePath)){
            msg+="图片 不能为空，";
        }
        if(msg.length()>0){
            json.put("status", "-1");
            json.put("msg",msg);
        }else{
            YdShopGoods ydShopGoods = new YdShopGoods();
            ydShopGoods.setShopId(shopIdL);
            ydShopGoods.setName(name);
            ydShopGoods.setTypeId(typeId);
            ydShopGoods.setBrief(brief);
            ydShopGoods.setOriginalPrice(originalPriceD);
            ydShopGoods.setDiscount(discountD);
            ydShopGoods.setSalePrice(salePriceD);
            ydShopGoods.setIntegralLimit(integralLimitI);
            ydShopGoods.setPhotoPath(filePath);
            ydShopGoods.setStorePrice(storePriceD);
            ydShopGoods.setIsFreeShipping(isFreeShippingB?1:0);
            ydShopGoods.setStoredCount(storedCountI);
            ydShopGoods.setProducer(producer);
            int insertGoods = ydShopGoodsService.insertGoods(ydShopGoods);
            json.put("status", insertGoods == 1 ? 1 : -1);
        }
        return json.toString();
    }

    /**
     * 下架
     * @param id
     * @return
     */
    @PostMapping("lowerShelf")
    public String lowerShelf (@RequestParam("id") long id){
        JSONObject json =new JSONObject();
        String photoPathById = ydShopGoodsService.selectPhotoPathById(id);
        int deleteGoods = ydShopGoodsService.deleteGoods(id);
        if(StringUtils.isNotEmpty(photoPathById) && photoPathById.indexOf("?")>-1){
            OSSFileUtil.remove(photoPathById.substring(0,photoPathById.indexOf("?")));
        }
        json.put("status", deleteGoods == 1 ? 1 : -1);
        return json.toString();
    }

    /**
     *
     * 修改商品上架信息
     * @param id
     * @param name
     * @param typeShow
     * @param brief
     * @param originalPrice
     * @param discount
     * @param salePrice
     * @param integralLimit
     * @param storePrice
     * @param isFreeShipping
     * @param storedCount
     * @param producer
     * @param parameter
     * @param filePath
     * @return
     */
    @PostMapping("updateGoods")
    public String updateGoods(@RequestParam("id") String id, @RequestParam("name") String name,@RequestParam("typeShow") String typeShow,
                           @RequestParam("brief") String brief,@RequestParam("originalPrice") String originalPrice, @RequestParam("discount") String discount,
                           @RequestParam("salePrice") String salePrice, @RequestParam("integralLimit") String integralLimit,
                           @RequestParam("storePrice") String storePrice, @RequestParam("isFreeShipping") String isFreeShipping,
                           @RequestParam("storedCount") String storedCount, @RequestParam("producer") String producer,
                           @RequestParam("parameter") String parameter, @RequestParam("filePath") String filePath){
        JSONObject json =new JSONObject();
        String msg="";
        long idL = 0l;
        if(StringUtils.isEmpty(id)){
            msg+="id 不能为空，";
        }else{
            idL = Long.parseLong(id);
        }
        if(StringUtils.isEmpty(name)){
            msg+="商品名称 不能为空，";
        }
        int typeId=1;
        if(StringUtils.isEmpty(typeShow)){
            msg+="商品类型 不能为空，";
        }else{
            typeId=Integer.parseInt(typeShow);
        }
        if(StringUtils.isEmpty(brief)){
            msg+="简介 不能为空，";
        }
        double originalPriceD=0.0;
        if(StringUtils.isEmpty(originalPrice)){
            msg+="原价 不能为空，";
        }else{
            originalPriceD = Double.parseDouble(originalPrice);
        }
        double discountD=0.0;
        if(StringUtils.isEmpty(discount)){
            msg+="折扣 不能为空，";
        }else{
            discountD = Double.parseDouble(discount);
        }
        double salePriceD=0.0;
        if(StringUtils.isEmpty(salePrice)){
            msg+="售价 不能为空，";
        }else{
            salePriceD = Double.parseDouble(salePrice);
        }
        int integralLimitI=0;
        if(StringUtils.isEmpty(integralLimit)){
            msg+="积分限额 不能为空，";
        }else{
            integralLimitI = Integer.parseInt(integralLimit);
        }
        double storePriceD=0.0;
        if(StringUtils.isEmpty(storePrice)){
            msg+="进价 不能为空，";
        }else{
            storePriceD = Double.parseDouble(storePrice);
        }
        boolean isFreeShippingB = false;
        if(StringUtils.isEmpty(isFreeShipping)){
            msg+="是否包邮 不能为空，";
        }else{
            isFreeShippingB = Boolean.parseBoolean(isFreeShipping);
        }
        int storedCountI=0;
        if(StringUtils.isEmpty(storedCount)){
            msg+="库存量 不能为空，";
        }else{
            storedCountI = Integer.parseInt(storedCount);
        }
        if(StringUtils.isEmpty(producer)){
            msg+="生产厂家 不能为空，";
        }
        if(StringUtils.isEmpty(parameter)){
            msg+="参数 不能为空，";
        }
        if(StringUtils.isEmpty(filePath)){
            msg+="图片 不能为空，";
        }
        if(msg.length()>0){
            json.put("status", "-1");
            json.put("msg",msg);
        }else{
            YdShopGoods ydShopGoods = new YdShopGoods();
            ydShopGoods.setId(idL);
            ydShopGoods.setName(name);
            ydShopGoods.setTypeId(typeId);
            ydShopGoods.setBrief(brief);
            ydShopGoods.setOriginalPrice(originalPriceD);
            ydShopGoods.setDiscount(discountD);
            ydShopGoods.setSalePrice(salePriceD);
            ydShopGoods.setIntegralLimit(integralLimitI);
            ydShopGoods.setPhotoPath(filePath);
            ydShopGoods.setStorePrice(storePriceD);
            ydShopGoods.setIsFreeShipping(isFreeShippingB?1:0);
            ydShopGoods.setStoredCount(storedCountI);
            ydShopGoods.setProducer(producer);
            int updateGoods = ydShopGoodsService.updateGoods(ydShopGoods);
            json.put("status", updateGoods == 1 ? 1 : -1);
        }
        return json.toString();
    }

    /**
     * 获取初始化图片上传控件配置数据
     * @param id
     * @return
     */
    @PostMapping("getFileInfo")
    public String getFileInfo(@RequestParam("id") long id){
        JSONObject json =new JSONObject();
        String photoPathById = ydShopGoodsService.selectPhotoPathById(id);
        if(StringUtils.isNotEmpty(photoPathById)){
            JSONObject info =new JSONObject();
            info.put("fileName",photoPathById.substring("image/shop/goods/".length(),photoPathById.indexOf("?")));
            info.put("url",photoPathById);
            json.put("info", info);
            json.put("status", 1);
        }else{
            json.put("status", -1);
        }
        return json.toString();
    }

}
