package org.openpaas.paasta.marketplace.web.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.api.domain.*;
import org.openpaas.paasta.marketplace.web.user.common.CommonService;
import org.openpaas.paasta.marketplace.web.user.service.InstanceService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/instances")
@Slf4j
@RequiredArgsConstructor
public class InstanceController {

    public final InstanceService instanceService;
    private final CommonService commonService;

    @GetMapping
    @ResponseBody
    public CustomPage<Instance> getSoftwareList(HttpServletRequest httpServletRequest){
        return instanceService.getSoftwareList(commonService.setParameters(httpServletRequest));
    }

    @GetMapping(value = "/total")
    @ResponseBody
    public CustomPage<Instance> getMyTotalList(HttpServletRequest httpServletRequest){
        return instanceService.getMyTotalList(commonService.setParameters(httpServletRequest));
    }

    @GetMapping(value = "/my/page")
    public String getMyPage(Model model, @AuthenticationPrincipal OAuth2User oauth2User, HttpSession httpSession, SoftwareSpecification spec, Authentication authentication) {
//        httpSession.setAttribute("yourName", oauth2User.getAttributes().get("user_name"));
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




