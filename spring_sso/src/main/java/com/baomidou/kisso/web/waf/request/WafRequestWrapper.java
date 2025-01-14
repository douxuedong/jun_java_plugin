/*
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.kisso.web.waf.request;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.baomidou.kisso.web.waf.WafHelper;

/**
 * Request请求过滤包装
 * <p>
 *
 * @author Wujun
 * @since 2014-5-8
 */
public class WafRequestWrapper extends HttpServletRequestWrapper {

  private boolean filterXSS = true;

  private boolean filterSQL = true;


  public WafRequestWrapper(HttpServletRequest request, boolean filterXSS, boolean filterSQL) {
    super(request);
    this.filterXSS = filterXSS;
    this.filterSQL = filterSQL;
  }


  public WafRequestWrapper(HttpServletRequest request) {
    this(request, true, true);
  }


  /**
   * @param parameter 过滤参数
   * @return
   * @since 数组参数过滤
   */
  @Override
  public String[] getParameterValues(String parameter) {
    String[] values = super.getParameterValues(parameter);
    if (values == null) {
      return null;
    }

    int count = values.length;
    String[] encodedValues = new String[count];
    for (int i = 0; i < count; i++) {
      encodedValues[i] = filterParamString(values[i]);
    }

    return encodedValues;
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Map getParameterMap() {
    Map<String, String[]> primary = super.getParameterMap();
    Map<String, String[]> result = new HashMap<String, String[]>(primary.size());
    for (Map.Entry<String, String[]> entry : primary.entrySet()) {
      result.put(entry.getKey(), filterEntryString(entry.getValue()));
    }
    return result;

  }

  protected String[] filterEntryString(String[] rawValue) {
    for (int i = 0; i < rawValue.length; i++) {
      rawValue[i] = filterParamString(rawValue[i]);
    }
    return rawValue;
  }

  /**
   * @param parameter 过滤参数
   * @return
   * @since 参数过滤
   */
  @Override
  public String getParameter(String parameter) {
    return filterParamString(super.getParameter(parameter));
  }


  /**
   * @param name 过滤内容
   * @return
   * @since 请求头过滤
   */
  @Override
  public String getHeader(String name) {
    return filterParamString(super.getHeader(name));
  }


  /**
   * @return
   * @since Cookie内容过滤
   */
  @Override
  public Cookie[] getCookies() {
    Cookie[] existingCookies = super.getCookies();
    if (existingCookies != null) {
      for (int i = 0; i < existingCookies.length; ++i) {
        Cookie cookie = existingCookies[i];
        cookie.setValue(filterParamString(cookie.getValue()));
      }
    }
    return existingCookies;
  }

  /**
   * @param rawValue 待处理内容
   * @return
   * @since 过滤字符串内容
   */
  protected String filterParamString(String rawValue) {
    if (rawValue == null) {
      return null;
    }
    String tmpStr = rawValue;
    if (this.filterXSS) {
      tmpStr = WafHelper.stripXSS(rawValue);
    }
    if (this.filterSQL) {
      tmpStr = WafHelper.stripSqlInjection(tmpStr);
    }
    return tmpStr;
  }
}
