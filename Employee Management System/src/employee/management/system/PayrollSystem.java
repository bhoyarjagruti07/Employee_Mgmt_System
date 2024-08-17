package employee.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class PayrollSystem extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField t1;
    Choice ch1;
    JButton bt1, bt2;
    Font t;
    JPanel p1, p2;

    PayrollSystem() {
        super("Salary");
        setLocation(150, 150);
        setSize(500, 300);
        setResizable(false);

        t = new Font("Arial", Font.BOLD, 14);

        l1 = new JLabel("Select ID");
        l2 = new JLabel("Basic Salary");

        l1.setFont(t);
        l2.setFont(t);

        ch1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select empId from employee");
            while (rs.next()) {
                ch1.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ch1.setFont(t);

        t1 = new JTextField();
        t1.setFont(t);
        
        bt1 = new JButton("Submit");
        bt2 = new JButton("Back");
        bt1.setFont(t);
        bt2.setFont(t);
        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt2.setBackground(Color.BLACK);
        bt2.setForeground(Color.WHITE);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(3, 2, 10, 10));
        p1.add(l1);
        p1.add(ch1);
        p1.add(l2);
        p1.add(t1);
        p1.add(bt1);
        p1.add(bt2);

        setLayout(new BorderLayout(20, 20));
        add(p1, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bt1) {
            int empId = Integer.parseInt(ch1.getSelectedItem());
            float basic = Float.parseFloat(t1.getText());

            try {
                Conn c = new Conn();

                // Update the basic salary in the employee table
                String updateEmployeeQuery = "update employee set salary='" + basic + "' where empId='" + empId + "'";
                c.s.executeUpdate(updateEmployeeQuery);

                JOptionPane.showMessageDialog(null, "Salary Updated Successfully");
                setVisible(false);
                new home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == bt2) {
            setVisible(false);
            new home();
        }
    }

    public static void main(String args[]) {
        new PayrollSystem();
    }
}
