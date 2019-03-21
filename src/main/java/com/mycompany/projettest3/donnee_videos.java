/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projettest3;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import static com.mycompany.projettest3.DBConnect.getcurrenttime;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author elmehdi
 */
public class donnee_videos {
    
   
 public static void IDvideo (String apikey,ArrayList<String> video_ID) {

     try {
   
            YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("river-city-231709").build();

            String apiKey = apikey;        //"AIzaSyB3Eoqgx418ofT33ijlyDcj_UaZfy3ggvw"; 
            YouTube.Videos.List listVideosRequest = youtube.videos().list("statistics,snippet");
               
            System.out.println("nobmre de videos (boucle) = "+ video_ID.size());

                 for (int i=0;i<video_ID.size();i++)
     {                                                                                                        
            System.out.println("Video Num : "+ i);
            listVideosRequest.setId(video_ID.get(i)); // add list of video IDs here
            listVideosRequest.setKey(apiKey);            
            VideoListResponse listResponse = listVideosRequest.execute();           
            Video video = listResponse.getItems().get(0);
            
            //**********getSnippet*************            
       /*   BigInteger viewCount = video.getStatistics().getViewCount();
            BigInteger Likes     = video.getStatistics().getLikeCount();
            BigInteger DisLikes  = video.getStatistics().getDislikeCount();
            BigInteger Comments  = video.getStatistics().getCommentCount();
            String id            = video.getId();     
            String getkind            = video.getKind(); 
            BigInteger getFavoriteCount            = video.getStatistics().getFavoriteCount(); 
            //**********getSnippet*************
            String getCategoryId          = video.getSnippet().getCategoryId();
            String getChannelId           = video.getSnippet().getChannelId();
            String getChannelTitle        = video.getSnippet().getChannelTitle();
            String getDefaultLanguage     = video.getSnippet().getDefaultLanguage();*/
            String title                  = video.getSnippet().getTitle();
            DateTime getPublishedAt       = video.getSnippet().getPublishedAt();
            //String description            = video.getSnippet().getDescription();

            // *********affichage**********
         /* System.out.println("[View Count] " + viewCount);
            System.out.println("[Likes] " + Likes);
            System.out.println("[Dislikes] " + DisLikes);
            System.out.println("[Comments] " + Comments);
            System.out.println("[videoID] " + id);
            System.out.println("[getkind] " +getkind );
            System.out.println("[getFavoriteCount] " +getFavoriteCount );
            System.out.println("[getCategoryId] " +getCategoryId );
            System.out.println("[getChannelId] " +getChannelId);
            System.out.println("[getChannelTitle] " +getChannelTitle);
            System.out.println("[getDefaultLanguage] " +getDefaultLanguage );*/
            System.out.println("[videoTitle] " + title);
            System.out.println("[getPublishedAt] " + getPublishedAt);
            //System.out.println("[videoDescription] " + description);
        
     
     //Insert_mongodb(title ,String);
     
     }

        
 
// catch (GoogleJsonResponseException e) {
//            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
//                + e.getDetails().getMessage());
//        } 
}catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
           t.printStackTrace();
        }   
 }
 
 public void Insert_mongodb(String title ,String getChannelTitle) throws UnknownHostException 
   {      
   MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
   DB database = mongoClient.getDB("channel_test1");
   DBCollection collection = database.getCollection("collect_test1");   
   
   DBObject dc = new BasicDBObject();
         dc.put("platform","youtube");
         dc.put("videoTitle",title);
         dc.put("getChannelTitle",getChannelTitle);   
   collection.insert(dc);
     }   
}
