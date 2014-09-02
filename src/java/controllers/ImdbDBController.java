/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import factory.Conn;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import objects.ImdbData;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author seijee
 */
public class ImdbDBController {
    private static List<ImdbData> niners=null, eighters=null, rest=null;
    private static List<ImdbData> cachedMovies = null;
    public static void retrieveData (){
        Session s = Conn.getSf().openSession();
        s.beginTransaction();
        try{
            Query q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGE).setFloat("minRating", 9).setFloat("maxRating", 11);
            niners = q.list();
            q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGE).setFloat("minRating", 8).setFloat("maxRating", 9);
            eighters = q.list();
            q = s.getNamedQuery(ImdbData.IMDB_FIND_BY_RATING_RANGE).setFloat("minRating", 0).setFloat("maxRating", 8);
            rest = q.list();
        }
        finally{
            s.getTransaction().commit();  
        }
    }
    
    // This procedure will be called only for refreshing the cache
    public static void UpdateMovieCache (){
        retrieveData();
        cachedMovies =  new ArrayList<ImdbData>();
        ListIterator<ImdbData> restIterator = rest.listIterator();
        ListIterator<ImdbData> ninersIterator = niners.listIterator();
        ListIterator<ImdbData> eightersIterator = eighters.listIterator();
        while (rest!=null || eighters!=null || niners!=null ){
            if (eightersIterator.hasNext()){cachedMovies.add(eightersIterator.next());}
            else {eighters=null;}
            if (restIterator.hasNext()){ cachedMovies.add(restIterator.next());}
            if (eightersIterator.hasNext()){cachedMovies.add(eightersIterator.next());}
            if (ninersIterator.hasNext()){cachedMovies.add(ninersIterator.next());}
            else{niners = null;}
            if (restIterator.hasNext()){ cachedMovies.add(restIterator.next());}
            if (eightersIterator.hasNext()){cachedMovies.add(eightersIterator.next());}
            if (restIterator.hasNext()){ cachedMovies.add(restIterator.next());}
            else{rest = null;}
        }
    }
    
    @Deprecated
    public static List<ImdbData> GetAllMovies (int offset, int limit, String order){
        List<ImdbData> list = null;
        Session s = Conn.getSf().openSession();
        s.beginTransaction();
        try{
            Query q = s.getNamedQuery("Imdb.findAll").setFirstResult(offset).setMaxResults(limit).setString("order", order);
            list = q.list();
        }
        finally{
            s.getTransaction().commit();  
            return list;
        }
    }

    public static List<ImdbData> getCache() {
        if (cachedMovies==null) UpdateMovieCache();
        return cachedMovies;
    }
}


