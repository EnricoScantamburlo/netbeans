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
package org.netbeans.modules.git.ui.stash;

import org.openide.util.NbBundle;

/**
 *
 * @author Ondrej Vrabec
 */
@NbBundle.Messages({
    "ACSN_SaveStash_Message=Stash message",
    "ACSD_SaveStash_Message=Stash message used as part of the commit message of the final stash commit."
})
public class SaveStashPanel extends javax.swing.JPanel {

    private static final int TITLE_WIDTH;
    private static final int MESSAGE_WIDTH;
    static {
        int width = Integer.getInteger("versioning.git.commitMessageWidth", 72); //NOI18N
        if (width < 0) {
            // 72 is a good practise according to
            // http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html
            width = 72;
        }
        MESSAGE_WIDTH = width;
        width = Integer.getInteger("versioning.git.commitMessageTitleWidth", 0); //NOI18N
        if (width < 0) {
            // 50 is a good practise according to
            // http://tbaggery.com/2008/04/19/a-note-about-git-commit-messages.html
            width = 50;
        }
        TITLE_WIDTH = width;
    }
    
    /**
     * Creates new form SaveStashPanel
     */
    public SaveStashPanel () {
        initComponents();
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        txtMessage.open();
    }

    @Override
    public void removeNotify() {
        txtMessage.close();
        super.removeNotify();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMessage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();

        lblMessage.setLabelFor(txtMessage);
        org.openide.awt.Mnemonics.setLocalizedText(lblMessage, org.openide.util.NbBundle.getMessage(SaveStashPanel.class, "SaveStashPanel.lblMessage.text")); // NOI18N
        lblMessage.setToolTipText(org.openide.util.NbBundle.getMessage(SaveStashPanel.class, "SaveStashPanel.lblMessage.TTtext")); // NOI18N

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        jScrollPane1.setViewportView(txtMessage);

        org.openide.awt.Mnemonics.setLocalizedText(cbIncludeUncommitted, org.openide.util.NbBundle.getMessage(SaveStashPanel.class, "SaveStashPanel.cbIncludeUncommitted.text")); // NOI18N
        cbIncludeUncommitted.setToolTipText(org.openide.util.NbBundle.getMessage(SaveStashPanel.class, "SaveStashPanel.cbIncludeUncommitted.TTtext")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblInfo, org.openide.util.NbBundle.getMessage(SaveStashPanel.class, "SaveStashPanel.lblInfo.text")); // NOI18N
        lblInfo.setToolTipText(org.openide.util.NbBundle.getMessage(SaveStashPanel.class, "SaveStashPanel.lblInfo.toolTipText")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbIncludeUncommitted)
                            .addComponent(lblMessage)
                            .addComponent(lblInfo))
                        .addGap(0, 238, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbIncludeUncommitted)
                .addGap(18, 18, 18)
                .addComponent(lblInfo)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    final javax.swing.JCheckBox cbIncludeUncommitted = new javax.swing.JCheckBox();
    private javax.swing.JScrollPane jScrollPane1;
    final javax.swing.JLabel lblInfo = new javax.swing.JLabel();
    private javax.swing.JLabel lblMessage;
    final org.netbeans.modules.git.ui.commit.MessageArea txtMessage = new org.netbeans.modules.git.ui.commit.MessageAreaBuilder()
    .setWraplineHint(MESSAGE_WIDTH)
    .setTitleHint(TITLE_WIDTH)
    .setAccessibleName(Bundle.ACSN_SaveStash_Message())
    .setAccessibleDescription(Bundle.ACSD_SaveStash_Message())
    .build();
    // End of variables declaration//GEN-END:variables
}