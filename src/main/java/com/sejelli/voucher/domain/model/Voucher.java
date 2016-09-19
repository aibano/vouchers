package com.sejelli.voucher.domain.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aibano on 9/9/2016.
 */
@Entity
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String serial;
    private long organizationId;
    private long categoryId;
    private Status status;
    private long createdBy;
    private Date created;
    private long consumedBy;
    private Date consumed;
    private long value;

    @JoinColumn(name="bucket_id")
    @ManyToOne(fetch=FetchType.LAZY,
            cascade = CascadeType.DETACH)
    private Bucket bucket;

    private Voucher(){}

    public Voucher(
            long id,
            String serial,
            long organizationId,
            long categoryId,
            Status status,
            long createdBy,
            Date created,
            long consumedBy,
            Date consumed,
            long value,
            Bucket bucket
    ){
        this.id = id;
        this.serial = serial;
        this.organizationId = organizationId;
        this.categoryId = categoryId;
        this.status = status;
        this.createdBy = createdBy;
        this.created = created;
        this.consumedBy = consumedBy;
        this.consumed = consumed;
        this.value = value;
        this.bucket = bucket;
    }

    public long getId() {
        return id;
    }

    public String getSerial() {
        return serial;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public Status getStatus() {
        return status;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public Date getCreated() {
        return created;
    }

    public long getConsumedBy() {
        return consumedBy;
    }

    public Date getConsumed() {
        return consumed;
    }

    public long getValue() {
        return value;
    }

    private void setBucket(Bucket newBucket){
        if(this.getBucket() == null && newBucket == null)
            return;
        if(this.getBucket().getId() == newBucket.getId())
            return;
        this.bucket = newBucket;
    }

    public Voucher consumeVoucher(long consumedBy){
        this.consumedBy = consumedBy;
        this.consumed = Calendar.getInstance().getTime();
        this.status = Status.CONSUMED;
        return this;
    }

    public Voucher activate() {
        if(this.status == Status.CONSUMED)
            throw new IllegalStateException("Cannot Activate Consumed Voucher");
        this.status = Status.ACTIVE;
        return this;
    }



    public Bucket getBucket() {
        return bucket;
    }
}
