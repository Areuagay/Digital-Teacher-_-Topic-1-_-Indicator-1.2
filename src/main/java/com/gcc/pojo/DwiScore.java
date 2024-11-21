package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class DwiScore implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "Id", type = IdType.AUTO)
    private Long Id;

    @TableField(value = "ScoreItemId")
    private Long ScoreItemId;

    @TableField(value = "UserNumber")
    private String UserNumber;

    @TableField(value = "Score")
    private BigDecimal Score;

    @TableField(value = "CreateTime")
    private Date CreateTime;

    @TableField(value = "UpdateTime")
    private Date UpdateTime;

    @TableField(value = "Data")
    private String Data;

    @TableField(value = "Deleted")
    private String Deleted;

    @TableField(value = "OrganizationCode")
    private String OrganizationCode;


}
