package br.com.alquest.alquestdemo.app.Custom;

public class AlquestItemListView {
    private String status;
    private String title;
    private String content;

    public AlquestItemListView() {
    }

    public AlquestItemListView(String title, String content, String status) {
        this.status = status;
        this.title = title;
        this.content = content;
    }

    public String gettitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String status) {
        this.content = status;
    }

    public void setTitle(String status) {
        this.title = status;
    }
}