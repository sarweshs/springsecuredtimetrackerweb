package com.ibm.timetrackerweb.view.resolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
 

public class JsonViewResolver implements ViewResolver {
 
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.ViewResolver#resolveViewName(java.lang
     * .String, java.util.Locale)
     */
    public View resolveViewName(String viewName, Locale locale)
            throws Exception {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }
 
}