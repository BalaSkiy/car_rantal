package com.example.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.sql.Timestamp;


import lombok.Data;
import lombok.EqualsAndHashCode;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;


/**
 * <p>
 * VIEW
 * </p>
 *
 * @author ch
 * @since 2023-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Orderview implements Serializable {

    private Integer id;

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

    private Integer oid;

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

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户性别
     */
    private Boolean sex;


}
