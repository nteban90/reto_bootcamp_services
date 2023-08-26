package com.postman.stepDefinitions;


import com.postman.questions.DeleteDataResponse;
import com.postman.tasks.DeleteUserTask;
import com.postman.utils.Data;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteUserStepDef {

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

    @When("I consume the endpoint  and I send the user information for delete user")
    public void iConsumeTheEndpointAndISendTheUserInformationForDeleteUser() {
        user.attemptsTo(
                DeleteUserTask.on()
        );
    }
    @Then("I can validate the response for the phone")
    public void iCanValidateTheResponseForThePhone() {
        user.should(
                seeThat(
                        "The server response for the phone was:",
                        res -> DeleteDataResponse.was().answeredBy(user).getPhone(),
                        equalTo("1-570-236-7033")
                )
        );
    }
}
