package com.ruoyi.MobileApi.shop;

import com.ruoyi.common.json.Body;
import com.ruoyi.system.domain.YdShopGoodComment;
import com.ruoyi.system.service.YdShopGoodCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Api("商城商品评论相关api")
@RestController
@RequestMapping("/yd/app/shop/goodsComment")
public class YdShopGoodsCommentController {
    @Autowired
    private YdShopGoodCommentService ydShopGoodCommentService;

    @ApiOperation("新增商品评论")
    @PostMapping("/addComment")
    @ApiImplicitParams({
            @ApiImplicitParam(name="soId",value="店铺ID",required=true,paramType="query"),
            @ApiImplicitParam(name="goodsId",value="商品id",required=true,paramType="query"),
            @ApiImplicitParam(name="userId",value="用户ID",required=true,paramType="query"),
            @ApiImplicitParam(name="content",value="评论内容",required=true,paramType="query"),
            @ApiImplicitParam(name="video",value="评论视频",required=true,paramType="query"),
            @ApiImplicitParam(name="commentScore",value="商品分数",required=true,paramType="query"),
            @ApiImplicitParam(name="logisticsScore",value="物流分数",required=true,paramType="query"),
            @ApiImplicitParam(name="attitudeScore",value="服务态度分数",required=true,paramType="query")
    })
    public Body addComment(@RequestParam("soId") long  soId  , @RequestParam("goodsId") long  goodsId ,
                                @RequestParam("userId") long  userId , @RequestParam(value = "content", required = false) String  content ,
                                @RequestParam(value = "video", required = false) MultipartFile  video ,
                                @RequestParam("commentScore") int  commentScore,
                                @RequestParam("logisticsScore") int  logisticsScore,
                                @RequestParam("attitudeScore") int  attitudeScore,
                                @RequestParam(value = "images", required = false) MultipartFile[] images) {
        YdShopGoodComment ydShopGoodComment = new YdShopGoodComment();
        ydShopGoodComment.setSoId(soId);
        ydShopGoodComment.setGoodsId(goodsId);
        ydShopGoodComment.setUserId(userId);
        ydShopGoodComment.setContent(content);
        ydShopGoodComment.setCommentScore(commentScore);
        ydShopGoodComment.setLogisticsScore(logisticsScore);
        ydShopGoodComment.setAttitudeScore(attitudeScore);
        boolean insertComment = ydShopGoodCommentService.insertComment(ydShopGoodComment, video, images);
        if(insertComment){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"新增失败");
        }
    }

    @ApiOperation("删除商品评论")
    @PostMapping("/removeComment")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="评论id",required=true,paramType="query")
    })
    public Body removeComment(@RequestParam("id") long  id ) {
        int deleteComment = ydShopGoodCommentService.deleteComment(id);
        if(deleteComment>0){
            return Body.newInstance();
        }else{
            return Body.newInstance(201,"删除失败");
        }
    }

    @ApiOperation("获取商品所有评论")
    @PostMapping("/getComment")
    @ApiImplicitParams({
            @ApiImplicitParam(name="goodsId",value="商品id",required=true,paramType="query")
    })
    public Body getComment(@RequestParam("goodsId") long  goodsId ) {
        List<YdShopGoodComment> ydShopGoodComments = ydShopGoodCommentService.selectCommentByGoodsId(goodsId);
        if(ydShopGoodComments.size()>0){
            return Body.newInstance(ydShopGoodComments);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("获取好评数、中评数、差评数")
    @PostMapping("/selectCommentNumToType")
    @ApiImplicitParams({
            @ApiImplicitParam(name="goodsId",value="商品id",required=true,paramType="query")
    })
    public Body selectCommentNumToType(@RequestParam("goodsId") long  goodsId ) {
        HashMap<String, Integer> stringIntegerHashMap = ydShopGoodCommentService.selectCommentNumToType(goodsId);
        if(stringIntegerHashMap != null){
            return Body.newInstance(stringIntegerHashMap);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }

    @ApiOperation("获取商品好评或中评或差评 1：好评；2：中评；3：差评")
    @PostMapping("/getCommentByType")
    @ApiImplicitParams({
            @ApiImplicitParam(name="goodsId",value="商品id",required=true,paramType="query"),
            @ApiImplicitParam(name="type",value="评论类别",required=true,paramType="query")
    })
    public Body getCommentByType(@RequestParam("goodsId") long  goodsId ,@RequestParam("type") int  type) {
        List<YdShopGoodComment> ydShopGoodComments = ydShopGoodCommentService.selectCommentByGoodsIdAndType(goodsId,type);
        if(ydShopGoodComments.size()>0){
            return Body.newInstance(ydShopGoodComments);
        }else{
            return Body.newInstance(201,"无数据");
        }
    }


}
