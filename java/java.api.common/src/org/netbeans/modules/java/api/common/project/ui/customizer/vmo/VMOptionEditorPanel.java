/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*
 * VMOptionEditorPanel.java
 *
 * Created on 12.10.2010, 11:39:51
 */

package org.netbeans.modules.java.api.common.project.ui.customizer.vmo;

import javax.swing.event.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.List;

/**
 * @author moonko
 */
public class VMOptionEditorPanel extends javax.swing.JPanel {
    private ValueCellEditor cellEditor;



    public static interface Callback {
        void okButtonActionPerformed(java.awt.event.ActionEvent evt);

        void cancelButtonActionPerformed(java.awt.event.ActionEvent evt);
    }

    private final Callback callback;

    /**
     * Creates new form VMOptionEditorPanel
     */
    public VMOptionEditorPanel(Callback callback, VMOptionsTableModel model) {
        this.callback = callback;
        initComponents();
        optionsTable.setModel(model);
        cellEditor = new ValueCellEditor();
        configureTable();
    }

    private void configureTable() {
        final TableColumnModel tcm = optionsTable.getColumnModel();
        setUpColumns(tcm, 0, tcm.getColumnCount() - 1);
        tcm.addColumnModelListener(new TableColumnModelListener() {
            @Override
            public void columnAdded(TableColumnModelEvent e) {
                setUpColumns(tcm, e.getFromIndex(), e.getToIndex());
            }

            @Override
            public void columnRemoved(TableColumnModelEvent e) {                
            }

            @Override
            public void columnMoved(TableColumnModelEvent e) {
            }

            @Override
            public void columnMarginChanged(ChangeEvent e) {
            }

            @Override
            public void columnSelectionChanged(ListSelectionEvent e) {
            }
        });

        optionsTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                processText();                
            }
        });

        processText();
        optionsTable.setFillsViewportHeight(true);
        optionsTable.setShowVerticalLines(false);
    }

    private void processText() {
        final List<JavaVMOption<?>> list = getOptions();
        StringBuilder sb = new StringBuilder();
        for (JavaVMOption<?> option : list) {
            option.print(sb);
            sb.append(" ");
        }
        variablePreviewTextField.setText(sb.toString());
    }                                           

    private void setUpColumns(TableColumnModel tcm, int start, int stop) {
        for (int i = start; i <= stop; i++) {
            final TableColumn column = tcm.getColumn(i);
            setUpColumn(column);
            if (i == 0) {
                column.setPreferredWidth(150);
                column.setMaxWidth(150);
            }
        }
    }

    private void setUpColumn(TableColumn column) {
        column.setCellRenderer(cellEditor);
        column.setCellEditor(cellEditor);        
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        variablePreviewTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        optionsTable = new javax.swing.JTable();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        variablePreviewTextField.setEditable(false);
        variablePreviewTextField.setText(org.openide.util.NbBundle.getMessage(VMOptionEditorPanel.class, "VMOptionEditorPanel.variablePreviewTextField.text")); // NOI18N

        optionsTable.setModel(new VMOptionsTableModel());
        jScrollPane1.setViewportView(optionsTable);

        cancelButton.setText(org.openide.util.NbBundle.getMessage(VMOptionEditorPanel.class, "VMOptionEditorPanel.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText(org.openide.util.NbBundle.getMessage(VMOptionEditorPanel.class, "VMOptionEditorPanel.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(variablePreviewTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(variablePreviewTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (callback != null) {
            callback.okButtonActionPerformed(evt);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        if (callback != null) {
            callback.cancelButtonActionPerformed(evt);
        }
    }//GEN-LAST:event_cancelButtonActionPerformed


    void setText(String text) {
        variablePreviewTextField.setText(text);
    }

    List<JavaVMOption<?>> getOptions() {
        final VMOptionsTableModel tableModel = (VMOptionsTableModel) optionsTable.getModel();
        return tableModel.getValidOptions();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButton;
    private javax.swing.JTable optionsTable;
    private javax.swing.JTextField variablePreviewTextField;
    // End of variables declaration//GEN-END:variables

}