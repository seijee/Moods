/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import factory.Conn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import objects.ImdbData;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author seijee
 */
public class ImdbDBController {
    private static final Logger LOG = Logger.getLogger(ImdbDBController.class);
    
    private static List<ImdbData> aboveNineOld=null, aboveEightOld=null, belowEightOld=null;
    private static List<ImdbData> cachedMovies = null;
    private static HashMap<String, List<ImdbData>> moodBuckets;
    
    @Deprecated
    public static void retrieveData (){
        Session s = Conn.getSf().openSession();
        s.beginTransaction();
        try{
            Query q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGEMOOD).setFloat("minRating", 9).setFloat("maxRating", 11).setString("mood", "test");
            aboveNineOld = q.list();
            q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGEMOOD).setFloat("minRating", 8).setFloat("maxRating", 9).setString("mood","test");
            aboveEightOld = q.list();
            q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGEMOOD).setFloat("minRating", 0).setFloat("maxRating", 8).setString("mood", "test");
            belowEightOld = q.list();
            s.getTransaction().commit();  
        }catch(Throwable ex){
            LOG.error(" ", ex);
        }
        finally{
            s.close();
        }
    }
    // This procedure will be called only for refreshing the cache
    @Deprecated
    public static void UpdateMovieCache (){
        retrieveData();
        cachedMovies =  new ArrayList<ImdbData>();
        ListIterator<ImdbData> restIterator = belowEightOld.listIterator();
        ListIterator<ImdbData> ninersIterator = aboveNineOld.listIterator();
        ListIterator<ImdbData> eightersIterator = aboveEightOld.listIterator();
        while (belowEightOld!=null || aboveEightOld!=null || aboveNineOld!=null ){
            if (eightersIterator.hasNext()){cachedMovies.add(eightersIterator.next());}
            else {aboveEightOld=null;}
            if (restIterator.hasNext()){ cachedMovies.add(restIterator.next());}
            if (eightersIterator.hasNext()){cachedMovies.add(eightersIterator.next());}
            if (ninersIterator.hasNext()){cachedMovies.add(ninersIterator.next());}
            else{aboveNineOld = null;}
            if (restIterator.hasNext()){ cachedMovies.add(restIterator.next());}
            if (eightersIterator.hasNext()){cachedMovies.add(eightersIterator.next());}
            if (restIterator.hasNext()){ cachedMovies.add(restIterator.next());}
            else{belowEightOld = null;}
        }
    }
   
    @Deprecated
    private static void retrieveMovieData(String mood, List<ImdbData> aboveNine, List<ImdbData> aboveEight, List<ImdbData> belowEight) {
        Session s = Conn.getSf().openSession();
        s.beginTransaction();
        try{
            Query q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGEMOOD).setFloat("minRating", 9).setFloat("maxRating", 11).setString("mood", mood);
            aboveNine = q.list();
            q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGEMOOD).setFloat("minRating", 8).setFloat("maxRating", 9).setString("mood",mood);
            aboveEight = q.list();
            q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGEMOOD).setFloat("minRating", 0).setFloat("maxRating", 8).setString("mood", mood);
            belowEight = q.list();
            s.getTransaction().commit();
            LOG.error("deprecated function called");
        }catch(Throwable ex){
            LOG.error(" ", ex);
        }
        finally{
            s.close();
        }
    }
    
    
    public static void updateBucket (String mood){
        moodBuckets.putIfAbsent(mood, new ArrayList<ImdbData>());
        moodBuckets.get(mood).clear();
        List<ImdbData> aboveNine=new ArrayList<ImdbData>(),
                       aboveEight=new ArrayList<ImdbData>(),
                       belowEight=new ArrayList<ImdbData>();
        //retrieving data
        
        Session s = Conn.getSf().openSession();
        s.beginTransaction();
        try{
            Query q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGEMOOD).setFloat("minRating", 9).setFloat("maxRating", 11).setString("mood", mood);
            aboveNine = q.list();
            q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGEMOOD).setFloat("minRating", 8).setFloat("maxRating", 9).setString("mood",mood);
            aboveEight = q.list();
            q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGEMOOD).setFloat("minRating", 0).setFloat("maxRating", 8).setString("mood", mood);
            belowEight = q.list();
            s.getTransaction().commit();
        }catch(Throwable ex){
            LOG.error(" ", ex);
        }
        finally{
            s.close();
        }
        
        ListIterator<ImdbData> restIterator = belowEight.listIterator();
        ListIterator<ImdbData> ninersIterator = aboveNine.listIterator();
        ListIterator<ImdbData> eightersIterator = aboveEight.listIterator();
        while (belowEight!=null || aboveEight!=null || aboveNine!=null ){
            if (eightersIterator.hasNext()){moodBuckets.get(mood).add(eightersIterator.next());}
            else {aboveEight=null;}
            if (restIterator.hasNext()){ moodBuckets.get(mood).add(restIterator.next());}
            if (eightersIterator.hasNext()){moodBuckets.get(mood).add(eightersIterator.next());}
            if (ninersIterator.hasNext()){moodBuckets.get(mood).add(ninersIterator.next());}
            else{aboveNine = null;}
            if (restIterator.hasNext()){ moodBuckets.get(mood).add(restIterator.next());}
            if (eightersIterator.hasNext()){moodBuckets.get(mood).add(eightersIterator.next());}
            if (restIterator.hasNext()){ moodBuckets.get(mood).add(restIterator.next());}
            else{belowEight = null;}
        }
    }
    
    public static List<ImdbData> getCache(String mood) {
        if (moodBuckets==null){moodBuckets = new HashMap<String, List<ImdbData>>();}
        moodBuckets.putIfAbsent(mood, new ArrayList<ImdbData>());
        if (moodBuckets.get(mood).isEmpty()){
           updateBucket(mood);
        }
        LOG.info(moodBuckets);
        return moodBuckets.get(mood);
    }
    
    @Deprecated
    public static List<ImdbData> getCache() {
        if (cachedMovies==null || cachedMovies.isEmpty()) UpdateMovieCache();
        return cachedMovies;
    }


}


