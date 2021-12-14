package com.fhd.springboot.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成启动类
 *
 * @author fhd
 * @date 2020年10月9日
 */
public class CodeGenerator {

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

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        gc.setAuthor("KeHui");//开发人员
        gc.setOpen(false); //是否打开输出目录
        gc.setFileOverride(true);//是否覆盖已有文件
        gc.setBaseResultMap(true);//XML文件 开启 BaseResultMap
        gc.setBaseColumnList(true);//开启 baseColumnList
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setEnableCache(false);//是否在xml中添加二级缓存配置
//        gc.setKotlin(true);//开启 Kotlin 模式
//        gc.setActiveRecord(true);//开启 ActiveRecord 模式
//        gc.setIdType(IdType.NONE);//指定生成的主键的ID类型
        gc.setServiceName("%sService");//service 命名方式

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/my_db_test?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
//        dsc.setTypeConvert(new ITypeConvert() {
//            @Override
//            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
//                String t = fieldType.toLowerCase();
//        		//datetime默认生成的java类型为localDateTime, 改成Date类型
//                if(t.contains("datetime")){
//                    return DbColumnType.DATE;
//                }
//                //其它字段采用默认转换（非mysql数据库可以使用其它默认的数据库转换器）
//                return new MySqlTypeConvert().processTypeConvert(globalConfig,fieldType);
//            }
//        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.huigege");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service");
        pc.setMapper("mapper");
//        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

//        cfg.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录，自定义目录用");
//                if (fileType == FileType.MAPPER) {
//                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
//                    return !new File(filePath).exists();
//                }
//                // 允许生成模板文件
//                return true;
//            }
//        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
//        templateConfig.setService("templates/service2.java");//生成service 不需要serviceImpl
//        templateConfig.setServiceImpl(null);
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setEntityTableFieldAnnotationEnable(true);//是否生成实体时，生成字段注解
//        strategy.setSuperEntityClass("com.fhd.springboot.common.BaseEntity");//父类实体,没有就不用设置,尽量不要用,影响SQL生成
//        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);//生成 @RestController 控制器
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(false);//驼峰转连字符
        strategy.setTablePrefix("t_");//去除表前缀
        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
