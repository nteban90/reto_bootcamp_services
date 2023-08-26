package com.postman.stepDefinitions;

import com.postman.questions.ServerResponse;
import com.postman.tasks.PostUserTask;
import com.postman.utils.Data;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import io.cucumber.java.en.*;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class PostUserStepDef {

    private EnvironmentVariables environmentVaribales;
    private String theRestApiBaseUrl;

    Actor user= Actor.named("user");
    Map<String, String> data = Data.extractTo().get(0);
    @Before
    public void setUpBaseURl(){
        theRestApiBaseUrl = environmentVaribales.optionalProperty("restapi.baseurl")
                .orElse("https://fakestoreapi.com");
        user.whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @When("I consume the endpoint and I send the user information email, username, password, firstname, lastname \"")
    public void setInfoUser() {
        user.attemptsTo(
                PostUserTask.on()
        );
    }

    @Then("I can validate the response service")
    public void iCanValidateTheResponseService() {

            user.should(
                    seeThat(
                            "The code of response was:",
                            ServerResponse.was(),
                            equalTo(Integer.parseInt(data.get("CODE")))
                    )
            );
    }

}
