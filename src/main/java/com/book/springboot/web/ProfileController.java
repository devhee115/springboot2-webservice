package com.book.springboot.web;


import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        List<String> profiles = Arrays.asList(env.getActiveProfiles()); // 현재 실행 중인 ActiveProfile을 모두 가져옴 즉, real,oauth,real-db등이 활성화 되어있다면 3개가 모두 담겨있다
        List<String> realProfiles = Arrays.asList("real", "real1", "real2"); //real, real1, real2 모두 배포에 사용될 profile이라 하나라도 있으면 반환함
        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
