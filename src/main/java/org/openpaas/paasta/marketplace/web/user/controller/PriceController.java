package org.openpaas.paasta.marketplace.web.user.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openpaas.paasta.marketplace.api.domain.Instance;
import org.openpaas.paasta.marketplace.api.domain.SoftwareSpecification;
import org.openpaas.paasta.marketplace.web.user.common.CommonService;
import org.openpaas.paasta.marketplace.web.user.service.InstanceService;
import org.openpaas.paasta.marketplace.web.user.service.PriceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;
    private final InstanceService instanceService;
    private final CommonService commonService;

    /**
     * 요금계산 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @GetMapping(value = "/priceCalculation")
    public String getUserInfoMain(Model model) {
        model.addAttribute("categories", instanceService.getCategories());
        model.addAttribute("spec", new SoftwareSpecification());

        // 전체 구매 상품 중 상품별 사용일 수
        List<Long> idIn = new ArrayList<>();
        for (Instance i:instanceService.getMyTotalList("").getContent()) {
            idIn.add(i.getId());
        }
        
        // 사용 시작 종료일자 생성 
        Calendar calendar = Calendar.getInstance();
        String currYear = String.valueOf(calendar.get(Calendar.YEAR));
        String currMonth = String.valueOf(calendar.get(Calendar.MONTH)+1);
        currMonth = (currMonth.length() == 1) ? "0"+ currMonth : currMonth;
        String lastDay = String.valueOf(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        		
        String usageStartDate = String.format("%s-%s-01", currYear, currMonth);
        String usageEndDate = String.format("%s-%s-%s", currYear, currMonth, lastDay);
        model.addAttribute("dayOfUsingPeriod", priceService.getDayOfUseInstsPeriod(idIn, usageStartDate, usageEndDate));
        
        return "contents/priceCalculation";
    }

    
    /**
     * 요금통계 메인페이지로 이동한다.
     *
     * @return ModelAndView(Spring 클래스)
     */
    @GetMapping(value = "/priceStatistics")
    public String getPriceStatsMain(Model model) {
        
        return "contents/priceStatistics";
    }
    
    /**
     * 요금통계 결과 객체를 불러온다.
     *
     * @return Map
     */
    @GetMapping(value = "/stats/instances/my/price/total")
    @ResponseBody
    public Map<Long, Object> purchaseAmount(HttpServletRequest httpServletRequest){
        return priceService.purchaseAmount(commonService.setParameters(httpServletRequest));
    }
}
