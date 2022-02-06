package asist.XBACTYN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class DialogForm extends JFrame implements ActionListener{

    final int START_LOCATION = 200;
    //final int WINDOW_WIDTH = 700;
    //final int WINDOW_HEIGHT = 700;
    public JButton b_send;
    public JEditorPane a_asist;
    public JScrollPane scroll_asist;
    public JScrollPane scroll_user;
    public JTextArea a_user;
    public JPanel panel;
    public ChatBot bot;
    public HTMLDocument htmldoc;

    public DialogForm()
    {
        //setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(300,150);

        bot = new ChatBot();
        htmldoc = new HTMLDocument();

        b_send = new JButton("Send command");
        a_asist = new JEditorPane();
        a_user = new JTextArea(4,20);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        scroll_asist = new JScrollPane(a_asist,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll_user = new JScrollPane(a_user,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        a_asist.setDocument(htmldoc);
        a_asist.addHyperlinkListener(new HyperlinkListener() {

            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {

                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    System.out.println(e.getSourceElement());
                    if (e.getURL() != null) {
                        System.out.println(e.getURL());//тест
                        //НАПИСАТЬ КУСОК В КОТОРОМ ИСПОЛЬЗУЕМ БРАУЗЕР ПО УМОЛЧАНИЮ И ОТКРЫВАЕМ
                    }
                    else
                        System.out.println(e.getDescription());
                    System.out.println("-----");
                }
            }
        });

        b_send.addActionListener(this);
        a_asist.setContentType("text/html");

        BoxLayout layout = new BoxLayout(panel,BoxLayout.X_AXIS);
        panel.setLayout(layout);
        panel.add(scroll_user);
        panel.add(b_send);
        add(BorderLayout.CENTER, scroll_asist);
        add(BorderLayout.SOUTH, panel);


        a_asist.setEditable(false);
        a_user.setLineWrap(true);
        a_user.setWrapStyleWord(true);

        //setContentPane(panel);

        setSize(400,400);
        setVisible(true);





    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(a_user.getText().trim().length()>0)
        {
            try {

                //HTMLEditorKit editorKit = (HTMLEditorKit) a_asist.getEditorKit();

                a_asist.getDocument().insertString(a_asist.getDocument().getLength(), "Me:\n>>" + a_user.getText() + "\n\n", new SimpleAttributeSet());
                a_asist.getDocument().insertString(a_asist.getDocument().getLength(), bot.botAnswers(a_user.getText()), new SimpleAttributeSet());

                if(bot.htmldoc!=null)
                    a_asist.setDocument(bot.htmldoc);


                a_user.selectAll();
                a_user.replaceSelection("");
            }catch (Exception e){}
        }

    }

    public void hyperlinkUpdate(HyperlinkEvent e) {

        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            System.out.println(e.getSourceElement());
            if (e.getURL() != null)
                System.out.println(e.getURL());
            else
                System.out.println(e.getDescription());
            System.out.println("-----");
        }
    }

}
