package com.cqc.admin.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


/**
 * User: Carson
 * Date: 2018/12/24
 * Description:  代码生成器
 **/
public class CodeGeneration {

    public static void main(String[] args){
        String projectPath = System.getProperty("user.dir");
        String modulePath = "/cqc";

        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                new GlobalConfig()
                        .setOutputDir(projectPath + modulePath + "/src/main/java")   //输出目录
                        .setFileOverride(true)   //是否覆盖
                        .setActiveRecord(false)  //不需要ActiveRecord特性
                        .setEnableCache(false)   //XML二级缓存
                        .setOpen(false)    //生成后打开文件夹
                        .setBaseResultMap(true)   // XML ResultMap
                        .setBaseColumnList(false)  // XML columnList
                        .setMapperName("%sMapper")
                        .setXmlName("%sMapper")
                        .setServiceName("%sService")
                        .setServiceImplName("%sServiceImpl")
                        .setControllerName("%sController")
                        .setEntityName("%s")
        ).setDataSource(
                new DataSourceConfig().setDbType(DbType.MYSQL)
                        .setUrl("jdbc:mysql://182.61.134.5:3306/cqc?useUnicode=true&characterEncoding=utf-8")
                        .setDriverName("com.mysql.cj.jdbc.Driver")
                        .setUsername("root")
                        .setPassword("!@#$Qiong1213")
        ).setStrategy(
                new StrategyConfig()
                       // .setSuperEntityClass("com.dhsq.devicemanager.domain.entity.BaseEntity")
                        //.setSuperEntityColumns(new String[] {"id", "version", "flag"})
                        .setTablePrefix(new String[] {""})
                        .setNaming(NamingStrategy.underline_to_camel)
                        .setInclude(new String[] {"pdd_account"})//这里修改表名
        ).setPackageInfo(
                new PackageConfig().setParent("com.cqc.portal")
                        .setController("controller")
                        .setService("service")
                        .setServiceImpl("service.impl")
                        .setMapper("mapper")
                        .setEntity("api.model")
                        .setXml("mapper")

        ).setCfg(new InjectionConfig() {
            @Override
            public void initMap() {

            }
        });
        mpg.execute();
    }
}
