package com.momolela.config;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class MyServletConfig implements ServletConfig {

    private ServletContext servletContext;
    private String servletName;
    private Map _initParams;

    public MyServletConfig(String servletName, ServletContext servletContext) {
        this.servletName = servletName;
        this.servletContext = servletContext;
        this._initParams = new HashMap();
    }

    @Override
    public String getServletName() {
        return servletName;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public String getInitParameter(String name) {
        if (_initParams.containsKey(name)) {
            return (String) _initParams.get(name);
        }
        return null;
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        if (_initParams == null) {
            return Collections.enumeration(Collections.EMPTY_LIST);
        }
        return Collections.enumeration(_initParams.keySet());
    }

    public void setInitParams(String key, Object value) {
        _initParams.put(key, value);
    }
}
