package com.example.liveOrderBoard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @GeneratedValue
    private long id;
    private String name;
}
