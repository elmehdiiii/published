/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projettest3;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.DateTime;
//import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author elmehdi
 */
public class IDchannel {
   
        public static ArrayList<String> IDvideo (String apikey, String channelID) {
               ArrayList<String> IDV = new ArrayList<>();
               
               //ArrayList<String> channel_ID =new ArrayList<>();
               String channel_ID;
               channel_ID = "UCYLNGLIzMhRTi6ZOLjAPSmw";  // get_channel_id_mysql.getidchannel();
                              
               try {
           
            YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) throws IOException {
                }
                
            }).setApplicationName("youtube-cmdline-search-sample").build();
            
            YouTube.Search.List search = youtube.search().list("id,snippet");                        
            search.setChannelId(channel_ID); //.get(7));// Restrict the search results to only include videos. See:// https://developers.google.com/youtube/v3/docs/search/list#type            
            search.setMaxResults(50L);                        
            DateTime  apres =  new DateTime("2019-01-03T00:00:00Z");            
            search.setPublishedAfter(apres);                                 
            search.setKey(apikey);  //"AIzaSyCnaGpoyXQDlVM-Fa-71pGkpgfhAj4lrAs");                                 
            search.setType("video");                        
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
                        // Call the API and print results.           
            ///////////****** Youtube v3 api PublishedAfter search
            SearchListResponse searchResponse = search.execute();         
            System.out.println("search.execute = " +searchResponse.size());
            
            List<SearchResult> searchResultList = searchResponse.getItems();
            System.out.println("nombre de videos = " +searchResultList.size());
           
             //if (searchResultList != null) { System.out.println(" There aren't any resultsx   for your query."); }   
        //if (!searchResultList.iterator().hasNext()) {          
        for(int i=0 ;i<15;i++){
                                    SearchResult singleVideo = searchResultList.get(i);
                                    ResourceId rId = singleVideo.getId();

                                // item will not contain a video ID.
                                if (rId.getKind().equals("youtube#video")) {
                                      Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
//                                    System.out.println(" getTitle = " + singleVideo.getSnippet().getTitle());                                    
//                                    System.out.println(" getPublishedAt = " + singleVideo.getSnippet().getPublishedAt());
//                                    System.out.println(" getPublishedAt = " + singleVideo.getSnippet().getChannelTitle());
//                                    System.out.println(" titre = " + singleVideo.getSnippet().getChannelId());
//                                    System.out.println(" getPublishedAt = " + getPublishedAt);
//                                    System.out.println("\n-------------------------------------------------------------\n");
//                                    
                                    IDV.add(rId.getVideoId());
                                    // System.out.println("\n-------------------------------------------------------------\n");
                                     
                                }
                               
                               }                              
        }catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                  + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
       
    return IDV ;
    
  }
}

