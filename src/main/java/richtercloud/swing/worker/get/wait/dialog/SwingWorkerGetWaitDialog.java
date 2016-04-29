/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package richtercloud.swing.worker.get.wait.dialog;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 * Provides a {@link JDialog} with a label with configurable text, a progress
 * bar with configurable text and mode and an optional cancel button with
 * configurable actions. All components can be manipulated by accessing them
 * with their getters {@link #getLabel() }, {@link #getProgressBar() } and
 * {@link #getCancelButton() }.
 *
 * All instances are modal dialogs since process displaying and cancel
 * possibilities can more easily created with {@link ProgressMonitor} if
 * {@link SwingWorker#get() } isn't used on the event handling thread (EVT).
 * @author richter
 */
/*
internal implementation notes:
- Don't hide components (e.g. top label) because it's no that disturbing if it's
displayed with whitespace text only.
*/
public class SwingWorkerGetWaitDialog extends javax.swing.JDialog {
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form SwingWorkerGetWaitDialog
     * @param parent the parent of the dialog (see
     * {@link JDialog#JDialog(java.awt.Dialog) } for details)
     * @param labelText the text of info label
     * @param progressBarText the text of the progress bar
     * @param cancelButtonActionListener
     */
    public SwingWorkerGetWaitDialog(java.awt.Frame parent,
            String labelText,
            String progressBarText,
            ActionListener cancelButtonActionListener) {
        super(parent,
                true //modal
        );
        initComponents();
        this.label.setText(labelText);
        this.progressBar.setString(progressBarText);
        this.progressBar.setStringPainted(true);
        this.cancelButton.addActionListener(cancelButtonActionListener);
    }

    public JLabel getLabel() {
        return label;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        label.setText(" ");

        cancelButton.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel label;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}