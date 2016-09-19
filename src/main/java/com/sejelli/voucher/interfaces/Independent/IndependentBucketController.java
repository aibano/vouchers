package com.sejelli.voucher.interfaces.Independent;

import com.netflix.governator.annotations.binding.Request;
import com.sejelli.voucher.domain.application.BucketApp;
import com.sejelli.voucher.domain.model.Bucket;
import com.sejelli.voucher.domain.model.Voucher;
import com.sejelli.voucher.interfaces.InterfaceProperties;
import com.sejelli.voucher.interfaces.Presentation.VoucherPres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by aibano on 9/19/2016.
 */
@RestController
@RequestMapping("/buckets")
public class IndependentBucketController {

    @Autowired
    BucketApp bucketApp;

    @Autowired
    InterfaceProperties interfaceProperties;

    @Autowired
    RequestValidation requestValidation;

    @RequestMapping(method = RequestMethod.POST)
    public BucketPres add(@RequestHeader(value="app-token") String token, @RequestBody BucketPres bucketPres){
        this.requestValidation.validateRequest(token);

        return new BucketPres(
                this.bucketApp.create(
                        bucketPres.categoryId,
                        bucketPres.value,
                        bucketPres.createdBy,
                        bucketPres.count,
                        this.interfaceProperties.getOrganizationId()
                ));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<BucketPres> get(@RequestHeader(value="app-token") String token, Pageable pageable){
        this.requestValidation.validateRequest(token);

        ArrayList<BucketPres> bucketsPres = new ArrayList<>();
        Page<Bucket> buckets = this.bucketApp.listByPage(pageable, this.interfaceProperties.getOrganizationId());
        for (Bucket item :
                buckets) {
            bucketsPres.add(new BucketPres(item)
            );
        }

        return new PageImpl<BucketPres>(bucketsPres, pageable, buckets.getTotalElements());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{bucketId}/activate")
    public BucketPres activate(@RequestHeader(value="app-token") String token,
                               @PathVariable(value = "bucketId") long bucketId){
        this.requestValidation.validateRequest(token);
        return new BucketPres(
                this.bucketApp.activate(bucketId, this.interfaceProperties.getOrganizationId())
        );
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{categoryId}/vouchers")
    public @ResponseBody Page<VoucherPres> getVouchers(@RequestHeader(value="app-token") String token,
                                                   @PathVariable(value = "categoryId") long categoryId,
                                                   Pageable pageable)
    {
        this.requestValidation.validateRequest(token);


        ArrayList<VoucherPres> vouchersPres = new ArrayList<>();
        Page<Voucher> vouchers = this.bucketApp.listVouchers(pageable, categoryId, this.interfaceProperties.getOrganizationId());
        for (Voucher item :
                vouchers) {
            vouchersPres.add(new VoucherPres(item)
            );
        }

        return new PageImpl<VoucherPres>(vouchersPres, pageable, vouchers.getTotalElements());
    }
}
