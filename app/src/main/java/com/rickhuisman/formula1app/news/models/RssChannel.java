package com.rickhuisman.formula1app.news.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.List;

@Root(name = "channel", strict = false)
public class RssChannel {

    @Element(name = "title")
    private String title;

    @Element(name = "description")
    private String description;

    @Path("link")
    @Text(required=false)
    private String link;

    @Element(name = "language")
    private String language;

    @Element(name = "copyright")
    private String copyright;

    @Element(name = "ttl")
    private String ttl;

    @Element(name = "lastBuildDate")
    private String lastBuildDate;

    @ElementList(name = "item", inline = true)
    private List<RssItem> itemList;

    public RssChannel() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public List<RssItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<RssItem> itemList) {
        this.itemList = itemList;
    }
}
