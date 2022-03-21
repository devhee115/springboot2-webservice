package com.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복 라이브러리. 선언된 모든 필드의 get메소드 생성해 줌
@NoArgsConstructor // 기본 생성자 자동 추가 public Post(){} 와 같은 효과
@Entity // 테이블과 링크될 클래스임을 나타냄.  !! Entity클래스에서는 절대 Setter 메소드를 만들지 않음. // Entity클래스를 기준으로 테이블이 생성되고 스키마가 변경됨
public class Posts extends BaseTimeEntity{

    @Id // 해당 테이블의 PK필드를 나타냄
    //PK의 생성규칙을 나타냄 .
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 스프링부트2.0에서는 GenerationType.IDENTITY 옵션추가해야한 auto_increment됨
    private Long id;

    //테이블의 칼럼을 나타냄. 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨
    @Column(length = 500, nullable = false)  // varchar(255)가 기본인데 사이즈를 500으로 늘리고 싶거나 타입을 변경하고 싶거나 등의 경우에 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
