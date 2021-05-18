package todoList;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class TodoList extends JFrame {


    public static void main(String[] args) {
        new TodoList();
    }

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final JTextField textField;
    private final JList<String> list;
    private final DefaultListModel<String> lm;

    public TodoList() {
        super();
        setTitle("TodoList");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                exit();
            }
        });

        Container con = getContentPane();
        JPanel buttonPanel = new JPanel();
        JButton add = new JButton("add");
        JButton remove = new JButton("remove");
        JButton removeAll = new JButton("remove all");
        JButton exit = new JButton("exit");

        lm = new DefaultListModel<>();
        list = new JList<>(lm);
        final JScrollPane sp = new JScrollPane(list);
        con.add(sp, BorderLayout.CENTER);

        textField = new JTextField();
        con.add(textField, BorderLayout.NORTH);
        con.add(buttonPanel, BorderLayout.EAST);
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(add);
        buttonPanel.add(remove);
        buttonPanel.add(removeAll);
        buttonPanel.add(exit);

        add.addActionListener((e) -> {
            lm.addElement(textField.getText());
            textField.setText("");
        });
        remove.addActionListener((e) -> lm.removeElementAt(list.getSelectedIndex()));
        removeAll.addActionListener((e) -> lm.removeAllElements());
        exit.addActionListener((e) -> exit());

        setSize(400, 400);
//        center window
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        setVisible(true);
    }

    private void exit() {
        System.exit(0);
    }
}
