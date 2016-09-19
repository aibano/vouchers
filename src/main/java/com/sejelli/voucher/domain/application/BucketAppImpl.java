package com.sejelli.voucher.domain.application;

import com.netflix.discovery.converters.Auto;
import com.sejelli.voucher.domain.model.Bucket;
import com.sejelli.voucher.domain.model.Organization;
import com.sejelli.voucher.domain.model.Status;
import com.sejelli.voucher.domain.model.Voucher;
import com.sejelli.voucher.infrastructure.repositories.BucketRepository;
import com.sejelli.voucher.infrastructure.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by aibano on 9/19/2016.
 */
public class BucketAppImpl implements BucketApp {
    @Autowired
    BucketRepository bucketRepository;

    @Autowired
    VoucherRepository voucherRepository;

    @Override
    @Transactional
    public Bucket create(long categoryId, long value, long createdBy, int count, long requesterOrgId) {
        Organization org = new Organization(requesterOrgId);
        Bucket newBucket = org.createNewBucket(categoryId, value, createdBy, -1, count);
        Bucket savedBucket = this.bucketRepository.save(newBucket);
        savedBucket.createRandomVouchers();

        this.voucherRepository.save(savedBucket.getVouchers());
        return savedBucket;
    }

    @Override
    public Bucket get(long id, long requesterOrgId) {
        return null;
    }

    @Override
    @Transactional
    public Bucket activate(long id, long requesterOrgId) {

        // Activate the bucket
        Bucket selectedBucket = this.bucketRepository.findOne(id);
        selectedBucket.activateBucket();
        Bucket updatedBucket = this.bucketRepository.save(selectedBucket);

        // Activate the associated vouchers
        this.voucherRepository.save(selectedBucket.getVouchers());

        return updatedBucket;
    }

    @Override
    public Bucket deactivate(long id, long requesterOrgId) {
        return null;
    }

    @Override
    public Page<Bucket> listByPage(Pageable pageable, long requesterOrgId) {
        return this.bucketRepository.findByOrganizationId(pageable, requesterOrgId);
    }

    @Override
    public Page<Voucher> listVouchers(Pageable pageable, long id, long requesterOrgId) {
        return this.voucherRepository.findByBucketId(pageable, id);
    }
}
