package com.viSmart.viSmart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Groups {

    private @Id @GeneratedValue int id;
    private String name;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return this.id;
    }

    public String getName(){return this.name;}

    public Date getCreated_at(){return this.created_at;}

    public Date getUpdated_at(){return this.updated_at;}
}