package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationapi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTests {
    public Playlist playlistBuilder(String name, String description, boolean _public){
        // implement with builder pattern
        return Playlist.builder()
                .name(name)
                .description(description)
                ._public(_public)
                .build();
        // implement without builder pattern
//        Playlist playlist=new Playlist();
//        playlist.setName(name);
//        playlist.setDescription(description);
//        playlist.set_public(_public);
//        return  playlist;

    }
    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist,Playlist requestPlaylist){
        assertThat(requestPlaylist.getName(), equalTo(responsePlaylist.getName()));
        assertThat(requestPlaylist.getDescription(), equalTo(responsePlaylist.getDescription()));
        assertThat(requestPlaylist.get_public(), equalTo(responsePlaylist.get_public()));

    }
    @Step
    public void assertStatusCode(int actualStatusCode,int expectedStatusCode){
assertThat(actualStatusCode,equalTo(expectedStatusCode));
    }
    @Step
    public  void assertContentType(String actualContentType,String expectedContentType){
        assertThat(actualContentType,equalTo(expectedContentType));
    }
    @Step
    public void assertError(Error responseEr,int expectedStautscode,String expectedMsg){


        assertThat(responseEr.getError().getStatus(), equalTo(expectedStautscode));
        assertThat(responseEr.getError().getMessage(), equalTo(expectedMsg));
    }
    @Story("create a Playlist story")
@Link("https://example/org")
@Link(name="allure",type="mylink")
@TmsLink("12345")
@Issue("1234567")
    @Description("this is the description")
    @Test(description = "should able to create a playlist")
    public void shouldBeAbleToCreateAPlaylists() throws FileNotFoundException {
        //By using builder pattern to avoid the repeating objects for every method
        Playlist requestPlaylist=playlistBuilder(generateName(),generateDescription(),false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201.getCode());
        assertContentType(response.contentType(),"application/json; charset=utf-8");
        assertPlaylistEqual(response.as(Playlist.class),requestPlaylist);





    }

    @Test
    public void shouldBeAbleToGetAPlaylist() throws FileNotFoundException {
        Playlist requestPlaylist=playlistBuilder("New Playlist test","New playlist description test",true);


        Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200.getCode());

        assertContentType(response.contentType(),"application/json; charset=utf-8");

        assertPlaylistEqual(response.as(Playlist.class),requestPlaylist);



    }

    @Test
    public void shouldBeAbleToUpdateAPlaylists() throws FileNotFoundException {
        Playlist requestPlaylist=playlistBuilder(generateName(),generateDescription(),false);

        Response response = PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_200.getCode());



    }
    @Story("create a Playlist story")
    @Test
    public void shouldNotBeAbleToUpdateAPlaylistsWithoutName() throws FileNotFoundException {
        Playlist requestPlaylist=playlistBuilder("",generateDescription(),false);

        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_400.getCode());
        assertContentType(response.contentType(),"application/json; charset=utf-8");

assertError(response.as(Error.class),StatusCode.CODE_400.getCode(), StatusCode.CODE_400.setMsg());



    }
    @Story("create a Playlist story")
    @Test
    public void shouldNotBeAbleToCreateAPlaylistWithExpiredToken() throws FileNotFoundException {
        String Invalid_Token = "12345";
        Playlist requestPlaylist=playlistBuilder(generateName(),generateDescription(),false);

        Response response = PlaylistApi.post(Invalid_Token, requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_401.getCode());
        assertContentType(response.contentType(),"application/json");

assertError(response.as(Error.class),StatusCode.CODE_401.getCode(),StatusCode.CODE_401.setMsg());



    }

}
