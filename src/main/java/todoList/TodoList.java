package todoList;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class TodoList extends JFrame {

    public static int count = 0;


    public static int getCount() {
        return count;
    }

    public void setCount(int count) {
        TodoList.count = count;
    }

    public static void main(String[] args) {
        new TodoList();
    }

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final JTextField textField;
    private final JList<String> list;
    private final DefaultListModel<String> lm;

    public TodoList() {
        super();
        DefaultListModel<String> lm1;
        setTitle("TodoList");
        Container con = getContentPane();
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("add");
        JButton removeButton = new JButton("remove");
        JButton removeAllButton = new JButton("remove all");
        JButton exitButton = new JButton("save & exit");


        try {
            lm1 = IO.read();
        } catch (Exception e) {
            e.printStackTrace();
            lm1 = new DefaultListModel<>();
        }
        lm = lm1;
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

        removeButton.addActionListener((e) -> {
            try {
                lm.removeElementAt(list.getSelectedIndex());
            } catch (IndexOutOfBoundsException i) {
                i.printStackTrace();
            }
        });

        removeAllButton.addActionListener((e) -> lm.removeAllElements());
        exitButton.addActionListener((e) -> {
                    if (!IO.save(list)) {
                        JOptionPane.showMessageDialog(new JFrame(), "failed saving the list");
                    }
                    exit();
                }
        );

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                exit();
            }
        });

        setSize(600, 400);

        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height -

                getHeight()) / 2);

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

    public class Entry {

        public String description;
        public int count;

        @Override
        public String toString() {
            return (description + "                                  ").substring(0, 40) + " " + count;
        }
    }


}

