package org.openpaas.paasta.marketplace.web.user.controller;

import org.openpaas.paasta.marketplace.api.domain.InstanceCart;
import org.openpaas.paasta.marketplace.web.user.service.InstanceCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/instances/cart")
@Slf4j
@RequiredArgsConstructor
public class InstanceCartController {

	private final InstanceCartService instanceCartService;
	
    @PostMapping
    @ResponseBody
    public InstanceCart createInstance(@RequestBody InstanceCart instanceCart) {
        return instanceCartService.createInstanceCart(instanceCart);
    }
}
