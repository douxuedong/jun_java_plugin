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
package com.alipay.sofa.runtime.beans.impl;

import com.alipay.sofa.runtime.beans.service.SampleService;

/**
 * @author Wujun
 * @since 3.1.0
 */
public class ParameterAnnotationSampleService implements SampleService {
    private SampleService service1;
    private SampleService service2;
    private SampleService service3;

    public ParameterAnnotationSampleService(SampleService service1, SampleService service2,
                                            SampleService service3) {
        this.service1 = service1;
        this.service2 = service2;
        this.service3 = service3;
    }

    @Override
    public String service() {
        return service1.service() + "@" + service2.service() + "@" + service3.service();
    }
}