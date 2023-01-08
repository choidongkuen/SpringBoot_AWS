package com.example.springboot_aws.domain.posts;


import com.example.springboot_aws.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@EnableJpaAuditing()
@Entity
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;


    @Builder // 생성자에 @Builder 추가 시 생성자에 포함된 필드만 빌더에 포함!!!
    public Posts(String title, String content, String author) {

        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void update(String content, String title) {

        this.title = title;
        this.content = content;
    }

}
