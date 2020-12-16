package com.viSmart.viSmart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Courses {

    private @Id @GeneratedValue Long id;
    private String name;
    private Integer is_live;
    private String available_for_groups;
    private Date created_at;
    private Date updated_at;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Integer getIs_live() {
        return is_live;
    }

    public String getAvailable_for_groups() {
        return available_for_groups;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIs_live(Integer is_live) {
        this.is_live = is_live;
    }

    public void setAvailable_for_groups(String available_for_groups) {
        this.available_for_groups = available_for_groups;
    }
}