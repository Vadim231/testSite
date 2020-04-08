package kz.itstep.entity;

import java.math.BigDecimal;

public class Cource extends Entity {
    private String title;
    private String description;
    private Integer price;
    private Integer duration;
    private Language language;
    private String imgUrl;
    private String htmlBlock;
    private boolean deleted;

    public Cource(String title, String description, Integer price, Integer duration, Language language, String imgUrl, String htmlBlock, boolean deleted) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.language = language;
        this.imgUrl = imgUrl;
        this.htmlBlock = htmlBlock;
        this.deleted = deleted;
    }

    public String getHour(int hour){
        String strHour = Integer.toString(hour);
        String lastChar = strHour.substring(strHour.length() - 1);
        hour = Integer.parseInt(lastChar);
        if(hour == 1)return "час";
        else if(hour > 1 && hour < 5)return "часа";
        else return "часов";
    }

    public Cource(){}

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getHtmlBlock() {
        return htmlBlock;
    }

    public void setHtmlBlock(String htmlBlock) {
        this.htmlBlock = htmlBlock;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}