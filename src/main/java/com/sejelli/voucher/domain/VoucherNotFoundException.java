package com.sejelli.voucher.domain;

/**
 * Created by aibano on 9/19/2016.
 */
public class VoucherNotFoundException extends RuntimeException{
    public VoucherNotFoundException(String message){
        super(message);
    }
}
