package com.example.demo.Models;
import javax.persistence.*;
import java.sql.Date;

    @Entity
    public class postUser {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        long id;

        @Column(columnDefinition = "text")
        String body;
        Date createdAt;

        @ManyToOne
        AppUser poster;

        public long getId()
        {
            return id;
        }

        public String getBody()
        {
            return body;
        }

        public Date getCreatedAtDate()
        {
            return createdAt;
        }

        public postUser() {}

        public postUser(String body, Date createdAt, AppUser poster)
        {
            this.body = body;
            this.createdAt = createdAt;
            this.poster = poster;
        }
}
