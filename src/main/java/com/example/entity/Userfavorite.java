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
 * @since 2023-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Userfavorite implements Serializable {

    private Integer id;

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    private Integer vid;


}
