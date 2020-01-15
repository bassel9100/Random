import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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
    
}
