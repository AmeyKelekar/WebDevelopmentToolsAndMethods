package com.amey.spring.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper{
    public RequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }
 
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values==null){
            return null;
        }
        int count = values.length;
        String[] sanitizedValues = new String[count];
        for (int i = 0; i < count; i++) {
            sanitizedValues[i] = sanitizeInput(values[i]);
         }
        return sanitizedValues;
    }
 
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return sanitizeInput(value);
    }
 
    private String sanitizeInput(String value) {
        value = value.replaceAll(" ", "_"); //Replacing white space with _
        //remove HTML tags
        value = value.replaceAll("<[^>]*>", "");
        //remove script tag
        value = value.replaceAll("script", "");
        //remove link
        value = value.replaceAll("www.", "");
        value = value.replaceAll(".com", "");
        //Remove eval
        value = value.replaceAll("eval\\\\((.*)\\\\)", "");
        //Remove JavaScript 
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        //Remove document tag
        value = value.replaceAll("document", "");
        //Remove .write tage
        value = value.replaceAll(".write", "");
        //Replace _ with space
        value = value.replaceAll("_"," ");
        return value;
    }
}