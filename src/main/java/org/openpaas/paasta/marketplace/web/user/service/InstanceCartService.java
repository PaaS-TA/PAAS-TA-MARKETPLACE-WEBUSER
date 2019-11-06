package org.openpaas.paasta.marketplace.web.user.service;

import java.util.List;

import org.openpaas.paasta.marketplace.api.domain.InstanceCart;
import org.openpaas.paasta.marketplace.api.domain.InstanceCartSpecification;
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
    
    public List<InstanceCart> getUserAllCartList(String queryParamString) {
    	log.info(">>>>>>> queryParamString: {}", queryParamString);
    	ResponseEntity<List<InstanceCart>> responseEntity = paasApiRest.exchange("/instances/cart/userAllCartList" + queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<List<InstanceCart>>() {});
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
    
    /**
     * 특이사항: 현재 구현된 공통에서는 exchage를 사용하여 requestBody를 담아도 보내지지 않아 Params로 처리함
     * @param instanceCartSpecification
     * @return
     */
    public Integer delete(InstanceCartSpecification spec) {
//    	HttpEntity<InstanceCartSpecification> requestBody = new HttpEntity<>(instanceCartSpecification);
//    	ResponseEntity<Integer> responseEntity = paasApiRest.exchange("/instances/cart/delete", HttpMethod.DELETE, requestBody, Integer.class);
//    	return responseEntity.getBody();
    	String params = "";
    	int deleteConut = 0;
    	if (spec.getInInstanceCartId() != null && !spec.getInInstanceCartId().isEmpty()) {
    		for (Long cartId : spec.getInInstanceCartId()) {
    			params += "&inInstanceCartId="+ cartId.toString();
    			deleteConut++;
    		}
    	}
    	paasApiRest.delete("/instances/cart/delete?"+ params);
    	return deleteConut;
    }
}
