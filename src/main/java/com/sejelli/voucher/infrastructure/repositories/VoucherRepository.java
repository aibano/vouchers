package com.sejelli.voucher.infrastructure.repositories;

import com.sejelli.voucher.domain.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by aibano on 9/19/2016.
 */
public interface VoucherRepository extends PagingAndSortingRepository<Voucher, Long> {
    Page<Voucher> findByBucketId(Pageable pageable, long bucketId);
    List<Voucher> findByBucketId(long bucketId);

    List<Voucher> findBySerial(String serial);
}
