package com.group.CorreiosSimJava.gui;

import com.group.CorreiosSimJava.entities.Frete;
import com.group.CorreiosSimJava.entities.Pacote;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaDetalhesFrete extends JDialog {
    public TelaDetalhesFrete(Frete frete) {
        super((Frame) null, "Detalhes do Frete #" + frete.getId(), true);
        setSize(500, 400);
        setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(new String[]{"Nome", "Peso", "C", "L", "P"}, 0);
        for(Pacote p : frete.getListaPacotes()) {
            model.addRow(new Object[]{p.getNome(), p.getPeso(), p.getComprimento(), p.getLargura(), p.getProfundidade()});
        }

        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        add(new JLabel("Valor Total: R$ " + frete.getTotal()), BorderLayout.SOUTH);
    }
}
