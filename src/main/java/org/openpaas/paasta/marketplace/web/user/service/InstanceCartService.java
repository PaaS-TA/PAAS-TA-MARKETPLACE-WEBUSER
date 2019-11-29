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

@Service
@RequiredArgsConstructor
public class InstanceCartService {

	private final RestTemplate paasApiRest;
	
	public InstanceCart createInstanceCart(InstanceCart instanceCart) {
        return paasApiRest.postForObject("/instances/cart", instanceCart, InstanceCart.class);
    }
	
	/**
	 * User의 모든 장바구니상품 조회
	 * @param queryParamString
	 * @return
	 */
    public List<InstanceCart> getAllList(String queryParamString) {
        ResponseEntity<List<InstanceCart>> responseEntity = paasApiRest.exchange("/instances/cart/allList" + queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<List<InstanceCart>>() {});
        List<InstanceCart> instanceCartList = responseEntity.getBody();
        return instanceCartList;
    }
    
    /**
     * User의 모든 장바구니상품 조회 기간한정
     * @param queryParamString
     * @return
     */
    public List<InstanceCart> getUserAllCartList(String queryParamString) {
    	ResponseEntity<List<InstanceCart>> responseEntity = paasApiRest.exchange("/instances/cart/userAllCartList" + queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<List<InstanceCart>>() {});
    	List<InstanceCart> instanceCartList = responseEntity.getBody();
    	return instanceCartList;
    }
    
    /**
     * User의 장바구니 상품 모두 삭제
     * @param queryParamString
     * @return
     */
    public Integer allDelete(String queryParamString) {
    	ResponseEntity<Integer> responseEntity = paasApiRest.exchange("/instances/cart/allDelete" + queryParamString, HttpMethod.DELETE, null, new ParameterizedTypeReference<Integer>() {});
    	Integer result = responseEntity.getBody();
    	return result;
    }
    
    /**
     * 특이사항: 현재 구현된 공통에서는 exchage를 사용하여 requestBody를 담아도 보내지지 않아 Params로 처리함
     * @param instanceCartSpecification
     * @return
     */
    public Integer delete(InstanceCartSpecification spec) {
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
    
    
    /**
     * 장바구니 페이지 리스트 조회
     * @param queryParamString
     * @return
     */
    public List<InstanceCart> getPageList(String queryParamString) {
        ResponseEntity<List<InstanceCart>> responseEntity = paasApiRest.exchange("/instances/cart/page/list" + queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<List<InstanceCart>>() {});
        List<InstanceCart> instanceCartList = responseEntity.getBody();
        return instanceCartList;
    }

    /**
     * 장바구니의 상품 구매
     * @param instanceCart
     * @return
     */
	public InstanceCart purchaseCart(InstanceCart instanceCart) {
        return paasApiRest.postForObject("/instances/cart/purchaseCart", instanceCart, InstanceCart.class);
    }

}
