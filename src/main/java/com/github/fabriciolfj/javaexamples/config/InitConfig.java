package com.github.fabriciolfj.javaexamples.config;

import com.github.fabriciolfj.javaexamples.entity.Author;
import com.github.fabriciolfj.javaexamples.entity.Post;
import com.github.fabriciolfj.javaexamples.repository.AuthorRepository;
import com.github.fabriciolfj.javaexamples.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@RequiredArgsConstructor
public class InitConfig implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final PostRepository postRepository;
    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 100; i++) {
            var author = new Author(null, "teste " + i);
            var result = authorRepository.save(author);
            var random =  new Random().nextInt(10);

            while(random > 0) {
                var post = new Post(null, "java " + random, "book", result.getId());
                postRepository.save(post);
                random--;
            }
        }

    }
}
