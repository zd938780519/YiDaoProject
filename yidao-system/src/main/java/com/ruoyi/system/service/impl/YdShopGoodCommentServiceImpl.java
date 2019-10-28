package com.ruoyi.system.service.impl;

import com.ruoyi.common.oss.OSSFileUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.YdShopGoodComment;
import com.ruoyi.system.domain.YdShopGoodCommentImage;
import com.ruoyi.system.mapper.YdShopGoodCommentImageMapper;
import com.ruoyi.system.mapper.YdShopGoodCommentMapper;
import com.ruoyi.system.service.YdShopGoodCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class YdShopGoodCommentServiceImpl implements YdShopGoodCommentService {
    @Autowired(required = false)
    YdShopGoodCommentMapper ydShopGoodCommentMapper;
    @Autowired(required = false)
    YdShopGoodCommentImageMapper ydShopGoodCommentImageMapper;
    @Override
    public int insert(YdShopGoodComment record) {
        return ydShopGoodCommentMapper.insert(record);
    }

    @Override
    public List<YdShopGoodComment> selectAll() {
        return ydShopGoodCommentMapper.selectAll();
    }

    @Override
    public int getCommentNumByGoodsId(long goodsId) {
        return ydShopGoodCommentMapper.getCommentNumByGoodsId(goodsId);
    }

    @Override
    public YdShopGoodComment getCommentByGoodsIdIsNew(long goodsId) {
        return ydShopGoodCommentMapper.getCommentByGoodsIdIsNew(goodsId);
    }

    @Override
    public List<YdShopGoodComment> getFavorableRate(long goodsId) {
        return ydShopGoodCommentMapper.getFavorableRate(goodsId);
    }

    @Override
    public boolean insertComment(YdShopGoodComment ydShopGoodComment, MultipartFile video, MultipartFile[] images) {
        if(video != null ){
            String fileKey = "video/goodsComment/commentVideo/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(video);
            String path = OSSFileUtil.upload(fileKey, video);
            ydShopGoodComment.setVideoPath(path);
        }else{
            ydShopGoodComment.setVideoPath("");
        }
        int insertComment = ydShopGoodCommentMapper.insert(ydShopGoodComment);
        if(insertComment>0){
            List<YdShopGoodCommentImage> shopGoodCommentImages = new ArrayList<YdShopGoodCommentImage>();
            if(images != null){
                for (int i=0;i<images.length;i++){
                    String fileKey = "image/goodsComment/commentImage/" + UUID.randomUUID().toString()+"."+ OSSFileUtil.getSuffix(images[i]);
                    String path = OSSFileUtil.upload(fileKey, images[i]);
                    YdShopGoodCommentImage item = new YdShopGoodCommentImage();
                    item.setPhotoPath(path);
                    item.setSgcId(ydShopGoodComment.getId());
                    shopGoodCommentImages.add(item);
                }
                if(images.length>0){
                    int insertShopGoodCommentImage = ydShopGoodCommentImageMapper.insertShopGoodCommentImage(shopGoodCommentImages);
                    if(insertShopGoodCommentImage>0){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    return true;
                }
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    @Override
    public int deleteComment(long id) {
        List<String> fileName = new ArrayList<String>();
        YdShopGoodComment shopGoodComment = ydShopGoodCommentMapper.selectCommentById(id);
        String videoPath = shopGoodComment.getVideoPath();
        int deleteComment = ydShopGoodCommentMapper.deleteComment(id);
        if(deleteComment>0){
            if(StringUtils.isNotEmpty(videoPath) && videoPath.indexOf("?")>-1){
                fileName.add(videoPath.substring(0,videoPath.indexOf("?")));
            }
            List<YdShopGoodCommentImage> ydShopGoodCommentImages = ydShopGoodCommentImageMapper.selectCommentImageBySgcId(id);
            ydShopGoodCommentImageMapper.deleteCommentImage(id);
            for (YdShopGoodCommentImage ydShopGoodCommentImage :ydShopGoodCommentImages) {
                String photoPath = ydShopGoodCommentImage.getPhotoPath();
                if(StringUtils.isNotEmpty(photoPath) && photoPath.indexOf("?")>-1){
                    fileName.add(photoPath.substring(0,photoPath.indexOf("?")));
                }
            }
            //数据库图片链接删除之后删除云服务器上的文件
            for (String name:fileName) {
                OSSFileUtil.remove(name);
            }
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public List<YdShopGoodComment> selectCommentByGoodsId(long goodsId) {
        return ydShopGoodCommentMapper.selectCommentByGoodsId(goodsId);
    }

    @Override
    public HashMap<String, Integer> selectCommentNumToType(long goodsId) {
        return ydShopGoodCommentMapper.selectCommentNumToType(goodsId);
    }

    @Override
    public List<YdShopGoodComment> selectCommentByGoodsIdAndType(long goodsId, int type) {
        return ydShopGoodCommentMapper.selectCommentByGoodsIdAndType(goodsId,type);
    }


}
