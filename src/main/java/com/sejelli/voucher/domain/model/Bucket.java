package com.sejelli.voucher.domain.model;

import org.springframework.data.annotation.*;
import org.springframework.data.annotation.AccessType;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.*;

/**
 * Created by aibano on 9/9/2016.
 */
@Entity
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @org.springframework.data.annotation.AccessType(value = AccessType.Type.PROPERTY)
    private long id;
    private long categoryId;
    private long organizationId;
    private Status status;
    private long createdBy;
    private long value;
    private int count;
    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.DETACH,
    mappedBy = "bucket")
    private List<Voucher> vouchers;

    public Bucket(){}

    public Bucket(long id,
                  long categoryId,
                  long organizationId,
                  Status status,
                  long value,
                  long createdBy,
                  int count){
        this.id = id;
        this.categoryId = categoryId;
        this.organizationId = organizationId;
        this.status = status;
        this.value = value;
        this.createdBy = createdBy;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public Status getStatus() {
        return status;
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public Bucket createRandomVouchers(){
        List<String> voucherStrings = generatePatchVoucherNumbers(1000000, this.id);
        ArrayList<Voucher> vouchers = new ArrayList<>(this.getCount());
        for (int i = 0; i < this.getCount(); i++){
            vouchers.add(
                    new Voucher(-1,
                            voucherStrings.get(i),
                            this.id,
                            categoryId,
                            Status.NOT_ACTIVE,
                            this.getCreatedBy(),
                            Calendar.getInstance().getTime(),
                            -1,
                            null,
                            this.getValue(),
                            this
                    )
            );
        }

        this.vouchers = vouchers;

        return this;
    }

    private ArrayList<String> generatePatchVoucherNumbers(int count, long bucketId){
        ArrayList<String> retVal = new ArrayList<>(count);
        int digitsLength = new Integer(count).toString().length();
        for (int i = 0; i < count; i++){
            retVal.add(formatVoucherNumber(bucketId, i, digitsLength));
        }

        Collections.shuffle(retVal, new Random(Double.doubleToLongBits(Math.random())));

        return retVal;
    }

    private String formatVoucherNumber(long bucketId, int counter, int digitsLength){
        Calendar calendar = Calendar.getInstance();
        String retVal = String.format(
                "%s-%04d-%s%03d%0" + digitsLength + "d",
                this.organizationId,
                bucketId,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.DAY_OF_YEAR),
                counter

        );

        return retVal;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public long getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    public Bucket activateBucket() {
        this.status = Status.ACTIVE;
        for (Voucher item :
                this.vouchers) {
            if (item.getStatus() == Status.NOT_ACTIVE)
                item.activate();
        }
        return this;
    }
}
