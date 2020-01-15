import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class ShoppingCart extends JFrame implements ActionListener{

    //Creates two list model objects
    private DefaultListModel<String> model1 = new DefaultListModel<>(),
                                     model2 = new DefaultListModel<>();
    //Creates two lists from the models above
    private JList<String> l1 = new JList<>(model1),
                          l2 = new JList<>(model2);
    //Buttons and labels for the UI
    private JButton to = new JButton(">>"),
                  from = new JButton("<<"),
                 order = new JButton("Order");
    private JLabel priceLabel = new JLabel("Total price: 0:-");
    private int nr; //Order number

    public ShoppingCart() throws IOException {
        JPanel arrows = new JPanel(); //Creates a Panel for the from and to arrows
        arrows.setLayout(new GridLayout(2,1,0,10));
        arrows.add(to); arrows.add(from);

        JPanel row1 = new JPanel(), //This panel will contain first scrollpane + arrows panel + second scrollpane
               row2 = new JPanel(); //This panel will contain Total price label + order button
        add(row1, BorderLayout.CENTER);
        add(row2, BorderLayout.SOUTH);

        JScrollPane sp1 = new JScrollPane(l1), //Creates two new ScrollPanes to put the
                    sp2 = new JScrollPane(l2); //articles in

        row1.add(sp1); row1.add(arrows); row1.add(sp2);
        row2.add(priceLabel); row2.add(order);
        //Add all articles from the txt file articles to the first model object using a scanner
        Scanner sc = new Scanner(new File("articles.txt"));
        while(sc.hasNext())
            model1.addElement(sc.nextLine());
        //Properties for the lists
        l1.setVisibleRowCount(10);  l2.setVisibleRowCount(10);
        sp2.setPreferredSize(sp1.getPreferredSize()); //Two scrollpanels will have the same size
        to.addActionListener(this);
        from.addActionListener(this);
        order.addActionListener(this);
        pack(); setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){

    }

    public static void main(String[] args) throws IOException {new ShoppingCart();}
}
