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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;

@Controller
public class AppUserController
{
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AppUserRepository applicationUserRepository;

    @PostMapping("/users")
    public RedirectView createUser(String username, String password, String fullName, Date dateOfBirth, String bio)
    {
        AppUser newUser = new AppUser(username,

                encoder.encode(password),
                fullName,
                dateOfBirth,
                bio);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }

    @GetMapping("/users/{id}")
    public String getIndividualUserPage(@PathVariable long id, Model model, Principal principal)
    {
        AppUser currentUser = (AppUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        AppUser individualUser = applicationUserRepository.findById(id).get();
        model.addAttribute("individualUser", individualUser);
        model.addAttribute("userID", currentUser.getId());
        model.addAttribute("username", currentUser.getUsername());

        return "userLog";
    }

    @GetMapping("/myprofile")
    public String getCurrentUserProfile(Principal principal, Model model)
    {
        AppUser currentUser = (AppUser)((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        model.addAttribute("individualUser", currentUser);
        model.addAttribute("userID", currentUser.getId());
        return "userLog";
    }

    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }}


