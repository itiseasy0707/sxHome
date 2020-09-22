package com.mt.fpb.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mt.fpb.model.Oldman;
import tk.mybatis.mapper.common.BaseMapper;

public class EasyExcelListener extends AnalysisEventListener<Oldman> {
    private BaseMapper mapper;

    public EasyExcelListener() {

    }
    public EasyExcelListener(BaseMapper mapper) {
        this.mapper=mapper;
    }


    /**
     * 老人表Excel导入
     * @param t 老人信息
     * @param analysisContext
     */
    @Override
    public void invoke(Oldman t, AnalysisContext analysisContext) {
        System.out.println(t);
        mapper.insert(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("finish--------");
    }
}
