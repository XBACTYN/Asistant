package asist.XBACTYN;
import java.util.*;
import java.util.regex.*;

public class ChatBot {

    private Pattern pattern;
    public Date date;

    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>()
    {{
        // hello
        put("хай", "hello");
        put("привет", "hello");
        put("здорово", "hello");
        put("здравствуй", "hello");
        // who
        put("кто\\s.*ты", "who");
        put("ты\\s.*кто", "who");
        // name
        put("как\\s.*зовут", "name");
        put("как\\s.*имя", "name");
        put("есть\\s.*имя", "name");
        put("какое\\s.*имя", "name");
        // howareyou
        put("как\\s.*дела", "howareyou");
        put("как\\s.*жизнь", "howareyou");
        // whatdoyoudoing
        put("зачем\\s.*тут", "whatdoyoudoing");
        put("зачем\\s.*здесь", "whatdoyoudoing");
        put("что\\s.*делаешь", "whatdoyoudoing");
        put("чем\\s.*занимаешься", "whatdoyoudoing");
        // whatdoyoulike
        put("что\\s.*нравится", "whatdoyoulike");
        put("что\\s.*любишь", "whatdoyoulike");
        // iamfeelling
        put("кажется", "iamfeelling");
        put("чувствую", "iamfeelling");
        put("испытываю", "iamfeelling");
        // yes
        put("^да", "yes");
        put("согласен", "yes");
        // whattime
        put("который\\s.*час", "whattime");
        put("сколько\\s.*время", "whattime");
        // bye
        put("прощай", "bye");
        put("увидимся", "bye");
        put("до\\s.*свидания", "bye");



        //put("новост\\s.*4pda","parse4pdanews");
        //put("новост\\s.*[stopgame|стопгейм]\\s*","parsestopgamenews");
        put("новости","parsestopgamenews");




    }};
    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>()
    {{
        put("hello", "Здравствуйте, рад Вас видеть.");
        put("who", "Я обычный чат-бот.");
        put("name", "Зовите меня Чаттер :)");
        put("howareyou", "Спасибо, что интересуетесь. У меня всё хорошо.");
        put("whatdoyoudoing", "Я пробую общаться с людьми.");
        put("whatdoyoulike", "Мне нравиться думать что я не просто программа.");
        put("iamfeelling", "Как давно это началось? Расскажите чуть подробнее.");
        put("yes", "Согласие есть продукт при полном непротивлении сторон.");
        put("bye", "До свидания. Надеюсь, ещё увидимся.");
    }};
    public ChatBot() {
        //random = new Random();
        date = new Date();
    }
    public String botAnswers(String user_message)
    {
        String answer ="Bot:\n>>"+recognizeText(user_message)+"\n\n";


        return answer;
    }

    private String recognizeText(String msg)
    {
        String say="";
        //int type =0;
        String message = String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
        for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet())
        {
            pattern = Pattern.compile(o.getKey());
            if (pattern.matcher(message).find())
            {
                if(o.getValue().contains("parse"))
                {
                    System.out.println("Хер");
                    return StartParse(o.getValue());
                }
                if (o.getValue().equals("whattime")) return date.toString();
                else return ANSWERS_BY_PATTERNS.get(o.getValue());
            }

        }
        return say;
    }

    private String StartParse(String key)
    {
        String answer = "Посмотри что нашел:\n";

        Parser pars1 = new Parser(key);
        List<NewsArticle> articl1 = new ArrayList();
        articl1.addAll(pars1.GetData());

        for(NewsArticle el: articl1)
        {
            answer+=el.getTitle()+"\n"+el.getUrl()+"\n\n";
        }

        return answer;
    }
}
