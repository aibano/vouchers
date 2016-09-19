package com.sejelli.voucher.domain.application;

import com.sejelli.voucher.domain.model.Bucket;
import com.sejelli.voucher.domain.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by aibano on 9/19/2016.
 */
public interface BucketApp {

    Bucket create(
            long categoryId,
            long value,
            long createdBy,
            int count,
            long requesterOrgId
    );

    Bucket get(long id, long requesterOrgId);

    Bucket activate(long id, long requesterOrgId);

    Bucket deactivate(long id, long requesterOrgId);

    Page<Bucket> listByPage(Pageable pageable, long requesterOrgId);

    Page<Voucher> listVouchers(Pageable pageable, long id, long requesterOrgId);
}
