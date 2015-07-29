package com.kokhouser.napod.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hkok on 7/28/2015.
 * Using code generated from http://www.jsonschema2pojo.org/
 */

public class Astropic {
    private String url;
    @SerializedName("media_type")
    private String mediaType;
    private String explanation;
    private Object concepts;
    private String title;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The mediaType
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     *
     * @param mediaType
     * The media_type
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     *
     * @return
     * The explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     *
     * @param explanation
     * The explanation
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     *
     * @return
     * The concepts
     */
    public Object getConcepts() {
        return concepts;
    }

    /**
     *
     * @param concepts
     * The concepts
     */
    public void setConcepts(Object concepts) {
        this.concepts = concepts;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
