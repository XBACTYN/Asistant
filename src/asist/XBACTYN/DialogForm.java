package asist.XBACTYN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

public class DialogForm extends JFrame implements ActionListener{

    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;
    public JButton b_send;
    public JTextPane a_asist;
    public JScrollPane scroll_asist;
    public JScrollPane scroll_user;
    public JTextArea a_user;
    public JPanel panel;
    public ChatBot bot;

    public DialogForm()
    {
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        b_send = new JButton("Send command");
        a_asist = new JTextPane();
        a_user = new JTextArea(4,20);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        scroll_asist = new JScrollPane(a_asist,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll_user = new JScrollPane(a_user,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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

        bot = new ChatBot();



    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if(a_user.getText().trim().length()>0)
        {
            try {
                StyledDocument doc = a_asist.getStyledDocument();
                doc.insertString(doc.getLength(), "Me:\n>>" + a_user.getText()+"\n\n", new SimpleAttributeSet());
                doc.insertString(doc.getLength(), bot.botAnswers(a_user.getText()), new SimpleAttributeSet());
                a_user.selectAll();
                a_user.replaceSelection("");
            }catch (Exception e){}
        }

    }
}
