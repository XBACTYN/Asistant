package asist.XBACTYN;

import java.net.URL;

public class NewsArticle {

    public String url;
    public String title;

    public NewsArticle (String url,String title)
    {
        setUrl(url);
        setTitle(title);
    }

    public String getUrl()
    {
        return this.url;
    }
    public String getTitle()
    {
        return this.title;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
