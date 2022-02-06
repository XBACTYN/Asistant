package asist.XBACTYN;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.net.URL;


public class Parser {

    private String type;
    private Document doc;

    private String url;

    final HashMap<String,String> REQUEST_URL = new HashMap<>()
    {{
        put("parse4pdanews","4pda.to");              //экранировать ли точку.
        put("parsestopgamenews","https://stopgame.ru");    //экранирование под вопросом.

    }};

    public Parser(String key)
    {
        setType(key);
        url ="";
    }

    public void setType(String key)
    {
        this.type =key;
    }

    public String getType()
    {
        return this.type;
    }


    public List<NewsArticle>  GetData()
    {
        List<NewsArticle> articleList = new ArrayList<>();

        for (HashMap.Entry<String,String> o: REQUEST_URL.entrySet())
        {
            if(o.getKey() == type)
                url =o.getValue();
        }

            GrabDoc(url);
            Elements fullElems = doc.getElementsByAttributeValue("class","item article-summary");   //все для stopgame

            fullElems.forEach(divElement->
            {
                Element aElement = divElement.child(0);
                String url1 = aElement.attr("href");
                Element imgElement =aElement.child(0);
                String title = imgElement.attr("alt");


                try {
                    articleList.add(new NewsArticle( new URL(url+url1.toString()),title));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            });

            //Теперь вывести articleList.
            //articleList.forEach(System.out::println);


        return articleList;
    }

    private void GrabDoc(String url)
    {
        try {
            //this.doc = Jsoup.connect("http://"+url).userAgent("Chrome/81.0.4044.138").get();
            this.doc = Jsoup.connect(url+"/news").get();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
