package com.sejelli.voucher.domain.application;

import com.sejelli.voucher.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by aibano on 9/10/2016.
 */
public interface CategoryApp {
    Category create(String name,
                            long requesterOrgId);
    void delete(long categoryId,
                         long requesterOrgId);

    Category get(long categoryId,
                         long requesterOrgId);

    Category updateName(long categoryId,
                        String name,
                        long requesterOrgId);

    Page<Category> listByPage(Pageable pageable, long requesterOrgId);
}
