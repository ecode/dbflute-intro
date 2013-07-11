package org.dbflute.intro.wizard;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.dbflute.emecha.eclipse.plugin.wizards.client.DBFluteNewClientPageResult;

/**
 * @author ecode
 * @author jflute
 */
public class SchemaSyncCheckPanal extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField databaseInfoUrlText;
    private JTextField databaseInfoSchemaText;
    private JTextField databaseInfoUserText;
    private JPasswordField databaseInfoPasswordText;

    /**
     * Create the application.
     */
    public SchemaSyncCheckPanal() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void initialize() {

        this.setLayout(null);

        this.add(createLabale(NewClientPanel.LABEL_URL, 10));
        this.add(createLabale(NewClientPanel.LABEL_SCHEMA, 35));
        this.add(createLabale(NewClientPanel.LABEL_USER + NewClientPanel.LABEL_REQUIRED, 60));
        this.add(createLabale(NewClientPanel.LABEL_PASSWORD, 85));

        databaseInfoUrlText = new JTextField();
        databaseInfoUrlText.setBounds(150, 10, 300, 20);
        databaseInfoUrlText.setColumns(10);
        this.add(databaseInfoUrlText);

        databaseInfoSchemaText = new JTextField();
        databaseInfoSchemaText.setBounds(150, 35, 300, 20);
        databaseInfoSchemaText.setColumns(10);
        this.add(databaseInfoSchemaText);

        databaseInfoUserText = new JTextField();
        databaseInfoUserText.setBounds(150, 60, 300, 20);
        databaseInfoUserText.setColumns(10);
        this.add(databaseInfoUserText);

        databaseInfoPasswordText = new JPasswordField();
        databaseInfoPasswordText.setBounds(150, 85, 300, 20);
        databaseInfoPasswordText.setColumns(10);
        this.add(databaseInfoPasswordText);
    }

    private JLabel createLabale(String label, int y) {
        JLabel jLabel = new JLabel(label);
        jLabel.setBounds(10, y, 150, 20);

        return jLabel;
    }

    public DBFluteNewClientPageResult asResult() {

        final String databaseInfoUrl = databaseInfoUrlText.getText();
        final String databaseInfoSchema = databaseInfoSchemaText.getText();
        final String databaseInfoUser = databaseInfoUserText.getText();
        final String databaseInfoPassword = new String(databaseInfoPasswordText.getPassword());

        final DBFluteNewClientPageResult result = new DBFluteNewClientPageResult();
        result.setDatabaseInfoUrl(databaseInfoUrl);
        result.setDatabaseInfoSchema(databaseInfoSchema);
        result.setDatabaseInfoUser(databaseInfoUser);
        result.setDatabaseInfoPassword(databaseInfoPassword);

        return result;
    }
}
