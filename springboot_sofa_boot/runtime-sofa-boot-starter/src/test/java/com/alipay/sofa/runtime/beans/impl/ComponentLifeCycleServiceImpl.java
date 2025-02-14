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

import com.alipay.sofa.runtime.api.ServiceRuntimeException;
import com.alipay.sofa.runtime.api.component.ComponentLifeCycle;
import com.alipay.sofa.runtime.beans.service.LifeCycleService;

/**
 *
 * @author Wujun
 * @since 2.6.0
 */
public class ComponentLifeCycleServiceImpl implements LifeCycleService, ComponentLifeCycle {

    private boolean activated;
    private boolean deactivated;

    @Override
    public void activate() throws ServiceRuntimeException {
        activated = true;
    }

    @Override
    public void deactivate() throws ServiceRuntimeException {
        deactivated = true;
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public boolean isDeactivated() {
        return deactivated;
    }
}
