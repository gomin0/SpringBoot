package org.boot.project.springboot.web;

import lombok.RequiredArgsConstructor;
import org.boot.project.springboot.config.auth.LoginUser;
import org.boot.project.springboot.config.auth.dto.SessionUser;
import org.boot.project.springboot.service.posts.PostsService;
import org.boot.project.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    // @LoginUser 를 사용하여 세션 정보를 갖고 옴
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {  // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
