package com.zhangmingge.mpdemo02.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 配置类
 */
@Configuration
@MapperScan("com.zhangmingge.mpdemo02.mapper") // 开启 mapper 扫描
public class MpConfig {

    /**
     * 乐观锁插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    //逻辑删除插件
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * SQL 执行性能分析插件<br>
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长<br>
     * 三种环境<br>
     *      * dev：开发环境<br>
     *      * test：测试环境<br>
     *      * prod：生产环境<br>
     */
    @Bean
    @Profile({"dev", "test"}) // 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(2); // ms，超过此处设置的 ms 则 sql 不执行
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
