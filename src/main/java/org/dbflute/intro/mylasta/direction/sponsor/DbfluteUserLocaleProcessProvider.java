/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.dbflute.intro.mylasta.direction.sponsor;

import java.util.Locale;

import org.dbflute.lastaflute.web.callback.ActionRuntimeMeta;
import org.dbflute.lastaflute.web.servlet.request.RequestManager;
import org.dbflute.lastaflute.web.servlet.request.UserLocaleProcessProvider;

/**
 * @author jflute
 */
public class DbfluteUserLocaleProcessProvider implements UserLocaleProcessProvider {

    public static final Locale centralLocale = Locale.getDefault(); // you can change it if you like

    @Override
    public boolean isAcceptCookieLocale() {
        return false;
    }

    @Override
    public Locale findBusinessLocale(ActionRuntimeMeta executeMeta, RequestManager requestManager) {
        return centralLocale;
    }

    @Override
    public Locale getRequestedLocale(RequestManager requestManager) {
        return null; // null means browser default
    }

    @Override
    public Locale getFallbackLocale() {
        return centralLocale;
    }

    @Override
    public String toString() {
        return "{acceptCookieLocale=" + isAcceptCookieLocale() + ", fallbackLocale=" + getFallbackLocale() + "}";
    }
}