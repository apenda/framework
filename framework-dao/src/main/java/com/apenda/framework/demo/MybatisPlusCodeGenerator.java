package com.apenda.framework.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

/**
 * @author rui.zhou
 * @date 2021/06/01 15:07
 **/
public class MybatisPlusCodeGenerator {

    // ================= 自定义配置  =================

    // mysql数据源信息
    private static final DbType DB_TYPE = DbType.MYSQL;
    private static final String JDBC_URL = "jdbc:mysql://121.5.112.73:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String SCHEMA_NAME = "";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123456";

    // oracle数据源信息(SCHEMA_NAME必须配置)
    /*private static final DbType DB_TYPE = DbType.ORACLE;
    private static final String JDBC_URL = "jdbc:oracle:thin:@192.168.0.1:1521:test";
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String SCHEMA_NAME = "schemaName";
    private static final String JDBC_USERNAME = "userName";
    private static final String JDBC_PASSWORD = "password";*/
    /**
     * 生成文件根目录及包名
     */
    private static final String ROOT_DIR =System.getProperty("user.dir") + "/src/main/java";
    /**
     * java文件夹下路径
     */
    private static final String PACKAGE_PARENT_NAME = "com.apenda";
    private static final String PACKAGE_CONTROLLER_NAME = "core.controller";
    private static final String PACKAGE_SERVICE_NAME = "component.service";
    private static final String PACKAGE_SERVICE_IMPL_NAME = "component.service.impl";
    private static final String PACKAGE_ENTITY_NAME = "dao.entity";
    private static final String PACKAGE_MAPPER_NAME = "dao.mapper";
    private static final String RESOURCES_DIR =System.getProperty("user.dir") + "/src/main/resources";
    private static final String MAPPER_XML_PATH = "mapper";
    /**
     * 生成代码的@author
     */
    private static final String AUTHOR = "rui.zhou";
    /**
     * 数据库表名前缀
     */
    private static final String[] TABLE_PREFIXS = new String[]{"t_"};

    public static void main(String[] args) {
        // 表名区分大小写
        AutoGenerator mpg = new AutoGenerator();
        mpg.setDataSource(getDataSourceConfig());
        mpg.setGlobalConfig(getGlobalConfig());
        mpg.setStrategy(getStrategyConfig());
        mpg.setPackageInfo(getPackageConfig());
        mpg.setCfg(getInjectionConfig());
        mpg.setTemplate(getTemplateConfig());
        mpg.execute();
    }

    /**
     * 数据源配置
     *
     * @return
     */
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DB_TYPE);
        dsc.setDriverName(JDBC_DRIVER);
        dsc.setUrl(JDBC_URL);
        dsc.setSchemaName(SCHEMA_NAME);
        dsc.setUsername(JDBC_USERNAME);
        dsc.setPassword(JDBC_PASSWORD);
        return dsc;
    }

    /**
     * 全局配置
     *
     * @return
     */
    private static GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        // 生成后是否打开资源管理器
        gc.setOpen(false);
        gc.setOutputDir(ROOT_DIR);
        // 重新生成时文件是否覆盖
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // 主键策略
        gc.setIdType(IdType.AUTO);
        // 定义生成的实体类中日期类型
        gc.setDateType(DateType.ONLY_DATE);
        // 开启Swagger2模式
        gc.setSwagger2(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(true);
        gc.setAuthor(AUTHOR);
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sComponentService");
        gc.setServiceImplName("%sComponentServiceImpl");
        gc.setControllerName("%sController");
        return gc;
    }

    /**
     * 生成策略配置
     *
     * @return
     */
    private static StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        // 全局大写命名 ORACLE 注意
        strategy.setCapitalMode(true);
        // 乐观锁字段
        // strategy.setVersionFieldName("version");
        // 链式模式
        strategy.setChainModel(true);
        // 去除表名的前缀
        strategy.setTablePrefix(TABLE_PREFIXS);
        // is_xxx 去除is_ 前缀
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // 字段注释
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据表列生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // lombok 模型 @Accessors(chain = true) setter链式操作
        strategy.setEntityLombokModel(true);
        // restful api风格控制器
        strategy.setRestControllerStyle(true);
        // url中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        return strategy;
    }

    /**
     * 生成包名设置
     *
     * @return
     */
    private static PackageConfig getPackageConfig() {
        // 4.生成文件所在包配置：
        PackageConfig pc = new PackageConfig();
        pc.setParent(PACKAGE_PARENT_NAME);
        pc.setController(PACKAGE_CONTROLLER_NAME);
        pc.setService(PACKAGE_SERVICE_NAME);
        pc.setServiceImpl(PACKAGE_SERVICE_IMPL_NAME);
        pc.setEntity(PACKAGE_ENTITY_NAME);
        pc.setMapper(PACKAGE_MAPPER_NAME);
        pc.setModuleName(scanner("模块名"));
        return pc;
    }

    /**
     * xml文件配置
     *
     * @return
     */
    private static InjectionConfig getInjectionConfig() {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>(0);
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        // 如果模板引擎是 freemarker
        // String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return RESOURCES_DIR + "/" + MAPPER_XML_PATH + "/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 关闭默认 xml 生成
     *
     * @return
     */
    private static TemplateConfig getTemplateConfig() {
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        return tc;
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
