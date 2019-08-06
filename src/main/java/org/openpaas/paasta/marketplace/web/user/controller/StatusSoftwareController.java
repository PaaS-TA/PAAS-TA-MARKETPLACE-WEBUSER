package org.openpaas.paasta.marketplace.web.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.web.user.service.StatusSoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/status/software")
@Slf4j
@RequiredArgsConstructor
public class StatusSoftwareController {

    @Autowired
    public StatusSoftwareService statusSoftwareService;

    /**
     * 사용상품 메인페이지로 이동한다.
     *
     */
    @GetMapping
    public String getCategoryMain() {
        return "contents/useStatusSoftware";
    }
}




