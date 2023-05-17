package com.github.fabriciolfj.javaexamples.entrypoint;

import com.github.fabriciolfj.javaexamples.entity.Author;
import com.github.fabriciolfj.javaexamples.entity.Post;
import com.github.fabriciolfj.javaexamples.repository.AuthorRepository;
import com.github.fabriciolfj.javaexamples.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostRepository repository;
    private final AuthorRepository authorRepository;

    @QueryMapping
    public List<Post> recentPosts(@Argument int count, @Argument int offset) {
        return repository.findAll()
                .stream().skip(offset)
                .limit(count)
                .collect(Collectors.toList());
    }

    @SchemaMapping
    public Author author(Post post) {
        return authorRepository.findById(post.getAuthorId()).get();
    }

    @SchemaMapping(typeName="Post", field="first_author")
    public Optional<Author> getFirstAuthor(Post post) {
        return authorRepository.findById(post.getAuthorId());
    }

    @MutationMapping
    @Transactional(propagation = Propagation.REQUIRED)
    public Post createPost(@Argument String title,
                           @Argument String category, @Argument Long authorId) {
        Post post = new Post();
        post.setTitle(title);
        post.setCategory(category);
        post.setAuthorId(authorId);
        repository.save(post);

        return post;
    }
}
