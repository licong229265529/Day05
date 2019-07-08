package com.example.day05.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TitleBean {

    @Id
    @Property(nameInDb ="llid")
    private Long id;
    private String _id;
    private String url;

    @Generated(hash = 496279608)
    public TitleBean(Long id, String _id, String url) {
        this.id = id;
        this._id = _id;
        this.url = url;
    }

    @Generated(hash = 970439350)
    public TitleBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
