package com.postman.tasks;

import com.postman.models.UserModel;
import com.postman.questions.BuildDataUser;
import com.postman.utils.Data;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutUserTask implements Task {

    Map<String, String> data = Data.extractTo().get(0);

    @Override
    public <T extends Actor> void performAs(T actor) {
        UserModel userInfo = actor.asksFor(BuildDataUser.was());
        actor.attemptsTo(
                Put.to(data.get("ENDPOINT")+"/7").with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(userInfo)
                )
        );
    }

    public static PutUserTask on(){

        return instrumented(PutUserTask.class);
    }
}
