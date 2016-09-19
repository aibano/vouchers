package com.sejelli.voucher.domain.application;

import com.sejelli.voucher.domain.VoucherNotFoundException;
import com.sejelli.voucher.domain.model.Status;
import com.sejelli.voucher.domain.model.Voucher;
import com.sejelli.voucher.infrastructure.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by aibano on 9/19/2016.
 */
public class VoucherAppImpl implements VoucherApp{
    @Autowired
    VoucherRepository voucherRepository;

    @Override
    public Page<Voucher> getVouchersByBucketId(Pageable pageable, long bucketId, long organizationId) {
        return this.voucherRepository.findByBucketId(pageable, bucketId);
    }

    @Override
    public Voucher consumeVoucher(String serialNumber, long consumedBy, long organizationId) {
        List<Voucher> result = this.voucherRepository.findBySerial(serialNumber);
        if(result.size() != 1)
            throw new VoucherNotFoundException("Voucher serial number is wrong");
        Voucher selectedVouher = result.get(0);
        if(selectedVouher.getStatus() != Status.ACTIVE)
            throw new VoucherNotFoundException("Voucher serial number is wrong");

        selectedVouher.consumeVoucher(consumedBy);

        this.voucherRepository.save(selectedVouher);

        return selectedVouher;
    }
}
