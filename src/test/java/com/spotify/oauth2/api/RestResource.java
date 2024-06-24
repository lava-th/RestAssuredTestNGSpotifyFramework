package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;


public class RestResource {

    public static Response post(String path,String Token,Object requestPlaylist){
        return given(getRequestSpec()).
             body(requestPlaylist).
        auth().oauth2(Token).
               // header("Authorization", "Bearer " + Token).
                when()
                .post(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

    }
    public static Response postAccount(HashMap<String,String> formParams){
        return given()
                .baseUri("https://accounts.spotify.com")
                .contentType(ContentType.URLENC)
                .formParams(formParams)
                .when().post("/api/token")
                .then().spec(getResponseSpec()).extract().response();
    }

    public static Response get(String path,String Token){
        return given(getRequestSpec())
                . auth().oauth2(Token)
                //.  header("Authorization", "Bearer " + Token)
                .when()
                .get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

    }
    public static Response update(String path,String Token,Object requestPlaylist){

        return given(getRequestSpec())
                .body(requestPlaylist)
                . auth().oauth2(Token)
                //.  header("Authorization", "Bearer " + Token)
                .when()
                .put(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();

    }
}
