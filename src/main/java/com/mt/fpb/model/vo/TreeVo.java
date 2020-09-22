package com.mt.fpb.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author g
 * @date 2020-02-08 20:21
 */
@Data
public class TreeVo {
    private Long id;
    private String name;
    private Integer role;
    private List<TreeVo> children;
}
