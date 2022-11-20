package com.example.studydemo.ui.recyclerview.beans;

public class BaseAdapterBean {
    private String type;
    private DelegateNo1Bean delegateNo1Bean;
    private DelegateNo2Bean delegateNo2Bean;

    public BaseAdapterBean(DelegateNo1Bean delegateNo1Bean, DelegateNo2Bean delegateNo2Bean) {
        this.delegateNo1Bean = delegateNo1Bean;
        this.delegateNo2Bean = delegateNo2Bean;
    }

    public BaseAdapterBean() {
    }

    public DelegateNo1Bean getDelegateNo1Bean() {
        return delegateNo1Bean;
    }

    public void setDelegateNo1Bean(DelegateNo1Bean delegateNo1Bean) {
        this.delegateNo1Bean = delegateNo1Bean;
    }

    public DelegateNo2Bean getDelegateNo2Bean() {
        return delegateNo2Bean;
    }

    public void setDelegateNo2Bean(DelegateNo2Bean delegateNo2Bean) {
        this.delegateNo2Bean = delegateNo2Bean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
