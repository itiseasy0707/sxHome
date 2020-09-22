package com.mt.fpb.mapper;

import com.mt.fpb.common.config.MyMapper;
import com.mt.fpb.model.Relation;
import org.springframework.stereotype.Repository;

/**
 * 亲属表(Relation)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-22 15:42:18
 */
@Repository
public interface RelationMapper extends MyMapper<Relation> {

}