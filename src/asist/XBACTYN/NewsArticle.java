package asist.XBACTYN;

import java.net.URL;

public class NewsArticle {

    public URL url;
    public String title;

    public NewsArticle (URL url,String title)
    {
        setUrl(url);
        setTitle(title);
    }

    public URL getUrl()
    {
        return this.url;
    }
    public String getTitle()
    {
        return this.title;
    }

    public void setUrl(URL url)
    {
        this.url = url;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
