package com.cnu.real_coding_server.repository;

import com.cnu.real_coding_server.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> { //JpaRepository가 제공하는 함수들, Post는 객체, Integer는 id값
//    Post save(Post post);
//    List<Post> findAll();
//    Optional<Post> findById(Integer postId);
//    void delete(Post post);
}
