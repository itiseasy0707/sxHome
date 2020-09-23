package com.mt.fpb.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.mt.fpb.common.util.HintUtil;
import com.mt.fpb.mapper.OldHealthMapper;
import com.mt.fpb.mapper.OldmanMapper;
import com.mt.fpb.model.OldHealth;
import com.mt.fpb.model.OldRelation;
import com.mt.fpb.model.Oldman;
import com.mt.fpb.model.dto.BaseQueryParams;
import com.mt.fpb.model.vo.CommonPage;
import com.mt.fpb.model.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 健康数据
 *
 * @author makejava
 * @since 2020-09-22 10:59:08
 */
@RestController
@RequestMapping("/oldHealthController")
public class OldHealthController {

    @Autowired
    private OldHealthMapper oldHealthMapper;

    /**
     * 健康数据列表
     *
     * @param queryParams 分页参数
     * @return
     */
    @GetMapping("/list")
    public CommonResult list(BaseQueryParams queryParams) {

        PageHelper.startPage(queryParams.getPage(), queryParams.getPageSize());
        // 健康数据列表
        Example example = new Example(OldHealth.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", queryParams.getUserId());
        List<OldHealth> list = oldHealthMapper.selectByExample(example);

//        List<Oldman> list = oldmanMapper.selectAll();
        return CommonResult.success(CommonPage.restPage(list));
    }

    /**
     * 详情
     *
     * @param oldHealth
     * @return
     */
    @GetMapping("/getById")
    public CommonResult getById(OldHealth oldHealth) {
        if (StringUtils.isEmpty(oldHealth.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        OldHealth oldHealth1 = oldHealthMapper.selectOne(oldHealth);
        return CommonResult.success(oldHealth1);
    }

    /**
     * 修改
     *
     * @param oldHealth
     * @return
     */
    @PostMapping("/update")
    public CommonResult update(@RequestBody OldHealth oldHealth) {
        if (StringUtils.isEmpty(oldHealth.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        Example example = new Example(OldHealth.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", oldHealth.getId());
        int cnt = oldHealthMapper.updateByExample(oldHealth, example);
        return HintUtil.update(cnt);
    }

//    /**
//     * 添加   导入
//     * @param oldman 待添加老人信息
//     * @return
//     */
//    @PostMapping("/add")
//    public CommonResult add(@RequestBody Oldman oldman){
//        oldmanMapper.insert(oldman);
//        return CommonResult.success(1);
//    }

    /**
     * 删除
     *
     * @param oldHealth
     * @return
     */
    @DeleteMapping("/delete")
    public CommonResult delete(OldHealth oldHealth) {
        if (StringUtils.isEmpty(oldHealth.getId())) {
            return CommonResult.fail(-1, "id不能为空");
        }
        int cnt = oldHealthMapper.delete(oldHealth);
        return HintUtil.delete(cnt);
    }



    /**
     * 健康数据信息导入
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/import")
    public CommonResult importExcel(MultipartFile file, String userId) throws Exception {

        if(StringUtils.isEmpty(userId)){
            return CommonResult.fail(-1,"userId不能为空");
        }
        if(StringUtils.isEmpty(file)){
            return CommonResult.fail(-1,"excel文件不能为空");
        }
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        if (reader.readAll().size() > 0) { // excel中存在数据
            List<Map<String, Object>> read = reader.read(1, 2, 2147483647);

            for (int i = 0; i < read.size(); i++) {
                // 1、身份证号  必填项
                if(StringUtils.isEmpty(read.get(i).get("身份证号"))){
                    return CommonResult.fail(-1,"第"+ i+"行，身份证号不能为空");
                }
                String idCard = read.get(i).get("身份证号").toString();

                // 2、跟老人信息表中比对  是否存在此身份证好的老人

                //3、根据 身份证号 进行比对健康表中数据  存在，则修改 不存在，新增

                //4、将userId，赋给每个对象

                //5、新增和修改操作

//                int cnt =  teacherService.checkTeacherByName(name);
//                if(cnt < 0 ){ // 不存在该教师
//                    return ResultUtil.error("第"+i+"行"+name+"老师不存在");
//                }

                // 往对象中填充值
//                Salary salary = new Salary();
//
//                String id = IdUtil.simpleUUID();
//                salary.setId(id);
//                salary.setTeacherId(name);
//                salary.setSalaryTime(time);
//                if(!StringUtils.isEmpty(read.get(i).get("部门"))){
//                    salary.setDepart(read.get(i).get("部门").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("底薪"))){
//                    salary.setBaseSalary(read.get(i).get("底薪").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("全勤"))){
//                    salary.setAllDay(read.get(i).get("全勤").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("教师资格证"))){
//                    salary.setCertificate(read.get(i).get("教师资格证").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("工龄奖"))){
//                    salary.setWorkAge(read.get(i).get("工龄奖").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("保险"))){
//                    salary.setGuarantee(read.get(i).get("保险").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("学历补助"))){
//                    salary.setXueli(read.get(i).get("学历补助").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("管理岗位津贴"))){
//                    salary.setJintie(read.get(i).get("管理岗位津贴").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("管理岗位提成"))){
//                    salary.setGlTicheng(read.get(i).get("管理岗位提成").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("招生提成"))){
//                    salary.setZsTicheng(read.get(i).get("招生提成").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("其他"))){
//                    salary.setPjTicheng(read.get(i).get("其他").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("宣传奖"))){
//                    salary.setXuanchuan(read.get(i).get("宣传奖").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("人次费"))){
//                    salary.setRenci(read.get(i).get("人次费").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("超课节费"))){
//                    salary.setBeyondClass(read.get(i).get("超课节费").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("周末餐补"))){
//                    salary.setWeekendEat(read.get(i).get("周末餐补").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("托育主班奖"))){
//                    salary.setTuoyu(read.get(i).get("托育主班奖").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("出勤奖"))){
//                    salary.setAttendance(read.get(i).get("出勤奖").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("班级卫生奖"))){
//                    salary.setHealthClass(read.get(i).get("班级卫生奖").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("安全奖"))){
//                    salary.setSecurity(read.get(i).get("安全奖").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("家长满意度奖"))){
//                    salary.setSatisfy(read.get(i).get("家长满意度奖").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("进步奖"))){
//                    salary.setAdvance(read.get(i).get("进步奖").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("加班"))){
//                    salary.setAllNight(read.get(i).get("加班").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("推荐教师奖"))){
//                    salary.setRecommendTeacher(read.get(i).get("推荐教师奖").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("师徒感恩费"))){
//                    salary.setThankTeacher(read.get(i).get("师徒感恩费").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("合计"))){
//                    salary.setSummary(read.get(i).get("合计").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("师徒感恩费(扣)"))){
//                    salary.setMoneyPupil(read.get(i).get("师徒感恩费(扣)").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("教学合伙人"))){
//                    salary.setCooperation(read.get(i).get("教学合伙人").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("请假"))){
//                    salary.setLeaveWork(read.get(i).get("请假").toString());
//                }
//                if(!StringUtils.isEmpty(read.get(i).get("实发"))){
//                    salary.setRealSalary(read.get(i).get("实发").toString());
//                }
//                Salary salaryUpdate = salaryMapper.findByNameAndTime(name,time);
//
//                if(salaryUpdate != null){ // 不为空  则修改
//                    // 如果时间和名称都重复 就更改
//                    salary.setName(name);
//                    salary.setTime(time);
//                    salaryMapper.updateSalaryData(salary);
//                }else {
//                    int cnt02 = salaryMapper.insertToSalary(salary);
//
//                }
//            }
//            return ResultUtil.success();
//
//        }
        return CommonResult.fail(-1,"excle表格不为空!");
    }


















}