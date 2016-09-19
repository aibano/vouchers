package com.sejelli.voucher.domain.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by aibano on 9/9/2016.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private long organizationId;

    /**
     * This constructor is mean for Hibernate
     */
    private Category(){}

    public Category(long id,
                    String name,
                    long organizationId){
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public Category updateName(String newName){
        this.name = newName;
        return this;
    }
}
