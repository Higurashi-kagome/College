package com.zhangmingge.mpdemo01.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

@Data // 生成 getter setter 方法和有参无参构造（可在类结构中查看生成的方法）
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}