package com.cqc.portal;

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
@MapperScan({"com.cqc.portal.mapper","com.cqc.portal.dao"})
public class PortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }

}
