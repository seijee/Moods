/**********************************************************************************************
 * Copyright 2009 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file 
 * except in compliance with the License. A copy of the License is located at
 *
 *       http://aws.amazon.com/apache2.0/
 *
 * or in the "LICENSE.txt" file accompanying this file. This file is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under the License. 
 *
 * ********************************************************************************************
 *
 *  Amazon Product Advertising API
 *  Signed Requests Sample Code
 *
 *  API Version: 2009-03-31
 *
 */

package paapi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import objects.MovieDetails;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PaapiCall {
    private static Logger LOG = Logger.getLogger(ItemLookupSample.class);
    private static final String AWS_ACCESS_KEY_ID = "AKIAJDVM2PUZ2TV4ENLQ";

    private static final String AWS_SECRET_KEY = "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";

    private static final String ENDPOINT = "ecs.amazonaws.com";

    public static List<MovieDetails> itemSearch(String title, String actor, String keywords) {
        /*
         * Set up the signed requests helper 
         */
    //****************POROXY SETTINGS***********************/
        String host = "172.16.0.87";
        String port = "8080";
        System.out.println("Using proxy: " + host + ":" + port);
        System.setProperty("http.proxyHost", host);
        System.setProperty("http.proxyPort", port);
        System.setProperty("http.proxySet", "true");
        System.setProperty("http.nonProxyHosts", "localhost|127.0.0.1");
    /******************END OF PROXY SETTINGS****************************/
    
        BasicConfigurator.configure();
        SignedRequestsHelper helper;
        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            LOG.error(" ",e);
            return null;
        }
        
        String requestUrl = null;

        //<editor-fold defaultstate="collapsed" desc="comment">
// The helper can sign requests in two forms - map form and string form */
        
        
        //Here is an example in map form, where the request parameters are stored in a map.
        /*
        LOG.info("Map form example:");
        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2011-08-01");
        params.put("Operation", "ItemLookup");
        params.put("ItemId", ITEM_ID);
        params.put("ResponseGroup", "Small");
        params.put("AssociateTag", "taga");
        */
        
        /*requestUrl = helper.sign(params);
        LOG.info("Signed Request is \"" + requestUrl + "\"");
        
        title = fetchTitle(requestUrl);
        LOG.info("Signed Title is \"" + title + "\"");
        LOG.info();*/
        
        // Here is an example with string form, where the requests parameters have already been concatenated
        // into a query string.
//</editor-fold>
//        title = "";
//        actor = "";
//        keywords="Paan Singh Tomar";
        LOG.info("String form example:");
        String queryString = "Service=AWSECommerceService&AssociateTag=taqa&" +
                            "Operation=ItemSearch&" +
                            "Keywords="+keywords+"&"+
                            "Title="+title+"&" +
                            "Actor="+actor+"&"+
                            "SearchIndex=Video&"+
                            "ResponseGroup=EditorialReview,Images,ItemAttributes";
        requestUrl = helper.sign(queryString);
        LOG.info("Request is \"" + requestUrl + "\"");

        return parseXML(requestUrl);
    }

    /*
     * Utility function to fetch the response from the service and extract the
     * title from the XML.
     */
    private static List<MovieDetails> parseXML(String requestUrl) {
        ArrayList<MovieDetails> mdl = new ArrayList<MovieDetails>();
        MovieDetails md = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);
            NodeList nodeList = doc.getChildNodes();
            recurse(nodeList.item(0).getChildNodes(), 0);
            nodeList = nodeList.item(0).getChildNodes().item(1).getChildNodes(); //All the Items in nodeList
            LOG.info ("totalResults = "+nodeList.item(1).getFirstChild().getNodeValue());
            String asin, detailPageURL;
            int totalResults = Integer.parseInt(nodeList.item(1).getFirstChild().getNodeValue());
            for (int i= 4; i<totalResults && i<14; i++){
                md = new MovieDetails();
                asin = nodeList.item(i).getChildNodes().item(0).getFirstChild().getNodeValue();
                detailPageURL = (nodeList.item(i).getChildNodes().item(1).getFirstChild().getNodeValue());
                System.out.println("ASIN : "+asin);
                System.out.println("URL  : "+detailPageURL);
                System.out.println(nodeList.item(i).getChildNodes().item(3).getNodeName());
                NodeList attributes = nodeList.item(i).getChildNodes().item(7).getChildNodes();
                for (int j = 0; j<attributes.getLength(); j++){
                    if (attributes.item(j).getNodeName().equalsIgnoreCase("title")){
                        md.setTitle(attributes.item(j).getFirstChild().getNodeValue());
                    }
                }
                md.setAsin(asin);
                md.setDetailPage(detailPageURL);
                mdl.add(md);
            }
        } catch (Exception e) {
        	LOG.error(" ",e);
            //throw new RuntimeException(e);
        }
        return mdl;
    }
    public static void recurse(NodeList root, int depth){
    	for(int i=0; i<root.getLength() ; i++){
            String s = "";
            for (int j=0; j<depth; j++){s+="\t";}
                if (root.item(i).getNodeName()=="#text"){
                    s+= "#"+root.item(i).getNodeValue();
                }else{
                    s+= root.item(i).getNodeName();
                }
    		//LOG.info(s);
                System.out.println(s);
    		recurse(root.item(i).getChildNodes(), depth+1);
    	}	
    }
}
