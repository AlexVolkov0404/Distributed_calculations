package org.example.Core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class Contact implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String skype;
    private String email;
    private String fbProfile;

    public Contact() {

    }
    public Contact(int id, String name, String phone, String skype, String email, String fbProfile) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.skype = skype;
        this.email = email;
        this.fbProfile = fbProfile;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", skype='" + skype + '\'' +
                ", email='" + email + '\'' +
                ", fbProfile='" + fbProfile + '\'' +
                '}';
    }
}