package com.example.demo.Models;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
public class AppUser implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String password;
    public String fullName;
    public Date dateOfBirth;
    public String bio;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "poster")
    public List<postUser> posts;

    @ManyToMany
    @JoinTable
            (
                    name = "followers",
                    joinColumns = { @JoinColumn(name = "primaryUser") },
                    inverseJoinColumns = { @JoinColumn(name = "followedUser") }
            )
    public Set<AppUser> usersThatIFollow;

    @ManyToMany(mappedBy = "usersThatIFollow")
    public Set<AppUser> usersThatFollowMe;

    public AppUser() {}

    public AppUser(String username, String password, String fullName, Date dateOfBirth, String bio)
    {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.posts = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<postUser> getPosts()
    {
        return this.posts;
    }

    public void setPosts(ArrayList<postUser> postsList)
    {
        this.posts = postsList;
    }

    public void addPosts(postUser post)
    {
        this.posts.add(post);
    }

    public Set<AppUser> getUsersThatIFollow()
    {
        return this.usersThatIFollow;
    }

    public void addFollowers(AppUser followingUser)
    {
        usersThatIFollow.add(followingUser);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}