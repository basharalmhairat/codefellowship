package com.example.demo.Controller;
import com.example.demo.Models.AppUser;
import com.example.demo.Models.postUser;
import com.example.demo.Repositries.AppUserRepository;
import com.example.demo.Repositries.postUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.sql.Date;

@Controller
public class postController {
    @Autowired
    AppUserRepository applicationUserRepository;

    @Autowired
    postUserRepository postRepository;

    @PostMapping("/users/post")
    public RedirectView createPost(String body, Principal principal) {
        long milliseconds = System.currentTimeMillis();
        Date createdAt = new Date(milliseconds);
        AppUser currentUser = applicationUserRepository.findByUsername(principal.getName());
        postUser post = new postUser(body, createdAt, currentUser);
        postRepository.save(post);
        return new RedirectView("/users/" + currentUser.getId());
    }
}