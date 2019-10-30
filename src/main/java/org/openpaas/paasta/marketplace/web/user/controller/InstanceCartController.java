package org.openpaas.paasta.marketplace.web.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.openpaas.paasta.marketplace.api.domain.InstanceCart;
import org.openpaas.paasta.marketplace.api.domain.InstanceCartSpecification;
import org.openpaas.paasta.marketplace.web.user.common.CommonService;
import org.openpaas.paasta.marketplace.web.user.service.InstanceCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/instances/cart")
@Slf4j
@RequiredArgsConstructor
public class InstanceCartController {

	private final InstanceCartService instanceCartService;
	private final CommonService commonService;
	
    @PostMapping
    @ResponseBody
    public InstanceCart createInstance(@RequestBody InstanceCart instanceCart) {
        return instanceCartService.createInstanceCart(instanceCart);
    }
    
    @GetMapping(value = "/allList")
    @ResponseBody
    public List<InstanceCart> getAllList(HttpServletRequest httpServletRequest) {
   		return instanceCartService.getAllList(commonService.setParameters(httpServletRequest));
    }
    
    @DeleteMapping(value = "/allDelete")
    @ResponseBody
    public Integer allDelete(HttpServletRequest httpServletRequest) {
        return instanceCartService.allDelete(commonService.setParameters(httpServletRequest));
    }
    
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public Integer delete(@RequestBody InstanceCartSpecification instanceCartSpecification) {
    	log.debug(">>>>>>>>>>>> instanceCartSpecification: {}", instanceCartSpecification.toString());
    	return instanceCartService.delete(instanceCartSpecification);
    }
}