package org.openpaas.paasta.marketplace.web.user.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class PriceCalculationService {

    public ModelAndView getUserInfoMain() {
        return new ModelAndView() {{
            setViewName("contents/priceCalculation");
        }};
    }
}
