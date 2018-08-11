package com.acfun.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
/**
 * 自动生成代码
 * @author an
 * @since
 */
public class GeneratorApplicationTests {

	@Test
	public void generateCode(){
		/**
		 * 包名
		 */
		String packageName = "com.acfun.core";
		/**
		 * 输出目录
		 */
		String outputPath = "D:\\vscloud-generator";
		/**
		 * 表名
		 */
		String[] tableNames = new String[] {"sys_user"};
		/**
		 * 作者
		 */
		String author = "an";
		generateByTables(packageName,outputPath,author,tableNames);
	}

	private void generateByTables(String packageName,String outputPath,String author,String... tableNames){
		GlobalConfig config = new GlobalConfig();
		String dbUrl = "jdbc:mysql://47.106.131.156:3306/blog?serverTimezone=UTC&characterEncoding=utf-8&useSSL=false";
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL)
				.setUrl(dbUrl)
				.setUsername("root")
				.setPassword("password")
				.setDriverName("com.mysql.jdbc.Driver");
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig
				.setCapitalMode(true)
				.setEntityLombokModel(true)
				.setSuperEntityClass("com.acfun.core.base.SuperEntity")
				.setSuperServiceClass("com.baomidou.mybatisplus.service.IService")
				.setSuperServiceImplClass("com.baomidou.mybatisplus.service.impl.ServiceImpl")
				.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper")
				.setSuperEntityColumns(new String[]{"id","created_by","created_date","last_modified_by","last_modified_date"})
				.setDbColumnUnderline(true)
				.setRestControllerStyle(true)
				.setNaming(NamingStrategy.underline_to_camel)
				.setInclude(tableNames);
		config.setActiveRecord(false)
				.setAuthor(author)
				.setOutputDir(outputPath)
				.setBaseResultMap(true)
				.setBaseColumnList(true)
				.setMapperName("%sRepository")
				.setServiceName("%sService")
				.setEnableCache(false)
				.setFileOverride(true);
		new AutoGenerator().setGlobalConfig(config)
				.setDataSource(dataSourceConfig)
				.setStrategy(strategyConfig)
				.setPackageInfo(
						new PackageConfig()
								.setParent(packageName)
								.setController("controller")
								.setEntity("entity")
								.setService("service")
								.setServiceImpl("service")
				).execute();
	}

}
