package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.TestService;

/**
 * Created by zg on 2016/6/27.
 */
public class Application extends Controller {

    public Result index() {
        return ok("Your new application is ready.");
    }

    public Result test(int userId) {
        return ok(Json.toJson(new TestService().test(userId)));
    }
}
