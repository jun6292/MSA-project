package com.sparta.msa_exam.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service")
public interface AuthFeignClient extends AuthService {  // 인증 서비스 호출 클라이언트가 응용 계층의 인터페이스인 AuthService 를 상속받아 DIP 를 적용
    @GetMapping("/auth/verify")
    Boolean verifyUser(@RequestParam(name = "user_id") String userId);
}
