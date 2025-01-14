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
package com.alipay.sofa.service.shared;

import com.alipay.sofa.common.dal.dai.NewsManageDao;
import com.alipay.sofa.common.dal.dao.NewsDO;
import com.alipay.sofa.facade.NewsReadService;
import com.alipay.sofa.runtime.api.annotation.SofaReference;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Wujun
 * @since 2.5.0
 */
public class NewsReadServiceImpl implements NewsReadService<NewsDO> {

    @SofaReference
    private NewsManageDao newManageDao;

    public List<NewsDO> read(String author) throws SQLException {
        try {
            return newManageDao.query(author);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            throw ex;
        }
    }
}