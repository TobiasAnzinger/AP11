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

        Container con = getContentPane();
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("add");
        JButton removeButton = new JButton("remove");
        JButton removeAllButton = new JButton("remove all");
        JButton exitButton = new JButton("exit");

        lm = new DefaultListModel<>();
        list = new JList<>(lm);
        final JScrollPane sp = new JScrollPane(list);
        con.add(sp, BorderLayout.CENTER);

        textField = new JTextField();
        con.add(textField, BorderLayout.NORTH);
        con.add(buttonPanel, BorderLayout.EAST);
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);
        buttonPanel.add(exitButton);

        addButton.addActionListener((e) -> {
            lm.addElement(textField.getText());
            textField.setText("");
            setFocusToTextField();
        });

        removeButton.addActionListener((e) -> lm.removeElementAt(list.getSelectedIndex()));
        removeAllButton.addActionListener((e) -> lm.removeAllElements());
        exitButton.addActionListener((e) -> exit());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                exit();
            }
        });


        setSize(600, 400);
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        setVisible(true);
        setFocusToTextField();
    }

    private void setFocusToTextField() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textField.requestFocus();
            }
        });
    }

    private void exit() {
        System.exit(0);
    }
}
