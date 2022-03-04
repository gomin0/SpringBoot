package org.boot.project.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.boot.project.springboot.domain.posts.Posts;
import org.boot.project.springboot.domain.posts.PostsRepository;
import org.boot.project.springboot.web.dto.PostsListResponseDto;
import org.boot.project.springboot.web.dto.PostsResponseDto;
import org.boot.project.springboot.web.dto.PostsSaveRequestDto;
import org.boot.project.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // posts -> new PostsListResponseDto(post)
                .collect(Collectors.toList());
    }
}
