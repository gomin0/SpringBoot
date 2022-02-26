package org.boot.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// 프로젝트의 메인 클래스
@ComponentScan
@SpringBootApplication      // 스프링 부트 자동 설정
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}