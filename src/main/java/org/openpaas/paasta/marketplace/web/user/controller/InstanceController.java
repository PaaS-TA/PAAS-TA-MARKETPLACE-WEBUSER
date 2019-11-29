package org.openpaas.paasta.marketplace.web.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.openpaas.paasta.marketplace.api.domain.CustomPage;
import org.openpaas.paasta.marketplace.api.domain.Instance;
import org.openpaas.paasta.marketplace.api.domain.InstanceCart;
import org.openpaas.paasta.marketplace.api.domain.Software;
import org.openpaas.paasta.marketplace.api.domain.SoftwareSpecification;
import org.openpaas.paasta.marketplace.web.user.common.CommonService;
import org.openpaas.paasta.marketplace.web.user.service.InstanceCartService;
import org.openpaas.paasta.marketplace.web.user.service.InstanceService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/instances")
@RequiredArgsConstructor
public class InstanceController {

	private final InstanceService instanceService;
	private final InstanceCartService instanceCartService;
    private final CommonService commonService;

    @GetMapping
    @ResponseBody
    public CustomPage<Instance> getSoftwareList(HttpServletRequest httpServletRequest){
        return instanceService.getSoftwareList(commonService.setParameters(httpServletRequest));
    }

    @GetMapping(value = "/total")
    @ResponseBody
    public Map<String,Object> getMyTotalList(HttpServletRequest httpServletRequest
    										, ModelAndView modelAndView
								    		, @RequestParam("usageStartDate") String usageStartDate
								    		, @RequestParam("usageEndDate") String usageEndDate) {
    	Map<String,Object> result = new HashMap<String,Object>();
    	
    	// 구매상품 리스트 조회
    	CustomPage<Instance> instancePage = instanceService.getMyTotalList(commonService.setParameters(httpServletRequest));

    	// 장바구니 상품 조회
   		List<InstanceCart> instanceCartList = instanceCartService.getUserAllCartList(commonService.setParameters(httpServletRequest));
   		result.put("instanceCartList", instanceCartList);

    	// InstanceID List 생성
        List<Long> idIn = new ArrayList<>();
        for (Instance i:instanceService.getMyTotalList("").getContent()) {
            idIn.add(i.getId());
        }
        
        // 상품별 사용요금 계산 리스트 (기간한정)
        Map<String, String> pricePerInstanceList = instanceService.getPricePerInstanceList(idIn, usageStartDate, usageEndDate);
        result.put("pricePerInstanceList", pricePerInstanceList);
        
        // 상품별 사용요금 상품정보에 설정
        List<Instance> instanceList = instancePage.getContent();
        for (Instance instance : instanceList) {
    		String temp = pricePerInstanceList.get(""+instance.getId());
    		instance.setPricePerInstance((StringUtils.isEmpty(temp) ? 0 : Long.valueOf(temp)));
        }
        result.put("instanceList", instanceList);
        
        result.put("instancePage", instancePage);
    	return result;
    }

    @GetMapping(value = "/my/page")
    public String getMyPage(Model model, @AuthenticationPrincipal OAuth2User oauth2User, HttpSession httpSession, SoftwareSpecification spec, Authentication authentication) {
        model.addAttribute("categories", instanceService.getCategories());
        model.addAttribute("spec", new SoftwareSpecification());
        model.addAttribute("status", Software.Status.values());
        return "contents/instance-list";
    }

    @GetMapping(value = "/{id}")
    public String getInstance(Model model, @PathVariable Long id) {
        model.addAttribute("categories", instanceService.getCategories());
        model.addAttribute("instance", instanceService.getInstance(id));
        return "contents/instance-detail";
    }


    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public Instance updateToDeleted(@PathVariable Long id) {
        instanceService.updateToDeleted(id);
        return instanceService.getInstance(id);
    }

    @PostMapping(value = "/provisionTest")
    public Software provision(@RequestBody Software software) {
        return instanceService.provision(software);
    }

    @PostMapping
    @ResponseBody
    public Instance createInstance(@RequestBody Instance instance) {
        return instanceService.createInstance(instance);
    }

}