package com.gcc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author gcc
 * @since 2023-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Ability implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String courseId;

    private String userId;

    private Double ability;

    private String abilityList;

    public Ability() {
    }

    public Ability(String courseId) {
        this.courseId = courseId;
    }

    public Ability(String courseId, String userId) {
        this.courseId = courseId;
        this.userId = userId;
    }
}
