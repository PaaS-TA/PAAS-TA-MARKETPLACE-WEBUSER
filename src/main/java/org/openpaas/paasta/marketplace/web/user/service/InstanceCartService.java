package org.openpaas.paasta.marketplace.web.user.service;

import java.util.List;

import org.openpaas.paasta.marketplace.api.domain.InstanceCart;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
	
    public List<InstanceCart> getAllList(String queryParamString) {
        ResponseEntity<List<InstanceCart>> responseEntity = paasApiRest.exchange("/instances/cart/allList" + queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<List<InstanceCart>>() {});
        List<InstanceCart> instanceCartList = responseEntity.getBody();
        
        log.debug(">>>>>>> instanceCartList: {}", (instanceCartList != null ? instanceCartList.toString() : "instanceCartList is null"));
        
        return instanceCartList;
    }
    
    public Integer allDelete(String queryParamString) {
    	ResponseEntity<Integer> responseEntity = paasApiRest.exchange("/instances/cart/allDelete" + queryParamString, HttpMethod.DELETE, null, new ParameterizedTypeReference<Integer>() {});
    	Integer result = responseEntity.getBody();
    	
    	log.debug(">>>>>>> instanceCart allDelete result: {}", result);
    	
    	return result;
    }
}
