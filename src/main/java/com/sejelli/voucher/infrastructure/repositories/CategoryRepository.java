package com.sejelli.voucher.infrastructure.repositories;

import com.sejelli.voucher.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by aibano on 9/10/2016.
 */
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    Page<Category> findByOrganizationId(Pageable pageable, long organizationId);
}
