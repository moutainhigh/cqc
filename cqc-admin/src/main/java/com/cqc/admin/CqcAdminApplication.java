package com.cqc.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description：
 * @author： wanglz
 * @date： 2020-01-16
 **/

@Slf4j
@SpringBootApplication
@MapperScan({"com.cqc.admin.mapper","com.cqc.admin.dao"})
public class CqcAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CqcAdminApplication.class, args);
    }

}
