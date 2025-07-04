package com.boot.board_250618.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * OAuth2 로그인 요청 시 인증 요청 URL에 추가 파라미터를 삽입하기 위한 커스텀 Resolver
 * (예: 구글은 prompt=consent, 네이버는 approval_prompt=force 등을 추가 가능)
 */
@Slf4j
public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {
    // Spring Security 기본 요청 생성자
    private final DefaultOAuth2AuthorizationRequestResolver defaultResolver;
    /**
     * 생성자: ClientRegistrationRepository를 받아 DefaultResolver 초기화
     * @param repo OAuth2 클라이언트 등록 정보를 담고 있는 저장소
     */
    public CustomAuthorizationRequestResolver(ClientRegistrationRepository repo) {
        log.info("@# CustomAuthorizationRequestResolver()");
        log.info("@# repo=>"+repo);

        this.defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(repo, "/oauth2/authorization");
    }

    /**
     * 요청 URI에서 registrationId (google, naver 등)를 추출
     * 예: /oauth2/authorization/google → google
     */
    private String extractRegistrationId(HttpServletRequest request){
        String uri = request.getRequestURI(); // 예: /oauth2/authorization/google
        if (uri != null && uri.contains("/oauth2/authorization/")){
            return uri.substring(uri.lastIndexOf("/")+1);
        }
        return null;
    }

    /**
     * 인증 요청을 커스터마이징하여 반환 (기본 경로에서 호출될 때)
     */
    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        log.info("@# resolve(HttpServletRequest request)");
//        @# request=>org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterRequest@6330ef0b
        log.info("@# request=>"+request);

        OAuth2AuthorizationRequest req = defaultResolver.resolve(request);
//        @# req=>org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest@16a1026d
        log.info("@# req=>"+req);
        String registrationId = extractRegistrationId(request);
//        @# registrationId=>google
        log.info("@# registrationId=>"+registrationId);

        return customizeRequest(req, registrationId);
    }

    /**
     * 인증 요청에 provider 별로 필요한 파라미터를 추가하여 새로운 요청 객체로 빌드
     * @param req 기존 요청
     * @param registrationId provider ID (google, naver 등)
     * @return 추가 파라미터를 포함한 새로운 OAuth2AuthorizationRequest
     */
    private OAuth2AuthorizationRequest customizeRequest(OAuth2AuthorizationRequest req, String registrationId){
        log.info("@# customizeRequest()");
//        @# req=>org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest@16a1026d
        log.info("@# req=>"+req);
//        @# registrationId=>google
        log.info("@# registrationId=>"+registrationId);

        if (req == null) return null;

        // 기존 파라미터 복사
        Map<String, Object> extraParams = new HashMap<>(req.getAdditionalParameters());

        // provider 별로 추가 파라미터 설정
        if ("google".equals(registrationId)){
//            구글은 "consent"로 매번 동의창 표시
            extraParams.put("prompt","consent");
        } else if ("naver".equals(registrationId)) {
            // 네이버 자동 로그인 방지
            extraParams.put("auth_type", "reauthenticate");
        }

        // 새로운 인증 요청 객체 반환
        return OAuth2AuthorizationRequest.from(req)
                .additionalParameters(extraParams)
                .build();
    }

    /**
     * 인증 요청을 커스터마이징하여 반환 (클라이언트 ID가 명시적으로 주어졌을 때)
     */
    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        log.info("@# resolve(HttpServletRequest request, String clientRegistrationId)");

        return null;
    }
}
