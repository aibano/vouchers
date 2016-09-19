package com.sejelli.voucher.interfaces.Independent;

import com.sejelli.voucher.domain.model.Category;

/**
 * Created by aibano on 9/11/2016.
 */
public class CategoryPres {

    private String name;
    private long organizationId;
    private long id;

    public CategoryPres() {

    }

    public CategoryPres(Category category){
        this.name = category.getName();
        this.id = category.getId();
        this.organizationId = category.getOrganizationId();
    }

    public CategoryPres(String name, long id, long organizationId){
        this.name = name;
        this.id = id;
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
