/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generic;

import Entity.Fiscalia;
import java.util.List;

/**
 *
 * @author dannyalvizures
 */
public class JsonGenerator {
    
    public final static String resultCode = "\"resultCode\":";
    public final static String data = "\"data\":";
    public final static String dataObject = "\"dataObject\":";
    public final static String SUCCESS_CODE = "0";
    public final static String SERVER_ERROR_CODE = "1";
    public final static String DB_ERROR_CODE = "2";
    public final static String PARSER_ERROR_CODE = "3";
    
    public static String generateListJson(List<Fiscalia> lista){
        boolean first = true;
        String json = "";
        for(Object o: lista){
            Object[] tmp = (Object[]) o;
            Fiscalia f = new Fiscalia((int)tmp[0],(String)tmp[1],(String)tmp[2],(String)tmp[3]);
            if(!first){
                json += ",";
            }
            json += f.getJson();
            first = false;
        }
        return "{"+ resultCode + SUCCESS_CODE+ "," +data +"["+json+"]" +"}";
    }
    
    public static String generateOneJson(Fiscalia fiscalia){
        String json = "";
        
        
        return "{"+ resultCode + SUCCESS_CODE+ "," +data +fiscalia.getJson() +"}";
    }
    
    public static String jsonServerError(){
        return "{"+ resultCode + SERVER_ERROR_CODE +"}";
    }
    
    public static String jsonDbError(){
        return "{"+ resultCode + DB_ERROR_CODE +"}";
    }
    
    public static String jsonParserError(){
        return "{"+ resultCode + PARSER_ERROR_CODE +"}";
    }
    
    public static String jsonSuccess(){
        return "{"+ resultCode + SUCCESS_CODE +"}";
    }
    
    public static String jsonSuccessOne(){
        return "{"+ resultCode + SUCCESS_CODE + "," +data +"{}" +"}";
    }
    
    public static String jsonSuccessList(){
        return "{"+ resultCode + SUCCESS_CODE + "," +data +"[]" +"}";
    }

}
