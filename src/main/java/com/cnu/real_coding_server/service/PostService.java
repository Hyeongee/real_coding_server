package com.cnu.real_coding_server.service;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.model.request.PostRequest;
import com.cnu.real_coding_server.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService { //post를 저장,수정,삭제
    private final PostRepository postRepository;
    public Post createPost(PostRequest postRequest) {
        return postRepository.save(postRequest.toEntity());
    }

    public List<Post> getPosts() { //모든 게시글을 조회
        return postRepository.findAll();
    }

    public Optional<Post> getPost(Integer postId) { //특정 게시글을 조회
        return postRepository.findById(postId);
    }

    public Optional<Post> updatePost(Integer postId, PostRequest postRequest){ //postId인 글을 postRequest로 수정
        return postRepository.findById(postId)
                .map(post -> { //post가 있다면
                    post.setTitle(postRequest.getTitle());
                    post.setContents(postRequest.getContents());
                    post.setTag(postRequest.getTag());
                    return postRepository.save(post);
                });
    }

    public void deletePost(Integer postId){
        postRepository.findById(postId)
                .ifPresent(post -> postRepository.delete(post)); //.ifPresent(postRepository::delete)로도 가능
    }




}
