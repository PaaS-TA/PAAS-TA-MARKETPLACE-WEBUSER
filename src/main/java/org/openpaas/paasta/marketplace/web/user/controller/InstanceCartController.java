package org.openpaas.paasta.marketplace.web.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.openpaas.paasta.marketplace.api.domain.Instance;
import org.openpaas.paasta.marketplace.api.domain.InstanceCart;
import org.openpaas.paasta.marketplace.api.domain.InstanceCartSpecification;
import org.openpaas.paasta.marketplace.api.domain.Software;
import org.openpaas.paasta.marketplace.web.user.common.CommonService;
import org.openpaas.paasta.marketplace.web.user.service.InstanceCartService;
import org.openpaas.paasta.marketplace.web.user.service.InstanceService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private final InstanceService instanceService;
	private final CommonService commonService;
	
    @PostMapping
    @ResponseBody
    public InstanceCart createInstanceCart(@RequestBody InstanceCart instanceCart) {
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
    	return instanceCartService.delete(instanceCartSpecification);
    }
    
    @GetMapping(value = "/page")
    public String cartPage(Model model, @AuthenticationPrincipal OAuth2User oauth2User, HttpSession httpSession) {
        model.addAttribute("categories", instanceService.getCategories());
        return "contents/instanceCart-list";
    }
    
    @GetMapping(value = "/page/list")
    @ResponseBody
    public Map<String,Object> getPageList(HttpServletRequest httpServletRequest) throws Exception {
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	// 카트리스트
    	resultMap.put("cartList", instanceCartService.getPageList(commonService.setParameters(httpServletRequest)));
   		return resultMap;
    }
    
    @PostMapping(value="/purchaseCart")
    @ResponseBody
    public Instance purchaseCart(@RequestBody InstanceCart instanceCart) {
    	// 상품구매
    	Instance instance = new Instance();
    	Software software = new Software();
    	software.setId(instanceCart.getSoftware().getId());
    	instance.setSoftware(software);
    	instance.setSoftwarePlanId(instanceCart.getSoftwarePlanId());
    	instance.setProvisionTryCount(instanceCart.getProvisionTryCount());
    	instance.setDeprovisionTryCount(instanceCart.getDeprovisionTryCount());
    	Instance result = instanceService.createInstance(instance);
    	
    	// 장바구니 상품 삭제
    	InstanceCartSpecification spec = new InstanceCartSpecification();
    	List<Long> inInstanceCartIdList = new ArrayList<Long>();
    	spec.setInInstanceCartId(inInstanceCartIdList);
    	inInstanceCartIdList.add(instanceCart.getId());
    	instanceCartService.delete(spec);
    	
    	return result;
    }

}