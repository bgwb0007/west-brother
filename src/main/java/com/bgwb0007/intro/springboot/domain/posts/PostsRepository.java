package com.bgwb0007.intro.springboot.domain.posts;

import com.bgwb0007.intro.springboot.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
