package com.postman.tasks;

import com.postman.models.UserModel;
import com.postman.questions.BuildDataUser;
import com.postman.utils.Data;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUserTask implements Task {

    Map<String, String> data = Data.extractTo().get(0);

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Delete.from(data.get("ENDPOINT")+"/1").with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                )
        );
    }

    public static DeleteUserTask on(){

        return instrumented(DeleteUserTask.class);
    }
}
