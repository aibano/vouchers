package com.sejelli.voucher.interfaces.Independent;

import com.sejelli.voucher.domain.model.Bucket;
import com.sejelli.voucher.domain.model.Voucher;
import com.sejelli.voucher.interfaces.Presentation.VoucherPres;

import java.util.ArrayList;

/**
 * Created by aibano on 9/19/2016.
 */
public class BucketPres {
    public long id;
    public long categoryId;
    public long organizationId;
    public StatusPres status;
    public long createdBy;
    public long value;
    public int count;


    public BucketPres(){}

    public BucketPres(Bucket bucket){
        this.id = bucket.getId();
        this.categoryId = bucket.getCategoryId();
        this.organizationId = bucket.getOrganizationId();
        this.status = Enum.valueOf(StatusPres.class, bucket.getStatus().name());
        this.createdBy = bucket.getCreatedBy();
        this.value = bucket.getValue();
        this.count = bucket.getCount();

    }
}
