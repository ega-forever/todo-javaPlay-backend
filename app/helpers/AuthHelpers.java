package helpers;

/**
 * Created by Ega on 07.03.2016.
 */

import com.google.inject.Inject;
import models.User;
import play.Logger;
import play.cache.CacheApi;
import play.cache.NamedCache;
import play.mvc.Http;

import javax.inject.Singleton;

@Singleton
public class AuthHelpers {

    @Inject
    @NamedCache("session-cache")
    CacheApi cache;

    public User get(){
        String token = HelpersApi.getHeader(Http.Context.current(), "x-token");
        return cache.get(token);
    }

}
