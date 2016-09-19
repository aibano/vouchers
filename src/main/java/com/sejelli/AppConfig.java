package com.sejelli;

import com.sejelli.voucher.domain.application.*;
import com.sejelli.voucher.domain.model.Voucher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aibano on 9/10/2016.
 */
@Configuration
public class AppConfig {

    @Bean
    CategoryApp categoryApp(){
        return new CategoryAppImpl();
    }

    @Bean
    BucketApp bucketApp(){
        return new BucketAppImpl();
    }

    @Bean
    VoucherApp voucherApp(){
        return new VoucherAppImpl();
    }
}
