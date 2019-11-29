package org.openpaas.paasta.marketplace.web.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.openpaas.paasta.marketplace.api.domain.CustomPage;
import org.openpaas.paasta.marketplace.api.domain.Software;
import org.openpaas.paasta.marketplace.api.domain.SoftwareSpecification;
import org.openpaas.paasta.marketplace.web.user.common.CommonService;
import org.openpaas.paasta.marketplace.web.user.service.SoftwareService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/softwares")
@RequiredArgsConstructor
public class SoftwareController {

    private final SoftwareService softwareService;
    private final CommonService commonService;

    @GetMapping
    @ResponseBody
    public CustomPage<Software> getSoftwareList(HttpServletRequest httpServletRequest){
        return softwareService.getSoftwareList(commonService.setParameters(httpServletRequest));
    }

    @GetMapping(value = "/page")
    public String getSoftwares(Model model, @AuthenticationPrincipal OAuth2User oauth2User, HttpSession httpSession, SoftwareSpecification spec, Authentication authentication) {
        model.addAttribute("categories", softwareService.getCategories());
        model.addAttribute("spec", new SoftwareSpecification());
        model.addAttribute("status", Software.Status.values());
        return "contents/software-list";
    }

    @GetMapping(value = "/{id}")
    public String getSoftware(Model model, @PathVariable Long id) {
        model.addAttribute("categories", softwareService.getCategories());
        model.addAttribute("software", softwareService.getSoftware(id));
        model.addAttribute("softwarePlanList", softwareService.getSoftwarePlanList(id));
        softwareService.getCategories();
        return "contents/software-detail";
    }

}
