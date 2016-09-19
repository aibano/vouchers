package com.sejelli.voucher.interfaces.Presentation;

import com.sejelli.voucher.domain.model.Voucher;
import com.sejelli.voucher.interfaces.Independent.StatusPres;

import java.util.Date;

/**
 * Created by aibano on 9/19/2016.
 */
public class VoucherPres {
    public long id;
    public String serial;
    public long organizationId;
    public long categoryId;
    public StatusPres status;
    public long createdBy;
    public Date created;
    public long consumedBy;
    public Date consumed;
    public long value;


    public VoucherPres(){
    }

    public VoucherPres(Voucher voucher){
        this.id = voucher.getId();
        this.serial = voucher.getSerial();
        this.organizationId = voucher.getOrganizationId();
        this.categoryId = voucher.getCategoryId();
        this.status = Enum.valueOf(StatusPres.class, voucher.getStatus().name());
        this.createdBy = voucher.getCreatedBy();
        this.created = voucher.getCreated();
        this.consumedBy = voucher.getConsumedBy();
        this.consumed = voucher.getConsumed();
        this.value = voucher.getValue();
    }
}
