package com.spring.calculator.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*
        DAUG-SU-DAUG - tai ryšys tarp dviejų lentelių, kur keletas eilučių iš vienos lentelės gali turėti
        keletą atitinkančių eilučių iš kitos lentelės. Pvz.: mokykla, kurioje mokytojai mokina mokinius.
        Kiekvienas mokytojas mokina daugelį moksleivių ir kiekvienas moksleivis yra mokinamas daugelio mokytojų.
        Svarbu paminėti, jog aprašant šį ryšį tarp lentelių, yra sukuriama dar viena papildoma (tarpinė) lentelė,
        kuri apjungia pastarąsias. Taigi daug-su-daug ryšys susideda iš dviejų ryšių vienas-su-daug.
        Šiuo atveju sukuriama db tarpinė lentelė 'user_roles', apjungianti 'user' ir 'roles' lenteles.
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
