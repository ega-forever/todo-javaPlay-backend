package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Ega on 07.03.2016.
 */
@Entity
public class Task extends Model {

    public static Finder<Integer, Task> find = new Finder<>(Task.class);

    @Id
    public Integer id;
    public String name;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

}
