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
package com.baomidou.kisso.common.signature;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;

import com.baomidou.kisso.exception.UnsupportedAlgorithmException;

/**
 * <p>
 * 非对称签名
 * </p>
 *
 * @author Wujun
 * @since 2019-04-08
 */
public class SignAsymmetric implements ISign {

    private final PrivateKey key;

    public SignAsymmetric(final PrivateKey key) {
        this.key = key;
    }

    @Override
    public byte[] sign(Provider provider, ShaAlgorithm shaAlgorithm, byte[] signingStringBytes) {
        try {
            final java.security.Signature instance = provider == null ?
                    java.security.Signature.getInstance(shaAlgorithm.getJmvName()) :
                    java.security.Signature.getInstance(shaAlgorithm.getJmvName(), provider);
            instance.initSign(key);
            instance.update(signingStringBytes);
            return instance.sign();
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedAlgorithmException(shaAlgorithm.getJmvName());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
