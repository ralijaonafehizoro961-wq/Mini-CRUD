package com.crud.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import com.crud.config.ConnectionDB;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	private JTextField idField;
	private JTextField nomField;
	private JLabel idLabel;
	private JLabel nomLabel;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;

	public MainFrame() {
		initializeWindow();
		initializeComponents();
		setupActions();
	}

	/**
	 * Initialise la fenêtre principale
	 */
	private void initializeWindow() {
		this.setTitle("GESTION USER");
		this.setSize(900, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Initialise tous les composants GUI
	 */
	private void initializeComponents() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(180, 200, 240));
		add(panel);

		// Label et champ ID
		idLabel = new JLabel("NUMERO:");
		idLabel.setBounds(50, 10, 200, 30);
		idLabel.setFont(new Font("Arial", Font.BOLD, 22));
		panel.add(idLabel);

		idField = new JTextField();
		idField.setBounds(270, 10, 200, 30);
		panel.add(idField);

		// Label et champ NOM
		nomLabel = new JLabel("NOM:");
		nomLabel.setBounds(50, 60, 200, 30);
		nomLabel.setFont(new Font("Arial", Font.BOLD, 22));
		panel.add(nomLabel);

		nomField = new JTextField();
		nomField.setBounds(270, 60, 200, 30);
		panel.add(nomField);

		// Boutons
		addButton = new JButton("AJOUTER");
		addButton.setBounds(100, 120, 120, 30);
		panel.add(addButton);

		updateButton = new JButton("MODIFIER");
		updateButton.setBounds(250, 120, 120, 30);
		panel.add(updateButton);

		deleteButton = new JButton("SUPPRIMER");
		deleteButton.setBounds(400, 120, 120, 30);
		panel.add(deleteButton);
	}

	/**
	 * Configure les actions des boutons
	 */
	private void setupActions() {
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser();
			}
		});

		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUser();
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUser();
			}
		});
	}

	/**
	 * Ajoute un nouvel utilisateur
	 */
	private void addUser() {
		if (!validateFields()) {
			JOptionPane.showMessageDialog(this, "Complétez le formulaire !", "Erreur", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			String query = "INSERT INTO utilisateur(id, nom) VALUES('" + idField.getText() + "','" + nomField.getText() + "')";
			executeUpdate(query);
			JOptionPane.showMessageDialog(this, "Insertion réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
			clearFields();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Erreur insertion : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Modifie un utilisateur existant
	 */
	private void updateUser() {
		if (!validateFields()) {
			JOptionPane.showMessageDialog(this, "Complétez le formulaire !", "Erreur", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			String query = "UPDATE utilisateur SET nom='" + nomField.getText() + "' WHERE id='" + idField.getText() + "'";
			executeUpdate(query);
			JOptionPane.showMessageDialog(this, "Modification réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
			clearFields();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Erreur modification : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Supprime un utilisateur
	 */
	private void deleteUser() {
		if (idField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Entrez un ID !", "Erreur", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			String query = "DELETE FROM utilisateur WHERE id='" + idField.getText() + "'";
			executeUpdate(query);
			JOptionPane.showMessageDialog(this, "Suppression réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
			clearFields();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Erreur suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Exécute une requête de mise à jour
	 */
	private void executeUpdate(String query) throws SQLException {
		ConnectionDB connectionDB = new ConnectionDB();
		statement = connectionDB.laConnection().createStatement();
		statement.executeUpdate(query);
	}

	/**
	 * Valide les champs du formulaire
	 */
	private boolean validateFields() {
		return !idField.getText().isEmpty() && !nomField.getText().isEmpty();
	}

	/**
	 * Efface les champs du formulaire
	 */
	private void clearFields() {
		idField.setText("");
		nomField.setText("");
	}
}