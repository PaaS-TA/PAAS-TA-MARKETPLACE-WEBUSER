package org.openpaas.paasta.marketplace.web.user.controller;

import lombok.RequiredArgsConstructor;
import org.openpaas.paasta.marketplace.api.domain.SoftwareSpecification;
import org.openpaas.paasta.marketplace.web.user.service.InstanceService;
import org.openpaas.paasta.marketplace.web.user.service.PriceCalculationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/priceCalculation")
public class PriceCalculationController {

    private final PriceCalculationService priceCalculationService;
    private final InstanceService instanceService;

    /**
     * 요금계산 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @GetMapping
    public String getUserInfoMain(Model model) {
        model.addAttribute("categories", instanceService.getCategories());
        model.addAttribute("spec", new SoftwareSpecification());

        return "contents/priceCalculation";
    }


}
