package hb.tech.add_baby_stories;


public class File_item {
    private String title;
    private String body;
    private String day;



    public File_item() {
    }


    public File_item(String title, String body, String day) {
        this.title = title;
        this.body = body;
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
