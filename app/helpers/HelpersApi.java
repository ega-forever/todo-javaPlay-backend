package helpers;

import com.google.common.collect.Iterables;
import play.mvc.Http;

import java.util.Arrays;

/**
 * Created by Ega on 07.03.2016.
 */
public class HelpersApi {

    public static String getHeader(Http.Context ctx, String header) {
        return Iterables.getFirst(Arrays.asList(ctx.request().headers().get(header)), null);
    }

}
