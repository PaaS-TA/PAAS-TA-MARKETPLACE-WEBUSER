package org.openpaas.paasta.marketplace.web.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.api.domain.*;
import org.openpaas.paasta.marketplace.web.user.common.CommonService;
import org.openpaas.paasta.marketplace.web.user.service.InstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(InstanceController.class);

    @GetMapping
    @ResponseBody
    public CustomPage<Software> getInstanceList(HttpServletRequest httpServletRequest){
        return instanceService.getInstanceList(commonService.setParameters(httpServletRequest));
    }


    //TODO : 내 상품조회
    @GetMapping(value = "/my/page")
    public String getMyPage(Model model, @AuthenticationPrincipal OAuth2User oauth2User, HttpSession httpSession, SoftwareSpecification spec, Authentication authentication) {
//        httpSession.setAttribute("yourName", oauth2User.getAttributes().get("user_name"));
        model.addAttribute("categories", instanceService.getCategories());
        model.addAttribute("spec", new SoftwareSpecification());
        model.addAttribute("status", Software.Status.values());
        return "contents/instanceSoftware";
    }


    @GetMapping(value = "/{id}")
    public String getSoftware(Model model, @PathVariable Long id) {
        model.addAttribute("categories", instanceService.getCategories());
        model.addAttribute("software", instanceService.getSoftware(id));
        instanceService.getCategories();
        return "contents/software-detail";
    }


    @GetMapping(value = "/create")
    public String createSoftwareHtml(Model model, HttpSession httpSession, @ModelAttribute Software software) {
        model.addAttribute("types", Software.Type.values());
        model.addAttribute("yns", Yn.values());
        model.addAttribute("categories", instanceService.getCategories());
        return "contents/software-create";
    }

}




