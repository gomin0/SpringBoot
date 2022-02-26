package web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.dto.HelloResponseDto;

@RestController //컨트롤러를 JASON을 반환하는 컨트롤러로
public class HelloController {

    @GetMapping("/hello") //Get요청 받는 API로 만듬듬
   public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
