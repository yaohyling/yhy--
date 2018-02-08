package com.boot.yhy.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.yhy.entity.Product;
import com.boot.yhy.repository.ProductRepository;

@Controller
// @RequestMapping("")
public class TestController {
	@Resource
	private ProductRepository pr;

	@GetMapping("view")
	public ModelAndView viewResolver() {
		ModelAndView mv = new ModelAndView("yhy");
		Character string = new Character('x');
		return mv;

	}

	@ResponseBody
	@GetMapping("/add")
	public String addProduct() {
		
		 Product product = new Product(11L, "sssssssss","qq", 2123L, 123L, "1213"); product.setDate(new Date());
		 pr.save(product);
		 
		List<Long> ids = pr.getIds();
		System.out.println(ids.size() + "====" + ids.get(1));
		return "XX";
	}

	@ResponseBody
	@GetMapping("/pro")
	public String page() {
		Sort sort = new Sort(Direction.DESC, "id");
		PageRequest pageable = new PageRequest(1, 5, sort);
		Specification<Product> sp = new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p = cb.gt(root.get("id").as(Long.class), 0);
				return p;
			}
		};
		Page<Product> p = pr.findAll(sp, pageable);
		System.out.println(p.getNumber()); // 当前页
		System.out.println(p.getNumberOfElements()); // 当前页记录数
		System.out.println(p.getSize()); // pagesize
		System.out.println(p.getTotalElements()); // 总记录数
		System.out.println(p.getTotalPages()); // 总页数
		System.out.println(p.getContent().size());
		System.out.println(p.getContent().get(0).toString());
		return "ok";
	}

	@RequestMapping("authority")
	@ResponseBody
	public JSONObject authority() throws OAuthSystemException,
			OAuthProblemException {
		JSONObject result = new JSONObject();
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthClientRequest codeTokenRequest = OAuthClientRequest
				.authorizationLocation("http://10.163.252.157:8080/oauth/token")
				.setResponseType(ResponseType.CODE.toString()).setClientId("1")
				.buildQueryMessage();
		// 获取 code
		OAuthResourceResponse codeResponse = oAuthClient.resource(
				codeTokenRequest, OAuth.HttpMethod.POST,
				OAuthResourceResponse.class);
		if (codeResponse.getResponseCode() != HttpServletResponse.SC_OK) {
			result.put("code", codeResponse.getResponseCode());
			result.put("msg", codeResponse.getBody());
		} else {
			String authCode = codeResponse.getBody();
			OAuthClientRequest accessTokenRequest = OAuthClientRequest
					.tokenLocation(
							"http://127.0.0.1:8080/auth-web/oauth/accessToken")
					.setGrantType(GrantType.AUTHORIZATION_CODE)
					.setClientId("c1ebe466-1cdc-4bd3-ab69-77c3561b9dee")
					.setClientSecret("d8346ea2-6017-43ed-ad68-19c0f971738b")
					.setCode(authCode)
					.setRedirectURI("http://127.0.0.1:8080/auth-web/")
					.buildQueryMessage();
			// 获取access token
			OAuthAccessTokenResponse tokenResponse = oAuthClient.accessToken(
					accessTokenRequest, OAuth.HttpMethod.POST);
			if (tokenResponse.getResponseCode() != HttpServletResponse.SC_OK) {
				result.put("code", tokenResponse.getResponseCode());
				result.put("msg", tokenResponse.getBody());
				return result;
			} else {
				// 验证token
				OAuthClientRequest validateRequest = new OAuthBearerClientRequest(
						"http://127.0.0.1:8080/auth-web/oauth/validate")
						.setAccessToken(tokenResponse.getAccessToken())
						.buildQueryMessage();
				OAuthResourceResponse validateResponse = oAuthClient.resource(
						validateRequest, OAuth.HttpMethod.GET,
						OAuthResourceResponse.class);
				if (validateResponse.getResponseCode() != HttpServletResponse.SC_OK) {
					result.put("code", validateResponse.getResponseCode());
					result.put("msg", validateResponse.getBody());
				} else {
					JSONObject body = JSON.parseObject(validateResponse
							.getBody());
					result.put("code", body.getString("code"));
					result.put("msg", body.getString("msg"));
				}
			}
		}
		return result;
	}
}
