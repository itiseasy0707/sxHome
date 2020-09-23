package com.mt.fpb.common.util;

import com.mt.fpb.model.vo.CommonResult;

/**
 * 提示信息语
 */
public class HintUtil {

    /**
     * 新增提示语
     * @param cnt
     * @return
     */
    public static CommonResult insert(int cnt){
        if(cnt > 0){ // 新增成功
            return CommonResult.success("新增成功");
        }else { // 新增失败
            return CommonResult.fail(-1,"新增失败");
        }
    }

    /**
     * 删除提示语
     * @param cnt
     * @return
     */
    public static CommonResult delete(int cnt){
        if(cnt > 0){ // 删除成功
            return CommonResult.success("删除成功");
        }else { // 删除失败
            return CommonResult.fail(-1,"删除失败");
        }
    }

    /**
     * 修改提示语
     * @param cnt
     * @return
     */
    public static CommonResult update(int cnt){
        if(cnt > 0){ // 修改成功
            return CommonResult.success("修改成功");
        }else { // 修改失败
            return CommonResult.fail(-1,"修改失败");
        }
    }


//    /**
//     * 审核提示语
//     * @param cnt
//     * @return
//     */
//    public static Result check(int cnt){
//        if(cnt > 0){ // 审核成功
//            return ResultUtil.success("审核成功");
//        }else { // 审核失败
//            return ResultUtil.error("审核失败");
//        }
//    }


//    /**
//     * 导入提示语
//     * @param cnt
//     * @return
//     */
//    public static Result importExcel(int cnt){
//        if(cnt > 0){ // 审核成功
//            return ResultUtil.success("导入成功");
//        }else { // 审核失败
//            return ResultUtil.error("导入失败");
//        }
//    }

}
