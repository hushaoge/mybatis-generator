package com.solar.mybatis.main;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author hushaoge
 * @date 2016/11/9
 */
public class RunGenerator {
    private static String resourceDir = "F:\\IdeaWorkSpace\\Projects\\github\\test\\mybatis-generator\\src\\main\\resources\\";
    private static String generatorConfigFileName = "generatorConfig.xml";
    private static String tableNameFileName = "tables.properties";

    public static void main(String[] args) {
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            File configFile = new File(resourceDir+generatorConfigFileName);
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            Properties p = parserProps();
            Enumeration<?> enu = p.propertyNames();
            /**循环生成tables.properties配置的表，生成的名称可以自定义*/
            while (enu.hasMoreElements()) {
                String tableName = (String)enu.nextElement();
                String domainObjectName = p.getProperty(tableName);
                config.getContexts().get(0).getTableConfigurations().get(0).setTableName(tableName);
                config.getContexts().get(0).getTableConfigurations().get(0).setDomainObjectName(domainObjectName);
                MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
                myBatisGenerator.generate(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
    }

    private static Properties parserProps(){
        // 生成文件对象
        File pf = new File(resourceDir+tableNameFileName);
        // 生成文件输入流
        FileInputStream inpf = null;
        try {
            inpf = new FileInputStream(pf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 生成properties对象
        Properties p = new Properties();
        try {
            p.load(inpf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
