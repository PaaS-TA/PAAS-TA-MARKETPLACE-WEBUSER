package org.openpaas.paasta.marketplace.web.user.controller;

import org.openpaas.paasta.marketplace.web.user.service.PriceCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PriceCalculationController {

    @Autowired
    public PriceCalculationService priceCalculationService;

    /**
     * 요금계산 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @RequestMapping(value = {"/priceCalculation"}, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getUserInfoMain() {
        return priceCalculationService.getUserInfoMain();
    }
}
