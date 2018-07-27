package com.yang.code.util.lock;

/**
 * Created by Administrator on 2018/7/27.
 */
public class LockDTO {
    private String name;
    private String value;

    public LockDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
