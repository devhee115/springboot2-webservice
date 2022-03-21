package com.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
iBatis에서 Dao라고 불리는 DB Layer접근자와 같음
JPA에선 Repository라고 부르며 인터페이스로 생성함.
JpaRpostitory<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD메소드가 자동으로 생성됨
@Repository를 추가할 필요도 없음.

 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
