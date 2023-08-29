package com.example.afinal;

public class Data {
    public String dishname,description,imgid,vegornot;
    public int likes,time,id;

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String isVegornot() {
        return vegornot;
    }

    public void setVegornot(String vegornot) {
        this.vegornot = vegornot;
    }


    public Data(String dishname, String description, int likes, int time, int imgid, String vegornot) {
        this.dishname = dishname;
        this.description = description;
        this.likes = likes;
        this.time = time;
        this.imgid = "https://spoonacular.com/recipeImages/"+imgid+"-556x370.jpg";
        this.id=imgid;

        this.vegornot = vegornot;
    }

    public Data(String dishname,int imgid){
        this.dishname=dishname;
        this.imgid=   this.imgid = "https://spoonacular.com/recipeImages/"+imgid+"-556x370.jpg";
    }

}
