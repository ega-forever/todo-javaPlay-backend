package controllers;

import com.google.inject.Inject;
import helpers.AuthHelpers;
import helpers.HelpersApi;
import models.Task;
import models.User;
import play.cache.CacheApi;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.List;

import static play.libs.Json.toJson;


/**
 * Created by Ega on 06.03.2016.
 */

public class TodoController extends Controller {

    @Inject
    AuthHelpers user;

    @Security.Authenticated(AuthController.class)
    public Result get(){

        User userSet = user.get();
        List<Task> tasks = Task.find.where().eq("user.id", userSet.id).findList();
        return ok(toJson(tasks));
    }


    @Security.Authenticated(AuthController.class)
    public Result add(){
        Task task = Form.form(Task.class).bindFromRequest().get();
        task.user = user.get();
        task.save();
        return ok(toJson(task));

    }



    @Security.Authenticated(AuthController.class)
    public Result remove(Long id){
        Task task = Task.find.where().eq("id", id).findUnique();
        task.delete();
        return ok();

    }

}