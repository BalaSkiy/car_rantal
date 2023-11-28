package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author ch
 * @since 2023-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Managercar implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer vid;

    /**
     * 车牌号
     */
    private String license;

    /**
     * 日租价
     */
    @TableField("dayRate")
    private Double dayrate;

    /**
     * 是否上架
     */
    @TableField("onShelf")
    private Integer onshelf;

    /**
     * 是否出租中
     */
    @TableField("isRenting")
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

    private Integer typeid;

    /**
     * 车辆品牌
     */
    private String brand;

    /**
     * 车型名称
     */
    private String typename;

    /**
     * 车型描述
     */
    private String typedescription;


}
