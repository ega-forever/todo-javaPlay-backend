package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import helpers.HelpersApi;
import models.User;
import play.cache.CacheApi;
import play.cache.NamedCache;
import play.data.Form;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import java.util.UUID;


/**
 * Created by Ega on 06.03.2016.
 */
public class AuthController extends Security.Authenticator  {

    @Inject
    @NamedCache("session-cache")
    CacheApi cache;


    public Result Register(){
        User user = Form.form(User.class).bindFromRequest().get();

        if(user.username != null && user.password != null &&
                User.find.where().eq("username", user.username).findUnique() == null){

            user.save();


            return ok(setToken(user));
        }
        return ok();
    }


    public Result Login(){
        User user = Form.form(User.class).bindFromRequest().get();
        user = User.find.where().eq("username", user.username).eq("password", user.password).findUnique();
        if(user != null){
            return ok(setToken(user));
        }
        return ok();
    }


    private ObjectNode setToken(User user){
        UUID uuid = UUID.randomUUID();
        cache.set(uuid.toString(), user, 24*3600);

        ObjectNode result = Json.newObject();
        result.put("x-token", uuid.toString());
        result.put("username", user.username);
        return result;
    }

    @Override
    public String getUsername(Http.Context ctx) {
        String token = HelpersApi.getHeader(ctx, "x-token");
        if (token != null) {
            User user = cache.get(token);
            if (user != null) {
                return user.username;
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return super.onUnauthorized(context);
    }

}