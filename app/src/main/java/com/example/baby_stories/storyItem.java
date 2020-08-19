package com.example.baby_stories;

public class storyItem {
    String title ;
    String story ;
    int Id ;
    int imageId;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    public storyItem() {
    }

    public storyItem( int id,String title, String story,int imageId) {
        this.title = title;
        this.story = story;
        Id = id;
        this.imageId = imageId;
    }
    public storyItem(String title, String story,int imageId) {
        this.title = title;
        this.story = story;
        this.imageId = imageId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
