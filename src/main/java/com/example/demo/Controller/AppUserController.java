package com.example.demo.Controller;
import com.example.demo.Models.AppUser;
import com.example.demo.Repositries.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;

@Controller
public class AppUserController {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/")
    public String getRoot(Principal p, Model m) {

        AppUser applicationUser = null;
        if (p != null) {
            applicationUser = appUserRepository.findByUsername(p.getName());
        }
        m.addAttribute("user", applicationUser);

        return "root";
    }

    @GetMapping("/myprofile")
    public String getProfile(Principal p, Model m) {
        AppUser applicationUser = null;

        if (p != null) {
            applicationUser = appUserRepository.findByUsername(p.getName());
        }
        m.addAttribute("user", applicationUser);
        return "userLog";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "registration";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, String firstName, String lastName,
                                   Date dateOfBirth, String bio) {
        AppUser newUser = new AppUser(username, encoder.encode(password), firstName, lastName,
                dateOfBirth,
                bio);
        appUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }
}


