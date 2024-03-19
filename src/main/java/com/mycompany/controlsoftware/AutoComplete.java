/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlsoftware;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Usuario
 */
class AutoComplete {
    private JComboBox<String> comboBox;
    private List<String> suggestions;
    private JTextField textField;

    public AutoComplete(JComboBox<String> comboBox, List<String> suggestions) {
        this.comboBox = comboBox;
        this.suggestions = suggestions;
        this.textField = (JTextField) comboBox.getEditor().getEditorComponent();

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                showSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // No action needed
            }
        });

        comboBox.getEditor().getEditorComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                comboBox.setPopupVisible(false);
            }
        });
    }

    private void showSuggestions() {
        String text = textField.getText().toLowerCase();
        comboBox.removeAllItems();

        for (String suggestion : suggestions) {
            if (suggestion.toLowerCase().startsWith(text)) {
                comboBox.addItem(suggestion);
            }
        }

        if (comboBox.getItemCount() > 0) {
            comboBox.setPopupVisible(true);
        } else {
            comboBox.setPopupVisible(false);
        }
    }
}