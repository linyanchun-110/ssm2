package com.hzit.ssm.resp;

import java.util.List;

public class UserQueryResp {
    private int code;
    private String msg;
    private List<UserQueryData> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UserQueryData> getData() {
        return data;
    }

    public void setData(List<UserQueryData> data) {
        this.data = data;
    }
}
