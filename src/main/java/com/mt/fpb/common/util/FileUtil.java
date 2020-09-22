package com.mt.fpb.common.util;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.mt.fpb.model.vo.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 上传、下载工具类
 *
 * @author administered
 * @date 2020-04-29 10:07
 */
@RequestMapping("file")
@RestController
@CrossOrigin
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    @Value("${system.upload.imagePath}")
    private String tempUpload;
    @Value("${system.upload.findDir}")
    private String path;

    /**
     * 上传   图片
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public CommonResult imageUpload(@RequestBody MultipartFile file) throws FileNotFoundException {
        if (file.isEmpty()) {
            return CommonResult.fail(-1, "上传失败，文件不能为空!");
        }
        // 获取文件名 xx.jpg
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 获取到项目的根路径  作为文件上传的位置

        // 创建新的文件名称
        fileName = IdUtil.simpleUUID() + suffixName;
        // 上传位置
        String filePath = tempUpload + fileName;
        File dest = cn.hutool.core.io.FileUtil.touch(filePath);
        try {
            file.transferTo(dest);
            logger.info("上传成功后的文件路径未：" + tempUpload + fileName);
            JSONObject json = new JSONObject();
            json.put("msg", CommonResult.success("上传成功!"));
            json.put("path", path + fileName);
            // 返回文件上传地址
            return CommonResult.success(json);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.fail(-1, "上传失败!");
    }

}
