package com.crud.view;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

import com.crud.config.ConnectionDB;

public class MainFrame extends JFrame{
	Statement st;
    ConnectionDB con = new ConnectionDB();
    ResultSet rst;

    JTextField id, nom;
    JLabel txtid, txtnom;
    JButton ajout, modifier, supprimer;
}
