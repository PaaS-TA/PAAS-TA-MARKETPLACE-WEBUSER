package org.openpaas.paasta.marketplace.web.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.api.domain.Instance;
import org.openpaas.paasta.marketplace.api.domain.SoftwareSpecification;
import org.openpaas.paasta.marketplace.web.user.common.CommonService;
import org.openpaas.paasta.marketplace.web.user.service.InstanceService;
import org.openpaas.paasta.marketplace.web.user.service.PriceCalculationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/priceCalculation")
public class PriceCalculationController {

    private final PriceCalculationService priceCalculationService;
    private final InstanceService instanceService;
    private final CommonService commonService;

    /**
     * 요금계산 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @GetMapping
    public String getUserInfoMain(Model model) {
        model.addAttribute("categories", instanceService.getCategories());
        model.addAttribute("spec", new SoftwareSpecification());

        // 전체 구매 상품 중 상품별 사용일 수
        List<Long> idIn = new ArrayList<>();
        for (Instance i:instanceService.getMyTotalList("").getContent()) {
            idIn.add(i.getId());
        }

        log.info("구매 상품 사용한 일(Day) 수 ::: " + priceCalculationService.getDayOfUseInstsPeriod(idIn));
        model.addAttribute("dayOfUsingPeriod", priceCalculationService.getDayOfUseInstsPeriod(idIn));
        return "contents/priceCalculation";
    }


}
