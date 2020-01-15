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
        sp2.setPreferredSize(sp1.getPreferredSize()); //The two scrollpanels will have the same size
        to.addActionListener(this); //Connect buttons to a listener
        from.addActionListener(this);
        order.addActionListener(this);
        pack(); setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == to){ //If the customer pressed on the ">>" buy button
            for(String s : l1.getSelectedValuesList()) //We loop through all selected values form the scroll panes and add them to second model object
                model2.addElement(s); //We loop in case the customer has selected more than one article
            updatePrice(); //Update priceLabel text with the current price
        } else if(e.getSource() == from){ //If the customer pressed on the "<<" remove button
            for(String s : l2.getSelectedValuesList()) //We loop through all selected values from the scroll pane and remove them from the second model object
                model2.removeElement(s);
            updatePrice(); //Update priceLabel text with the current price
        } else if(e.getSource() == order){
            String name = JOptionPane.showInputDialog("Enter you name and address please");
            if(name != null) {
                String str = String.format("Order number %d\n%s\n", ++nr, name);
                String str2 = "";
                for (int i = 0; i < model2.size(); i++) {
                    str += model2.get(i) + '\n';
                }
                JOptionPane.showMessageDialog(null, str + '\n' + str2 + '\n' + priceLabel.getText());
                l1.clearSelection(); //Clear selections from the first list for the new customer
                model2.clear(); //Empty list 2
            }
        }
    }

    private void updatePrice(){
        int totalP = 0;
        for(int i = 0; i < model2.size() ; i++){ //Loop through the purchase list
            Scanner sc = new Scanner(model2.get(i)); //Creates a new scanner for each article in the model2 list object
            String str = "";
            while(sc.hasNext()){ //Loop through the entire line to get the price
                str = sc.next();
            }
            str = str.substring(0,str.length()-2); // Remove the :- from the price and update the total price
            totalP += Integer.parseInt(str);
        }
        priceLabel.setText("Total price: " + totalP + ":-");
    }

    public static void main(String[] args) throws IOException {new ShoppingCart();}
}
