package com.spotify.oauth2.api.applicationapi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.io.FileNotFoundException;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.api.TokenManager.getToken;

import static io.restassured.RestAssured.*;


public class PlaylistApi {
   // static String Access_Token = "BQD7RssXKvWKz4g4RRz4hgs92d3VCJ3Ail02mGcGZpPenwK7cc8KAsPBqo2ZHaphsazn3bbES04fn5KErQvnxUnS5UVrjENLS9vPnYVeR3ZgXzOdULcwLEqumvjB4sNhhf6MRU7cqIoZijJUjrvSQit1h_9c1XLPr0g1LMEG2UghHZgBzg5x8xfRcMixU6p7ts6I9LKcRyHBrbLxjbyrF7rvCmN8dVgrEvbWcNEFGaELWidzyW09vBJVxj6QFrtOvcIXQ3OLIY__eROQ";
    public static Response post(Playlist requestPlaylist) throws FileNotFoundException {
        return RestResource.post(USERS+ConfigLoader.getInstance().getUser()+PLAYLISTS,getToken(),requestPlaylist);



    }
    public static Response post(String tokenId,Playlist requestPlaylist) throws FileNotFoundException {
        return RestResource.post(USERS+ ConfigLoader.getInstance().getUser() +PLAYLISTS,tokenId,requestPlaylist);


    }
    public static Response get(String PlaylistId){
        return RestResource.get(PLAYLISTS+ "/" +PlaylistId,getToken());

    }
    public static Response update(String PlaylistId,Playlist requestPlaylist){
        return RestResource.update(PLAYLISTS+"/" +PlaylistId,getToken(),requestPlaylist);


    }
}
