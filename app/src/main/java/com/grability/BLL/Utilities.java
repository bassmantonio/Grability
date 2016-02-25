package com.grability.BLL;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import com.grability.DTO.ArtistDTO;
import com.grability.DTO.AuthorDTO;
import com.grability.DTO.CategoryDTO;
import com.grability.DTO.ContentTypeDTO;
import com.grability.DTO.EntryDTO;
import com.grability.DTO.FeedDTO;
import com.grability.DTO.IdDTO;
import com.grability.DTO.ImageDTO;
import com.grability.DTO.LinkDTO;
import com.grability.DTO.PriceDTO;
import com.grability.DTO.ReleaseDateDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Vector;


/**
 * This is a utilities class, help to many different works around application.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class Utilities {

    /**
     * Method used for build a FeedDTO object from Json Input String.
     * @param JSONInput: Json String.
     * @return FeedDTO class instance, with all mapped information.
     * @see FeedDTO
     */
    public static FeedDTO getFeedFromJSON(String JSONInput)
    {
        FeedDTO result = new FeedDTO();

        try{
            result = mappingJSON(getJsonObject(new JSONObject(JSONInput), "feed"));
        }catch(JSONException e){}
        return result;
    }

    /**
     * Method used for build a FeedDTO object from Json Input String.
     * @param data: Json String.
     * @return FeedDTO class instance, with all mapped information.
     * @see FeedDTO
     */
    private static FeedDTO mappingJSON(JSONObject data) throws JSONException {

        // TODO: refactorizar este método
        FeedDTO result = new FeedDTO();

        // Mapping Author
        //JSONObject JSONAux = data.getJSONObject("author");
        JSONObject JSONAux = getJsonObject(data, "author");

        if(JSONAux != null)
        {
            AuthorDTO newAuthor = new AuthorDTO();
            newAuthor.setName(getJsonDatafromObject(JSONAux, "name", "label"));
            newAuthor.setUri(getJsonDatafromObject(JSONAux, "uri", "label"));
            result.setAuthor(newAuthor);
        }

        // Mapping Updated
        JSONAux = getJsonObject(data, "updated");
        if(JSONAux != null)
        {
            result.setUpdated(getJsonData(JSONAux, "label"));
        }

        // Mapping Rights
        JSONAux = getJsonObject(data, "rights");
        if(JSONAux != null)
        {
            result.setRights(getJsonData(JSONAux, "label"));
        }

        // Mapping Title
        JSONAux = getJsonObject(data, "title");
        if(JSONAux != null)
        {
            result.setTitle(getJsonData(JSONAux, "label"));
        }

        // Mapping Icon
        JSONAux = getJsonObject(data, "icon");
        if(JSONAux != null)
        {
            result.setIcon(getJsonData(JSONAux, "label"));
        }

        // Mapping Id
        JSONAux = getJsonObject(data, "id");
        if(JSONAux != null)
        {
            result.setId(getJsonData(JSONAux, "label"));
        }

        // Mapping Links
        JSONArray jsonArray = getJsonArray(data, "limk");
        if(jsonArray != null)
        {
            for (int i=0; i<jsonArray.length(); i++)
            {
                LinkDTO newLink = new LinkDTO();
                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "attributes");
                if (JSONAux != null)
                {
                    newLink.setRel(getJsonData(JSONAux, "rel"));
                    newLink.setType(getJsonData(JSONAux, "type"));
                    newLink.setHRef(getJsonData(JSONAux, "href"));
                }
                result.getLinks().add(newLink);
            }
        }

        // Mapping Entries
        jsonArray = getJsonArray(data, "entry");
        if(jsonArray != null)
        {
            for (int i=0; i<jsonArray.length(); i++)
            {
                EntryDTO newEntry = new EntryDTO();

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "im:name");
                newEntry.setName(getJsonData(JSONAux, "label"));

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "summary");
                newEntry.setSummary(getJsonData(JSONAux, "label"));

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "rights");
                newEntry.setRights(getJsonData(JSONAux, "label"));

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "title");
                newEntry.setTitle(getJsonData(JSONAux, "label"));

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "im:price");
                newEntry.setPrice(new PriceDTO(
                        getJsonData(JSONAux, "label"),
                        getJsonDatafromObject(JSONAux, "attributes", "amount"),
                        getJsonDatafromObject(JSONAux, "attributes", "currency")
                ));

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "im:contentType");
                newEntry.setContentType(new ContentTypeDTO(
                        getJsonDatafromObject(JSONAux, "attributes", "term"),
                        getJsonDatafromObject(JSONAux, "attributes", "label")
                ));

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "link");
                newEntry.setLink(new LinkDTO(
                        getJsonDatafromObject(JSONAux, "attributes", "rel"),
                        getJsonDatafromObject(JSONAux, "attributes", "type"),
                        getJsonDatafromObject(JSONAux, "attributes", "href")
                ));

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "id");
                newEntry.setId(new IdDTO(
                        getJsonData(JSONAux, "label"),
                        getJsonDatafromObject(JSONAux, "attributes", "im:bundleId"),
                        getJsonDatafromObject(JSONAux, "attributes", "im:id")
                ));

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "im:artist");
                newEntry.setArtist(new ArtistDTO(
                        getJsonData(JSONAux, "label"),
                        getJsonDatafromObject(JSONAux, "attributes", "href")
                ));

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "category");
                newEntry.setCategory(new CategoryDTO(
                        getJsonDatafromObject(JSONAux, "attributes", "im:id"),
                        getJsonDatafromObject(JSONAux, "attributes", "term"),
                        getJsonDatafromObject(JSONAux, "attributes", "scheme"),
                        getJsonDatafromObject(JSONAux, "attributes", "label")
                ));

                JSONAux = getJsonObject(jsonArray.getJSONObject(i), "im:releaseDate");
                newEntry.setReleaseDate(new ReleaseDateDTO(
                        getJsonData(JSONAux, "label"),
                        getJsonDatafromObject(JSONAux, "attributes", "label")
                ));

                JSONArray arrayAux = getJsonArray(jsonArray.getJSONObject(i), "im:image");
                if (arrayAux != null)
                {
                    for (int j=0;j<arrayAux.length();j++)
                    {
                        JSONAux = arrayAux.getJSONObject(j);
                        newEntry.getImages().add(
                                new ImageDTO(
                                        getJsonData(JSONAux, "label"),
                                        getJsonDatafromObject(JSONAux, "attributes", "height")
                                )
                        );
                    }
                }

                result.getEntries().add(newEntry);
            }
        }

        return result;
    }

    /**
     * Method used for search categories in all feed data.
     * @param DataFeed: Data input.
     * @return String[] string array, used for build category list adapter.
     */
    public static String[] getCategoriesFromFeed(FeedDTO DataFeed)
    {
        String resultConcat = "";
        for (int i=0;i< DataFeed.getEntries().size();i++)
        {
            EntryDTO entryObj = DataFeed.getEntries().elementAt(i);
            if (!resultConcat.contains(entryObj.getCategory().getLabel())) {
                resultConcat += entryObj.getCategory().getLabel();
                resultConcat += ",";
            }
        }
        String [] result = resultConcat.split(",");

        return result;
    }

    /**
     * Method used for search apps in all feed data for one selected category.
     * @param DataFeed: Data input.
     * @param sCategory: selected category.
     * @return Vector<EntryDTO> EntryDTO vector, with all entries related.
     * @see Vector
     * @see EntryDTO
     */
    public static Vector<EntryDTO> getAppsbyCategory(FeedDTO DataFeed, String sCategory)
    {
        Vector<EntryDTO> result = new Vector<EntryDTO>();
        for (int i=0;i< DataFeed.getEntries().size();i++)
        {
            EntryDTO entryObj = DataFeed.getEntries().elementAt(i);
            if (entryObj.getCategory().getLabel().equals(sCategory)) {
                result.add(entryObj);
            }
        }
        return result;
    }

    /**
     * Method used for search apps in all feed data for one selected category.
     * @param DataFeed: Data input.
     * @param sId: selected application Id.
     * @return EntryDTO contains application information gotten by an Id.
     * @see EntryDTO
     */
    public static EntryDTO getAppbyId(FeedDTO DataFeed, String sId)
    {
        for (int i=0;i< DataFeed.getEntries().size();i++)
        {
            EntryDTO entryObj = DataFeed.getEntries().elementAt(i);
            if (entryObj.getId().getId().equals(sId)) {
                return entryObj;
            }
        }
        return null;
    }

    public static JSONObject getJsonObject(JSONObject input,String inputName) {
        JSONObject result = new JSONObject();
        try{

            if(input.has(inputName))
            {
                result = input.getJSONObject(inputName);
            }
        }catch (JSONException ex) {
            result = null;
        }
        return result;
    }

    public static JSONArray getJsonArray(JSONObject input,String inputName) {
        JSONArray result = new JSONArray();
        try{

            if(input.has(inputName))
            {
                result = input.getJSONArray(inputName);
            }
        }catch (JSONException ex) {
            result = null;
        }
        return result;
    }

    public static String getJsonData(JSONObject input,String inputName) {
        String result = "";
        try{

            if(input.has(inputName))
            {
                result = input.getString(inputName);
            }
        }catch (JSONException ex) {
            result = null;
        }
        return result;
    }

    public static String getJsonDatafromObject(JSONObject input, String objectName, String dataName) {
        String result = "";
        try{

            if(input.has(objectName))
            {
                JSONObject aux = input.getJSONObject(objectName);
                result = aux.optString(dataName);
            }
        }catch (JSONException ex) {
            result = null;
        }
        return result;
    }

    /**
     * Check if device has internet connection.
     */
    public static boolean isOnline() {

        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    /**
     * Show one Toast, with a input message.
     */
    public static void showToast(Activity context, String Message)
    {
        Toast.makeText(context, Message,
                Toast.LENGTH_LONG).show();
    }

    public static void startAnimationBeforeIntent(int animationId, final View view, final Intent newActivity)
    {
        Animation defaultAnimation = AnimationUtils.loadAnimation(view.getContext(), animationId);
        view.startAnimation(defaultAnimation);

        defaultAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.getContext().startActivity(newActivity);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    
    public static void startAnimationSet(int animationId, final View view)
    {
        AnimatorSet animationSet = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(),
                animationId);
        animationSet.setTarget(view);
        animationSet.start();
    }

    public static void startAnimation(int animationId, final View view)
    {
        Animation defaultAnimation = AnimationUtils.loadAnimation(view.getContext(), animationId);
        view.startAnimation(defaultAnimation);
    }

}
