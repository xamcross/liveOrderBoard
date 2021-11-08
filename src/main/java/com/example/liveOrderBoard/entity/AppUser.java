package com.example.liveOrderBoard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "app_user")
@ToString
public class AppUser {
    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "app_user_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "app_user_seq")
    private long id;
    private String name;
}
