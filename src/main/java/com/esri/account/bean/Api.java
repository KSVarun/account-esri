package com.esri.account.bean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "APIKEY")
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "API key missing")
    private String key;

    public Api() {
    }

    public Api(Long id, String key) {
        this.id = id;
        this.key = key;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Api{" +
                "id=" + id +
                ", key='" + key + '\'' +
                '}';
    }
}
