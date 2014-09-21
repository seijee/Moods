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
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import objects.MovieProduct;
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

    public static List<MovieProduct> itemSearch(String title, String actor, String keywords) {
        /*
         * Set up the signed requests helper 
         */
    /****************POROXY SETTINGS**********************
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

        LOG.info("String form example:");
        String queryString = "Service=AWSECommerceService&AssociateTag=taqa&" +
                            "Operation=ItemSearch&" +
                            "Keywords="+keywords+"&"+
                            "Title="+title+"&" +
                            "Actor="+actor+"&"+
                            "Condition=New&"+
                            "SearchIndex=Video&"+
                            "ResponseGroup=Images,ItemAttributes,EditorialReview,OfferSummary";
        requestUrl = helper.sign(queryString);
        LOG.info("Request is \"" + requestUrl + "\"");

        return parseXML(requestUrl);
    }

    /*
     * Utility function to fetch the response from the service and extract the
     * title from the XML.
     */
    private static List<MovieProduct> parseXML(String requestUrl) {
        ArrayList<MovieProduct> mdl = new ArrayList<MovieProduct>();
        MovieProduct md = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(requestUrl);
            NodeList nodeList = doc.getChildNodes();
            
            //recurse(nodeList.item(0).getChildNodes(), 0);
            nodeList = nodeList.item(0).getChildNodes().item(1).getChildNodes(); //All the Items in nodeList
            
            LOG.info ("totalResults = "+nodeList.item(1).getFirstChild().getNodeValue());
            String asin, detailPageURL, Description;
            int totalResults = Integer.parseInt(nodeList.item(1).getFirstChild().getNodeValue());
            System.out.println(totalResults);
            for (int i= 4; i<nodeList.getLength()/*(totalResults+4) && i<14*/; i++){
                md = new MovieProduct();
                fetchDetails(nodeList.item(i).getChildNodes(), md);
                mdl.add(md);
                System.out.println("/**********************/");
            }
        } catch (Exception e) {
        	LOG.error(" ",e);
            //throw new RuntimeException(e);
        }
        return mdl;
    }
    public static void fetchDetails (NodeList movie, MovieProduct md){
        for(int i=0; i<movie.getLength() ; i++){
            
            if (movie.item(i).getNodeName().equalsIgnoreCase("ItemLinks")){
                //ignoring item links
            }
            else if (movie.item(i).getNodeName().equalsIgnoreCase("ImageSets")){
                // Ignoring Image sets as of Now...
            }
            else if (movie.item(i).getNodeName().equalsIgnoreCase("title")){
                md.setTitle(movie.item(i).getFirstChild().getNodeValue());
                System.out.println(movie.item(i).getNodeName()+"="+movie.item(i).getFirstChild().getNodeValue());
            }
            else if (movie.item(i).getNodeName().equalsIgnoreCase("ASIN")){
                md.setAsin(movie.item(i).getFirstChild().getNodeValue());
                System.out.println(movie.item(i).getNodeName()+"="+movie.item(i).getFirstChild().getNodeValue());
            }
            else if (movie.item(i).getNodeName().equalsIgnoreCase("DetailPageURL")){
                md.setDetailPageUrl(movie.item(i).getFirstChild().getNodeValue());
                System.out.println(movie.item(i).getNodeName()+"="+movie.item(i).getFirstChild().getNodeValue());
            }
            else if (movie.item(i).getNodeName().equalsIgnoreCase("Binding")){
                md.setBinding(movie.item(i).getFirstChild().getNodeValue());
                System.out.println(movie.item(i).getNodeName()+"="+movie.item(i).getFirstChild().getNodeValue());
            }
            else if (movie.item(i).getNodeName().equalsIgnoreCase("ProductTypeName")){
                md.setProductType(movie.item(i).getFirstChild().getNodeValue());
                System.out.println(movie.item(i).getNodeName()+"="+movie.item(i).getFirstChild().getNodeValue());
            }
            else if (movie.item(i).getNodeName().equalsIgnoreCase("LargeImage")){
                md.setImagelink(movie.item(i).getFirstChild().getFirstChild().getNodeValue());
            }
            else if (movie.item(i).getNodeName().equalsIgnoreCase("OfferSummary")){
                NodeList offerSummary = movie.item(i).getChildNodes();
                
                for (int j=0; j<offerSummary.getLength(); j++){
                    if (offerSummary.item(j).getNodeName().equalsIgnoreCase("LowestNewPrice")){
                        md.setPrice(offerSummary.item(j).getChildNodes().item(2).getFirstChild().getNodeValue());
                    }
                }
                //md.setPrice(movie.item(i).getFirstChild().getNodeValue());
                System.out.println(movie.item(i).getNodeName()+"="+movie.item(i).getFirstChild().getNodeValue());
            }
            else if (movie.item(i).getNodeName().equalsIgnoreCase("ProductGroup")){
                md.setProductGroup(movie.item(i).getFirstChild().getNodeValue());
                System.out.println(movie.item(i).getNodeName()+"="+movie.item(i).getFirstChild().getNodeValue());
            }
            else if (movie.item(i).getNodeName().equalsIgnoreCase("EditorialReview")){
                String description = "NA";
                String source = "NA";
                NodeList Descriptions = movie.item(i).getChildNodes();
                for (int j=0; j<Descriptions.getLength();j++){
                    if (Descriptions.item(j).getNodeName().equalsIgnoreCase("source")){
                        //source = Descriptions.item(j).getNodeName()+": ";
                        source = Descriptions.item(j).getFirstChild().getNodeValue()+"\n";
                    }else
                    if (Descriptions.item(j).getNodeName().equalsIgnoreCase("content")){
                        description = Descriptions.item(j).getFirstChild().getNodeValue()+"\n----";
                    }
                    //System.out.println(Descriptions.item(j).getNodeName());
                }
                System.out.println(description);
                md.addNewDescription(source, description);
            }
            else{
                //System.out.print(movie.item(i).getNodeType()+") "+movie.item(i).getNodeName());
                //System.out.println(" = "+movie.item(i).getNodeValue());
                fetchDetails(movie.item(i).getChildNodes(), md);
            }
    	}
        //System.out.println("/*************************************/");
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
