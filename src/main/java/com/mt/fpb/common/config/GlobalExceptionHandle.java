package com.mt.fpb.common.config;


import com.mt.fpb.common.enums.ResponseCode;
import com.mt.fpb.common.exception.GlobalException;
import com.mt.fpb.model.vo.CommonResult;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

/**
 * 全局异常处理器
 *
 * @author fuzhigang
 * @version 1.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandle.class);
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxSize;

    @ExceptionHandler(Exception.class)
    public CommonResult<Object> exceptionHandle(Exception e) {
        if (e instanceof GlobalException) {
            return CommonResult.fail(ResponseCode.FAILED.getCode(), e.getMessage());
        } else {
            LOGGER.error("系统异常", e);
            return CommonResult.fail(ResponseCode.FAILED.getCode(), "操作失败，系统异常");
        }
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<Object> multipartExceptionHandler(MultipartException e) {
        LOGGER.error("文件上传失败", e);
        if (e.getCause().getCause() instanceof FileUploadBase.FileSizeLimitExceededException) {
            return CommonResult.fail(ResponseCode.FAILED.getCode(), "文件上传失败, 大小不能超过" + maxSize);
        } else if (e.getCause().getCause() instanceof FileUploadBase.SizeLimitExceededException) {
            return CommonResult.fail(ResponseCode.FAILED.getCode(), "文件上传失败, 总大小不能超过100MB");
        } else {
            return CommonResult.fail(ResponseCode.FAILED.getCode(), "文件上传失败");
        }
    }
}
