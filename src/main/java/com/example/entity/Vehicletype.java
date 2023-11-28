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
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Vehicletype implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "typeid", type = IdType.AUTO)
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
