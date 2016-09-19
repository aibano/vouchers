package com.sejelli.voucher.domain.application;

import com.sejelli.voucher.domain.model.Category;
import com.sejelli.voucher.infrastructure.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by aibano on 9/10/2016.
 */
public class CategoryAppImpl implements CategoryApp {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(String name, long requesterOrgId) {
        return categoryRepository.save(new Category(-1, name, requesterOrgId));
    }

    @Override
    public void delete(long categoryId, long requesterOrgId) {

    }

    @Override
    public Category get(long categoryId, long requesterOrgId) {
        Category category = this.categoryRepository.findOne(categoryId);
        return category;
    }

    @Override
    public Category updateName(long categoryId, String name, long requesterOrgId) {
        Category cat = this.categoryRepository.findOne(categoryId);
        cat.updateName(name);
        this.categoryRepository.save(cat);
        return cat;
    }

    @Override
    public Page<Category> listByPage(Pageable pageable, long requesterOrgId) {
        return this.categoryRepository.findByOrganizationId(pageable, requesterOrgId);
    }
}
