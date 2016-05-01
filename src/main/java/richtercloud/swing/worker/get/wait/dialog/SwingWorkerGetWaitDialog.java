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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INPUT_VALUE_PROPERTY;
import static javax.swing.JOptionPane.VALUE_PROPERTY;
import javax.swing.JProgressBar;

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
public class SwingWorkerGetWaitDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private boolean canceled = false;
    private final JLabel label = new JLabel(" ");
    private final JProgressBar progressBar = new JProgressBar();

    /**
     * Creates new form SwingWorkerGetWaitDialog
     * @param parent the parent of the dialog (see
     * {@link JDialog#JDialog(java.awt.Dialog) } for details)
     * @param dialogTitle
     * @param labelText the text of info label
     * @param progressBarText the text of the progress bar
     */
    public SwingWorkerGetWaitDialog(Frame parent,
            String dialogTitle,
            String labelText,
            String progressBarText) {
        super(parent,
                dialogTitle, //title
                true //modal
        );
        init(labelText,
                progressBarText,
                parent);
    }

    public SwingWorkerGetWaitDialog(Window parent,
            String dialogTitle,
            String labelText,
            String progressBarText) {
        super(parent,
                dialogTitle, //title
                ModalityType.APPLICATION_MODAL //modalityType
        );
        init(labelText,
                progressBarText,
                parent);
    }

    private void init(String labelText,
            String progressBarText,
            Component parent) {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.label.setText(labelText);
        this.progressBar.setString(progressBarText);
        this.progressBar.setStringPainted(true);
        JOptionPane optionPane = new JOptionPane(new Object[] {label,
            progressBar},
            JOptionPane.INFORMATION_MESSAGE,
            JOptionPane.DEFAULT_OPTION,
            null,
            new String[] {"Cancel"},
            null) {
            private static final long serialVersionUID = 1L;
            @Override
            public int getMaxCharactersPerLineCount() {
                return 60;
            }
        };
        optionPane.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                if(SwingWorkerGetWaitDialog.this.isVisible()
                        && event.getSource() == optionPane
                        && (event.getPropertyName().equals(VALUE_PROPERTY)
                                || event.getPropertyName().equals(INPUT_VALUE_PROPERTY))) {
                    SwingWorkerGetWaitDialog.this.canceled = true;
                    SwingWorkerGetWaitDialog.this.setVisible(false);
                    SwingWorkerGetWaitDialog.this.dispose();
                }
            }
        });
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(optionPane,
                BorderLayout.CENTER);
        pack();
        this.setLocationRelativeTo(parent);
    }

    public boolean isCanceled() {
        return canceled;
    }

    public JLabel getLabel() {
        return label;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }
}
