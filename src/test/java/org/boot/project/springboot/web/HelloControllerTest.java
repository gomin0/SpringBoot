package org.boot.project.springboot.web;

import org.boot.project.springboot.web.HelloController;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 스프링 부트 테스트와 JUnit 사이의 연결자 역할
@RunWith(SpringRunner.class)                        // 스프링 실행자 SpringRunner 사용
@WebMvcTest(controllers = HelloController.class)    // Web(Spring MVC)에 집중할 수 있는 어노테이션

@ContextConfiguration(classes = HelloController.class)
public class HelloControllerTest {

    @Autowired              // Bean 을 주입 받음
    private MockMvc mvc;    // 웹 API 테스트할 때 사용

    @Test
    public void hello_test() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))           // HTTP GET 요청
                .andExpect(status().isOk())            // 상태 검증 (OK - 200)
                .andExpect(content().string(hello));   // 결과 검증
    }

    @Test
    public void helloDto_test() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)    // API 테스트할 때 사용될 요청 파라미터 설정 (String만 가능)
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    // JSON 응답값을 필드별로 검증하는 메소드
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
