package com.sejelli.voucher.infrastructure.repositories;

import com.sejelli.voucher.domain.model.Bucket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by aibano on 9/19/2016.
 */
public interface BucketRepository extends PagingAndSortingRepository<Bucket, Long> {
    Page<Bucket> findByOrganizationId(Pageable pageable, long organizationId);
}
