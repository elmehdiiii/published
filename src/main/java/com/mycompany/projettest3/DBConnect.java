
        /*
        * To change this license header, choose License Headers in Project Properties.
        * To change this template file, choose Tools | Templates
        * and open the template in the editor.
        */
        package com.mycompany.projettest3;

/*      import com.google.api.client.googleapis.json.GoogleJsonResponseException;
        import com.google.api.client.http.HttpRequest;
        import com.google.api.client.http.HttpRequestInitializer;
        import com.google.api.client.util.DateTime;
        import com.google.api.services.youtube.YouTube;
        import com.google.api.services.youtube.model.Video;
        import com.google.api.services.youtube.model.VideoListResponse;  */
import com.google.api.client.util.Throwables;
        import com.mongodb.BasicDBObject;
        import com.mongodb.DB;
        import com.mongodb.DBCollection;
        import com.mongodb.DBObject;
        import com.mongodb.MongoClient;
        import com.mongodb.MongoClientURI;
  
        import java.net.UnknownHostException;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;

public class DBConnect {

    

        public static void main(String Args[]) throws ParseException, UnknownHostException{

        Date date1 = new Date();
        Date date2;  
        ArrayList<String> video_ID =new ArrayList<>();  
        String deb = getcurrenttime();
        System.out.println("started_at = "+deb);
        Long v1 = System.currentTimeMillis();
        System.out.println("***************");     


        ArrayList<String> IdChannel = new ArrayList<>();
        //IdChannel = get_channel_id_mysql.getidchannel();
        video_ID = IDchannel.IDvideo("AIzaSyCL53rWE4d0zefmelJyuHtgUbTdSzfn3Mc","UCyiIhe5QvKjKNUQCBjrbloQ");   //UC9hJ5XcjHXYjwDOqrlQUuow");
        donnee_videos.IDvideo("AIzaSyCL53rWE4d0zefmelJyuHtgUbTdSzfn3Mc", video_ID);


        System.out.println("***************");
        String fin = getcurrenttime();
        System.out.println("finished_at = "+fin);
        Long v2 = System.currentTimeMillis();
        System.out.println("la durée d'execution = "+ ((double)(v2-v1))/60000+" min ");
        System.out.println("la durée d'execution = "+ ((double)(v2-v1))/1000+" seconds ");     

        //Insert_mongo(deb ,fin,v1,v2);
        }     


        
        
        
        
        
        
        
        public static String getcurrenttime(){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd  hh:mm:ss");
        return ft.format(dNow);  }


        public static void Insert_mongo(String deb ,String fin,Long v1,Long v2) throws UnknownHostException { 
            try{
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB database = mongoClient.getDB("channel_test1");
        DBCollection collection = database.getCollection("collect_test1");   
        DBObject dc = new BasicDBObject();
        dc.put("platform","youtube");
        dc.put("date_debut",deb);
        dc.put("date_fin",fin);
        dc.put("duree_execution",(double)(v2-v1)/60000);  
        collection.insert(dc);
        }catch(Exception ex)
            
        {throw Throwables.propagate(ex);}

        }}
