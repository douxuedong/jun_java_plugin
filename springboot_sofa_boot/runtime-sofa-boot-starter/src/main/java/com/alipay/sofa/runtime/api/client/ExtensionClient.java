/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.runtime.api.client;

import com.alipay.sofa.runtime.api.client.param.ExtensionParam;
import com.alipay.sofa.runtime.api.client.param.ExtensionPointParam;

/**
 * Programming API for Extension/Extension-Point
 *
 * @author Wujun
 * @author Wujun
 * @since 2.6.0
 */
public interface ExtensionClient {

    /**
     * publish extension
     *
     * @param extensionParam extension param
     */
    void publishExtension(ExtensionParam extensionParam);

    /**
     * publish extension-point
     *
     * @param extensionPointParam extension point param
     */
    void publishExtensionPoint(ExtensionPointParam extensionPointParam);
}
