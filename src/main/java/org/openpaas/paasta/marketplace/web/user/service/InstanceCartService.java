package org.openpaas.paasta.marketplace.web.user.service;

import org.openpaas.paasta.marketplace.api.domain.InstanceCart;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InstanceCartService {

	private final RestTemplate paasApiRest;
	
	public InstanceCart createInstanceCart(InstanceCart instanceCart) {
        return paasApiRest.postForObject("/instances/cart", instanceCart, InstanceCart.class);
    }
}
