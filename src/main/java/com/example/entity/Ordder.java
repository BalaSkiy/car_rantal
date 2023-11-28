package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ch
 * @since 2023-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Ordder implements Serializable {

    private Integer id;

    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    /**
     * 车辆id
     */
    private Integer vid;

    /**
     * 预约取车时间
     */
    private String datetime;

    /**
     * 租车开始时间
     */
    private String begintime;

    /**
     * 结束时间
     */
    private String endtime;

    /**
     * 租借天数
     */
    private Integer rentaldays;

    /**
     * 总金额
     */
    private Double amount;

    /**
     * 用户评分
     */
    private String mark;

    /**
     * 订单进度（0已取消，1待取车，2待还车，3待评价）
     */
    private Integer isend;

}
