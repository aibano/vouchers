package com.sejelli.voucher.interfaces.Independent;

import com.sejelli.voucher.domain.application.VoucherApp;
import com.sejelli.voucher.interfaces.InterfaceProperties;
import com.sejelli.voucher.interfaces.Presentation.VoucherPres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by aibano on 9/10/2016.
 * This controller is used when the service is consumed by single app
 */
@RestController
@RequestMapping(value = "vouchers")
public class IndependentVoucherController {

    @Autowired
    InterfaceProperties interfaceProperties;

    @Autowired
    RequestValidation requestValidation;

    @Autowired
    VoucherApp voucherApp;

    @RequestMapping(method = RequestMethod.PUT, value = "/consume")
    public @ResponseBody
    VoucherPres consumeVoucher(@RequestHeader(value = "app-token") String token,
                               @RequestBody VoucherPres voucherPres){
        this.requestValidation.validateRequest(token);

        return new VoucherPres(this.voucherApp.consumeVoucher(voucherPres.serial,
                voucherPres.consumedBy,
                this.interfaceProperties.getOrganizationId()
                ));
    }
}
