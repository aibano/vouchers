package com.sejelli.voucher.domain.application;

import com.sejelli.voucher.domain.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by aibano on 9/19/2016.
 */
public interface VoucherApp {
    Page<Voucher> getVouchersByBucketId(Pageable pageable, long bucketId, long organizationId);

    Voucher consumeVoucher(String serialNumber, long consumedBy, long organizationId);
}
