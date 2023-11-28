package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ch
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Vehicle implements Serializable {

    @TableId(value = "vid", type = IdType.AUTO)
    private Integer vid;

    /**
     * 车牌号
     */
    private String license;

    /**
     * 车型id
     */
    private Integer typeid;

    /**
     * 日租价
     */
    private Double dayrate;

    /**
     * 是否上架
     */
    private Integer onshelf;

    /**
     * 是否出租中
     */
    private Integer isrenting;

    /**
     * 维修状态
     */
    private Integer repair;

    /**
     * 备注1
     */
    private String remark1;

    /**
     * 备注2
     */
    private String remark2;


}
