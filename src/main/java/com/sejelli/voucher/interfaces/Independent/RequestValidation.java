package com.sejelli.voucher.interfaces.Independent;

import com.amazonaws.services.route53.model.InvalidArgumentException;
import com.amazonaws.services.securitytoken.model.InvalidIdentityTokenException;
import com.sejelli.voucher.interfaces.InterfaceProperties;
import com.sejelli.voucher.interfaces.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Created by aibano on 9/19/2016.
 */
@Component
public class RequestValidation {

    @Autowired
    InterfaceProperties interfaceProperties;

    void validateRequest(@RequestHeader(value = "app-token") String token) {
        if(!this.interfaceProperties.getToken().equals(token))
            throw new InvalidIdentityTokenException("Wrong Token");
        if(this.interfaceProperties.getMode() != Mode.INDEPENDENT)
            throw new InvalidArgumentException("Independent Mode is Disabled");
    }
}
