package com.xiaoleilu.hutool.json;

/**
 * JSON支持<br>
 * 继承此类实现实体类与JSON的相互转换
 * 
 * @author Wujun
 *
 */
public class JSONSupport implements JSONString{
	
	/**
	 * JSON String转Bean
	 * @param jsonString JSON String
	 */
	public void parse(String jsonString){
		new JSONObject(jsonString).toBean(this);
	}

	/**
	 * @return JSON对象
	 */
	public JSONObject toJSON() {
		return new JSONObject(this);
	}
	
	@Override
	public String toJSONString() {
		return toJSON().toString();
	}

	/**
	 * 美化的JSON（使用回车缩进显示JSON），用于打印输出debug
	 * 
	 * @return 美化的JSON
	 */
	public String toPrettyString() {
		return toJSON().toJSONString(4);
	}

	@Override
	public String toString() {
		return toJSONString();
	}
}
