package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Ega on 06.03.2016.
 */
@Entity
public class User extends Model {

    public static Finder<Integer,User> find = new Finder<>(User.class);

    @Id
    public Integer id;
    public String username;
    public String password;


}
