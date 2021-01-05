package com.second.mall.modules.common.entity;

/**
 * @ClassName ResultEntity
 * @Author icy
 * @Data 2020/12/29 15:06
 * @Version v1.0
 **/
public class ResultEntity<T> {
    private int state;
    private String message;
    private T object;

    public ResultEntity() {
    }

    public ResultEntity(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public ResultEntity(int state, String message, T object) {
        this.state = state;
        this.message = message;
        this.object = object;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public enum ResultStatus {
        SUCCESS(200), FAILED(500);

        public int status;

        private ResultStatus(int status) {
            this.status = status;
        }
    }
}
