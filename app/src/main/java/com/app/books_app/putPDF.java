package com.app.books_app;

public class putPDF {
    public String name;
    public String url;
    int image;


    public putPDF(){

    }

    public putPDF(String name, String url){
        this.name = name;
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
