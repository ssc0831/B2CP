package com.group5.b2c.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group5.b2c.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Comment 엔티티에 필요한 추가적인 메서드가 있다면 선언합니다.
}
