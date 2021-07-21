package cn.ag.common.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;

public class GenteratorCode {
    //运行main方法就可以生成代码了
    public static void main(String[] args) throws InterruptedException {
        //用来获取Mybatis-Plus.properties文件的配置信息
        //不要加后缀
        final ResourceBundle rb = ResourceBundle.getBundle("mybatiesplus-config");
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(rb.getString("OutputDir"));
        gc.setFileOverride(true);
        gc.setSwagger2(true);//实体属性swagger2 注解
        gc.setActiveRecord(true);// 开启 activeRecord 模式
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor(rb.getString("author"));
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert());
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(rb.getString("jdbc.user"));
        dsc.setPassword(rb.getString("jdbc.pwd"));
        dsc.setUrl(rb.getString("jdbc.url"));
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[] { "tb_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setLogicDeleteFieldName("deleted");
        strategy.setInclude(new String[]{
                "tb_user_role_relation",
        }); // 需要生成的表
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(rb.getString("parent")); //基本包 edu.lzy
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("model");
        pc.setMapper("mapper");
//        pc.setModuleName("cn.ag.channel");
        //定义query
        Map<String,String> myselfPackage = new HashMap<>();
        myselfPackage.put("query","query");
        pc.setPathInfo(myselfPackage);
        mpg.setPackageInfo(pc);
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
                map.put("basePath","cn.ag.channel");
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        //拼接基本路径 + "/" + pc.getModuleName()
        String parentDir = strRep(rb.getString("OutputDir") + "/" + pc.getParent());
        // 调整 controller 生成目录演示
        focList.add(new FileOutConfig("/templates/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //controller输出完整路径
                return parentDir + "/controller/" + tableInfo.getEntityName() + "Controller.java";
            }
        });
        // 调整 query 生成目录演示
        focList.add(new FileOutConfig("/templates/query.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //query输出完整路径
                return parentDir + "/query/" + tableInfo.getEntityName() + "Query.java";
            }
        });
        // 调整 domain 生成目录演示 ， 你的domain到底要输出到哪儿？？？？，你的domain怎么输出
        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //domain输出完整路径
                return parentDir + "/model/" + tableInfo.getEntityName() + ".java";
            }
        });
        // 调整 service
        focList.add(new FileOutConfig("/templates/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //domain输出完整路径
                return parentDir + "/service/" + tableInfo.getServiceName() + ".java";
            }
        });
        // 调整 service
        focList.add(new FileOutConfig("/templates/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //domain输出完整路径
                return parentDir + "/service/Impl/" + tableInfo.getServiceImplName() + ".java";
            }
        });
        // 调整 mapper
        focList.add(new FileOutConfig("/templates/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //domain输出完整路径
                return parentDir + "/mapper/" + tableInfo.getMapperName() + ".java";
            }
        });
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {// + "/" + strRep(pc.getParent())
                return rb.getString("OutputDirXml") + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setService(null);///templates/service.java.vm
        tc.setServiceImpl(null);//"/templates/serviceImpl.java.vm"
        tc.setEntity(null);
        tc.setMapper(null);//"/templates/mapper.java.vm"
        tc.setController(null);
        tc.setXml(null);
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
    }
    /*将包名转换为文件夹格式*/
    public static String strRep(String s){
        return s.replace(".","/");
    }
}
