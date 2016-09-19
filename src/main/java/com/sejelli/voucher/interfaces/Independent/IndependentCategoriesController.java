package com.sejelli.voucher.interfaces.Independent;

import com.amazonaws.services.route53.model.InvalidArgumentException;
import com.amazonaws.services.securitytoken.model.InvalidIdentityTokenException;
import com.sejelli.voucher.domain.application.CategoryApp;
import com.sejelli.voucher.domain.model.Category;
import com.sejelli.voucher.domain.model.Voucher;
import com.sejelli.voucher.interfaces.InterfaceProperties;
import com.sejelli.voucher.interfaces.Mode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aibano on 9/10/2016.
 */
@RestController
@RequestMapping("voucher/categories")
public class IndependentCategoriesController {

    @Autowired
    InterfaceProperties interfaceProperties;

    @Autowired
    CategoryApp categoryApp;

    @Autowired
    RequestValidation requestValidation;

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestHeader(value="app-token") String token, @RequestBody CategoryPres category){
        this.requestValidation.validateRequest(token);

        categoryApp.create(category.getName(), this.interfaceProperties.getOrganizationId());
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<CategoryPres> listAllByPage(@RequestHeader(value="app-token") String token, Pageable pageable){
        this.requestValidation.validateRequest(token);
        ArrayList<CategoryPres> categoriesPres = new ArrayList<>();
        Page<Category> categories = categoryApp.listByPage(pageable, this.interfaceProperties.getOrganizationId());
        for (Category cat :
                categories) {
            categoriesPres.add(new CategoryPres(
                    cat.getName(),
                    cat.getId(),
                    cat.getOrganizationId()
            ));
        }

        return new PageImpl<CategoryPres>(categoriesPres, pageable, categories.getTotalElements());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{categoryId}")
    public @ResponseBody CategoryPres getCategory(@RequestHeader(value="app-token") String token,
                                    @PathVariable(value = "categoryId") long categoryId)
    {
        this.requestValidation.validateRequest(token);
        return new CategoryPres(this.categoryApp.get(categoryId, this.interfaceProperties.getOrganizationId()));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody CategoryPres updateCategoryName(@RequestHeader(value = "app-token") String token,
                                                         @RequestBody CategoryPres categoryPres){

        this.requestValidation.validateRequest(token);
        return new CategoryPres(
                this.categoryApp.updateName(categoryPres.getId(),
                categoryPres.getName(),
                this.interfaceProperties.getOrganizationId()
        ));
    }




}
