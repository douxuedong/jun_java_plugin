package com.xiaoleilu.hutool.db.ds.hikari;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.xiaoleilu.hutool.db.DbRuntimeException;
import com.xiaoleilu.hutool.db.ds.DSFactory;
import com.xiaoleilu.hutool.io.IoUtil;
import com.xiaoleilu.hutool.setting.Setting;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * HikariCP数据源工厂类
 * @author Wujun
 *
 */
public class HikariDSFactory extends DSFactory {
	
	private Setting setting;
	/** 数据源池 */
	private Map<String, HikariDataSource> dsMap;
	
	public HikariDSFactory() {
		this(null);
	}
	
	public HikariDSFactory(Setting setting) {
		super("HikariCP");
		checkCPExist(HikariDataSource.class);
		if(null == setting){
			setting = new Setting(DEFAULT_DB_SETTING_PATH, true);
		}
		this.setting = setting;
		this.dsMap = new ConcurrentHashMap<>();
	}

	@Override
	public DataSource getDataSource(String group) {
		if (group == null) {
			group = StrUtil.EMPTY;
		}
		
		// 如果已经存在已有数据源（连接池）直接返回
		final HikariDataSource existedDataSource = dsMap.get(group);
		if (existedDataSource != null) {
			return existedDataSource;
		}

		final HikariDataSource ds = createDataSource(group);
		// 添加到数据源池中，以备下次使用
		dsMap.put(group, ds);
		return ds;
	}

	@Override
	public void close(String group) {
		if (group == null) {
			group = StrUtil.EMPTY;
		}

		HikariDataSource ds = dsMap.get(group);
		if (ds != null) {
			IoUtil.close(ds);
			dsMap.remove(group);
		}
	}

	@Override
	public void destroy() {
		if(CollectionUtil.isNotEmpty(dsMap)){
			Collection<HikariDataSource> values = dsMap.values();
			for (HikariDataSource ds : values) {
				IoUtil.close(ds);
			}
			dsMap.clear();
			dsMap = null;
		}
	}

	/**
	 * 创建数据源
	 * @param group 分组
	 * @return Hikari数据源 {@link HikariDataSource}
	 */
	private HikariDataSource createDataSource(String group){
		if (group == null) {
			group = StrUtil.EMPTY;
		}
		
		Properties config = setting.getProperties(group);
		if(CollectionUtil.isEmpty(config)){
			throw new DbRuntimeException("No HikariCP config for group: [{}]", group);
		}
		final HikariDataSource ds = new HikariDataSource(new HikariConfig(config));
		return ds;
	}
}
