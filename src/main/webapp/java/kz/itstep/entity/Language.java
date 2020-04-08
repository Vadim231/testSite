package kz.itstep.entity;

public class Language extends Entity {
    private String name;
    private String imgUrl;

    public Language(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public Language(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
