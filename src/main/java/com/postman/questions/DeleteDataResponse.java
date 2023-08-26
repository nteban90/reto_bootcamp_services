package com.postman.questions;

import com.postman.models.deleteResponse.DeleteResponseModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class DeleteDataResponse implements Question<DeleteResponseModel> {

    @Override
    public DeleteResponseModel answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(DeleteResponseModel.class);
    }

    public static DeleteDataResponse was(){
        return new DeleteDataResponse();
    }

}
