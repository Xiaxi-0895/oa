package org.example.imooc_oa.Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseUtils {
    private String code;
    private String msg;
    private Map data=new LinkedHashMap<>();

    public ResponseUtils(String code, String msg, Map data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseUtils(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseUtils() {
        this.code="0";
        this.msg="success";
    }

    public ResponseUtils put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
