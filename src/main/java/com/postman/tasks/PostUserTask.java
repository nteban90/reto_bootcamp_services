package com.postman.tasks;

import com.postman.models.UserModel;
import com.postman.questions.BuildDataUser;
import com.postman.utils.Data;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostUserTask implements Task {

    Map<String, String> data = Data.extractTo().get(0);

    @Override
    public <T extends Actor> void performAs(T actor) {
        UserModel userInfo = actor.asksFor(BuildDataUser.was());
        actor.attemptsTo(
                Post.to(data.get("ENDPOINT")).with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(userInfo)
                )
        );
    }

    public static PostUserTask on(){

        return instrumented(PostUserTask.class);
    }
}
