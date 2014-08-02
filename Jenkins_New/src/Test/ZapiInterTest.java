package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
import java.net.HttpURLConnection;
import java.net.URL;
 
import java.util.ArrayList;
 



import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
/** Helper class for calling ZAPI */
public class ZapiInterTest {
  
  /** Status IDs */
  private static final int ZAPI_STATUS_PASS = 1;
  private static final int ZAPI_STATUS_FAIL = 2;
  private static final int ZAPI_STATUS_WIP = 3;
  private static final int ZAPI_STATUS_BLOCKED = 4;
  
  /** URLS */
  private static final String BASE_URL= "http://192.168.11.41:8080";
  private static final String ZAPI_URL = BASE_URL + "/rest/zapi/latest/";
  
  /** JIRA credentials */
  private static final String CREDENTIALS = "sabyasachi.rout:S@1234";
  
  //================================================================================
  // ZAPI methods
  //================================================================================
    
  /**
   * Returns the ID for the specified Version in the specified Project
   * 
   * @param versionName
   * @param projectId
   */
   public static String getVersionID(final String versionName, final String projectId) {
     try {
       //Get list of versions on the specified project
       final JSONObject obj = (JSONObject) get(ZAPI_URL + "util/versionBoard-list?projectId=" + projectId);
       if(null != obj) {
          final JSONArray versionOptions = (JSONArray) obj.get("versionOptions");
          //Iterate over versions
          for(int i = 0; i < versionOptions.length(); i++) {
            final JSONObject obj2 = versionOptions.getJSONObject(i);
            //If label matches specifed version name
            if(obj2.getString("label").equals(versionName)) {
             //Return the ID for this version
             return obj2.getString("value");
           }
          }
       }
     } catch (final JSONException e) {
       e.printStackTrace();
     }
     return null;
   }
   
   /**
    * Updates the specified test execution
    * @param executionId
    * @param status - e.g. ZAPI_STATUS_PASS
    * @param comment
 * @throws IOException 
    */
    public static void updateTestExecution(final String executionId, final int status, final String comment) throws IOException {
      //Construct JSON object
      final JSONObject obj = new JSONObject();
      try {
        obj.put("status", String.valueOf(status));
        obj.put("comment", comment);
      } catch (final JSONException e) {
        e.printStackTrace();
      }
      //Send PUT request
      put(ZAPI_URL + "execution/" + executionId + "/execute", obj);
      
    }
    
    /**
     * Adds attachement to an execution
     * @param file - the file to attach
     * @param executionId
     */
     public static void addAttachment(final File file, final String executionId) {
       try {
         final HttpPost httpPost = new HttpPost(ZAPI_URL + "attachment?entityId=" + executionId + "&entityType=EXECUTION");
         final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
         httpPost.setHeader("X-Atlassian-Token", "nocheck");
         httpPost.setHeader("Authorization", "Basic " + encoding);
         
         final MultipartEntity mpEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
         final ContentBody cbFile = new FileBody(file);
         mpEntity.addPart("file", cbFile);
         httpPost.setEntity(mpEntity);
         final HttpResponse response = new DefaultHttpClient().execute(httpPost);
         final HttpEntity resEntity = response.getEntity();
         
         if(null != resEntity) {
           EntityUtils.consume(resEntity);
         }
         
       } catch (final ClientProtocolException e) {
         e.printStackTrace();
       } catch (final IOException e) {
         e.printStackTrace();
       }
     }
   
   /**
    * Deletes all of the attachments on the specified execution
    * @param executionId
 * @throws IOException 
    */
    public static void deleteAttachments(final String executionId) throws IOException {
      final ArrayList<String> fileIds = new ArrayList<String>();
      // Note the IDs for the files currently attached to the execution
      try {
        final JSONObject obj = (JSONObject) get(ZAPI_URL + "attachment/attachmentsByEntity?entityId=" + executionId + "&entityType=EXECUTION");
        if(null != obj) {
          final JSONArray data = (JSONArray) obj.get("data");
          for(int i = 0; i < data.length(); i++) {
            final JSONObject fileData =  data.getJSONObject(i);
            fileIds.add(fileData.getString("fileId"));
          }
        }
      } catch (final JSONException e) {
        e.printStackTrace();
      }
      
      //Iterate over attachments
      for(final String fileId : fileIds) {
        delete(ZAPI_URL + "attachment/" + fileId);
      }
    }
    
   //================================================================================
   // HTTP request methods
   //================================================================================
   
   /**
    * Send GET request to the specified URL
    * @param url
    */
    private static Object get(final String url)
    {
      try {
        final HttpURLConnection httpCon = httpCon(url, "GET");
        
        final BufferedReader rd = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        final StringBuffer result = new StringBuffer();
        String line = "";
        while(null != (line = rd.readLine())) {
          result.append(line);
        }
        
        final String resultString = result.toString();
        
        if(resultString.startsWith("{")) {
          return new JSONObject(resultString);
        } else if (resultString.startsWith("[")) {
          return new JSONArray(resultString);
        }
      } catch (final IOException e) {
        e.printStackTrace();
      } catch (final JSONException e) {
        e.printStackTrace();
      }
      
      return null;
    }
   
   /**
    * Send a request with JSON content with the specified method
    * @param url - the URL to send the request to
    * @param obj - the JSON content to send 
    * @param method - e.g. PUT
    */
    private static void sendRequest(final String url, final JSONObject obj, final String method)
            throws IOException {
        final HttpURLConnection httpCon = httpCon(url, method);
 
        if (null != obj) {
            final OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
            out.write(obj.toString());
            out.close();
        }
 
        final BufferedReader rd =
                new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        final StringBuffer result = new StringBuffer();
        String line = "";
        while (null != (line = rd.readLine())) {
            result.append(line);
        }
    }
    
    /**
     * Send PUT request to the specified URL
     * @param url - the URL to send the request to
     * @param obj - the JSON content to send 
     */
    private static void put(final String url, final JSONObject obj) throws IOException {
        sendRequest(url, obj, "PUT");
    }
     
    /**
     * Send POST request to the specified URL
     * @param url - the URL to send the request to
     * @param obj - the JSON content to send 
     */
    private static void post(final String url, final JSONObject obj) throws IOException {
        sendRequest(url, obj, "POST");
    }
     
     /**
     * Send DELETE request to the specified URL
     * @param url - the URL to send the request to
     * @throws IOException 
     */
     private static void delete(final String url) throws IOException {
       sendRequest(url, null, "DELETE");
     }
     
     /**
      * Return a HttpURLConnection object for the specified URL and request method
      * @param url
      * @param method - e.g. GET
      */
      private static HttpURLConnection httpCon(final String url, final String method) throws IOException{
        final HttpURLConnection httpCon = (HttpURLConnection) new URL(url).openConnection();
        
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod(method);
        
        final String encoding = new Base64().encodeToString(CREDENTIALS.getBytes());
        httpCon.setRequestProperty("Authorization", "Basic " + encoding);
        
        httpCon.setRequestProperty("Content-type", "application/json");
        
        return httpCon;
      }
}