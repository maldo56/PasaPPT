package com.pasappts;

import com.pasappts.configuration.Configuration;
import com.pasappts.core.FileManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class Main extends JFrame {

    private JButton addButton = null;
    private JButton reprodButton = null;

    private JList list = null;
    private Vector itemsVector = new Vector();

    public Main() { }

    public void initialize() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 300, 300);

        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(10, 10, 266, 50);
        menuPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.getContentPane().setLayout(null);
        this.getContentPane().add(menuPanel);

        this.addButton = new JButton("+");
        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileManager.openFileExplorer();
                } catch (PasaPPTsException ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.reprodButton = new JButton("Reproducir");
        this.reprodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    FileManager.reproduce(list.getSelectedValue().toString());
                } catch (PasaPPTsException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        GroupLayout gl_menuPanel = new GroupLayout(menuPanel);
        gl_menuPanel.setHorizontalGroup(
                gl_menuPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_menuPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(this.addButton)
                                .addGap(52)
                                .addComponent(this.reprodButton, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                .addContainerGap())
        );
        gl_menuPanel.setVerticalGroup(
                gl_menuPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, gl_menuPanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_menuPanel.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(this.reprodButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                        .addComponent(this.addButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                                .addContainerGap())
        );
        menuPanel.setLayout(gl_menuPanel);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(10, 70, 266, 183);
        getContentPane().add(panel);

        this.list = new JList();
        this.list.setListData(itemsVector);

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(this.list, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(this.list, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
        );
        panel.setLayout(gl_panel);
    }

    // ************** STATIC CONTEXT ************** //
    public static Main main = null;

    public static void addItem(String item) {
        main.itemsVector.add(item);
        main.list.updateUI();
    }

    public static void main(String[] args) throws PasaPPTsException {
        Configuration.setDefaultConfiguration();

        main = new Main();
        main.initialize();

        FileManager.openFileExplorer();

        main.setVisible(true);
    }

}
