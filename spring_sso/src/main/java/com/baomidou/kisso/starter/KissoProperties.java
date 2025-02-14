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
package com.baomidou.kisso.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.baomidou.kisso.SSOConfig;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * <p>
 * Spring boot starter 启动配置
 * </p>
 *
 * @author Wujun
 * @since 2017-07-18
 */
@ConfigurationProperties(prefix = "kisso")
public class KissoProperties {

    @NestedConfigurationProperty
    private SSOConfig config;

    public SSOConfig getConfig() {
        return config;
    }

    public void setConfig(SSOConfig config) {
        this.config = config;
    }
}
