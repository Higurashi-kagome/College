package com.zhangmingge.mpdemo02.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data // 生成 getter setter 方法和有参无参构造（可在类结构中查看生成的方法）
public class User {
    @TableId(type = IdType.AUTO) // 设置主键生成策略为自增（要求数据库对应主键设置为 auto_increment）
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableField(fill = FieldFill.INSERT) // 在 insert 时自动填充数据
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE) // 在 insert 和 update 时自动填充数据
    private Date updateTime;
    @Version
    private Integer version;// 版本号
    @TableLogic
    private Integer deleted;// 逻辑删除
}