package cn.fves24.vo;

public class Note {
    private int id;
    private String title;
    private String content;
    private int del;
    private String createTime;

    public Note() {

    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note(String title, String content, String createTime) {
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public String getCreateTime() {

        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", del=" + del +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
