package com.sejelli.voucher.domain.model;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by aibano on 9/9/2016.
 */
public class Organization {
    private long id;


    public Organization(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Bucket createNewBucket(
            long categoryId,
            long value,
            long createdBy,
            long bucketId,
            int count
    ){

        return new Bucket(bucketId, categoryId, this.id, Status.NOT_ACTIVE, value, createdBy, count);
    }




}
