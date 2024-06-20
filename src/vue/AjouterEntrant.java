package vue;
import com.formdev.flatlaf.FlatClientProperties;
import com.mysql.jdbc.Statement;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;
public class AjouterEntrant extends javax.swing.JFrame {

    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    static String Tab2;
    private JTable TableCourrierEntrant;
    private JTable TableArchive;
    private JTable TableTraitement;

    public AjouterEntrant() {
        initComponents();
        conn = Connexion.ConnexionDB.conn;
        init();
    }

     private void init() {
        numeroCourier.putClientProperty(FlatClientProperties.STYLE, ""
                 + "showClearButton:true");
        numeroCourier.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "numero du courrier");
        observation.putClientProperty(FlatClientProperties.STYLE, ""
                 + "showClearButton:true");
        observation.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "observation");
        nature.putClientProperty(FlatClientProperties.STYLE, ""
                 + "showClearButton:true");
        nature.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "nature du courrier");
        destination.putClientProperty(FlatClientProperties.STYLE, ""
                 + "showClearButton:true");
        destination.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "destination");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        date = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numeroCourier = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        origine = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        nature = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        observation = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        destination = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        ajouter = new javax.swing.JButton();
        annuler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        date.setDateFormatString("yyyy-MM-dd");

        jLabel23.setText("  Date *");

        jLabel4.setText("Numero Courrier *");

        numeroCourier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroCourierKeyTyped(evt);
            }
        });

        jLabel5.setText("    Origine *");

        origine.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Veuillez selectionner", "CISCO Manandriana", "CISCO Fandriana", "CISCO Ambatofinandrahana", "CISCO Ambositra", "Autre" }));
        origine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                origineMousePressed(evt);
            }
        });

        jLabel6.setText(" Nature du courrier *");

        jLabel7.setText("  Observation *");

        jLabel8.setText("Destination *");

        jPanel4.setBackground(new java.awt.Color(0, 102, 255));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("nouveau courrier entrant");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 102, 255));

        ajouter.setBackground(new java.awt.Color(51, 153, 255));
        ajouter.setForeground(new java.awt.Color(255, 255, 255));
        ajouter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonAjouter.png"))); // NOI18N
        ajouter.setBorder(null);
        ajouter.setContentAreaFilled(false);
        ajouter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ajouter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ajouterMouseClicked(evt);
            }
        });

        annuler.setBackground(new java.awt.Color(204, 204, 204));
        annuler.setForeground(new java.awt.Color(0, 0, 0));
        annuler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonAnnuler.png"))); // NOI18N
        annuler.setBorder(null);
        annuler.setContentAreaFilled(false);
        annuler.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        annuler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                annulerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(annuler, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ajouter, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(annuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(numeroCourier, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(observation, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(destination, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nature, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(origine, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel23))
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroCourier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(origine, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nature, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(observation, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destination, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void origineMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_origineMousePressed
        origine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Autre".equals(origine.getSelectedItem())) {
                    origine.setEditable(true);
                    origine.setSelectedItem("");
                } else {
                    origine.setEditable(false);
                }
            }
        });
    }//GEN-LAST:event_origineMousePressed

    private void ajouterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouterMouseClicked
        try {
            String natureValue = nature.getText();
            String numeroCourierValue = numeroCourier.getText();
            String observationValue = observation.getText();
            String origineValue = origine.getSelectedItem().toString();
            String destinationValue = destination.getText();

            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matchernature = pattern.matcher(natureValue);
            Matcher matchernumeroCourier = pattern.matcher(numeroCourierValue);
            Matcher matcherobservation = pattern.matcher(observationValue);

            if (origine.getSelectedItem() == "Veuillez selectionner") {
                if (origine.getSelectedItem().equals("Veuillez selectionner")) {
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner sur l'origine");
                }
                return;
            }
            
            // Vérifier les caractères spéciaux
            if (matchernature.find() || matcherobservation.find() || matchernumeroCourier.find()) {
                JOptionPane.showMessageDialog(null, "Les caractères spéciaux ne sont pas valides. Veuillez réessayer", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                return; // Arrête l'exécution de la méthode en cas d'erreur
            }

            // Vérifier les champs vides
            if (date.getDate() == null || numeroCourierValue.isEmpty() || natureValue.isEmpty() || observationValue.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                return;
            }

            String dateValue = ((JTextField) date.getDateEditor().getUiComponent()).getText();

            // Insérer l'entrée dans la table "entrant"
            String requeteInsertionEntrant = "INSERT INTO entrant (dateEntrant, numCourrier, origine, nature, observation, destination) VALUES (?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(requeteInsertionEntrant, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, dateValue);
            pst.setString(2, numeroCourierValue);
            pst.setString(3, origineValue);
            pst.setString(4, natureValue);
            pst.setString(5, observationValue);
            pst.setString(6, destinationValue);
            pst.executeUpdate();

            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "AJOUT COURRIER AVEC SUCCÈS");

            AffichageTableEntrant();
            this.dispose();

            int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous traiter ce dossier ?", "Traitement du dossier", JOptionPane.YES_NO_OPTION);

            if (choix == JOptionPane.YES_OPTION) {
                // Ajout de traitement

                java.util.Date dateTraitement = new java.util.Date();
                java.sql.Date sqlDateTraitement = new java.sql.Date(dateTraitement.getTime());
                AjouterTraitement atr = new AjouterTraitement();
                atr.setNumCourrier(numeroCourierValue);
                atr.setDateTraitement(sqlDateTraitement);
                atr.setVisible(true);
                atr.setTraite(TableTraitement);
            } else {
                // Archiver le dossier dans la table "archive"
                try {
                    java.util.Date dateArchive = new java.util.Date();
                    java.sql.Date sqlDateArchive = new java.sql.Date(dateArchive.getTime());

                    String requeteInsertionArchive = "INSERT INTO archive (numCourrier, dateArchive) VALUES (?, ?)";
                    pst = conn.prepareStatement(requeteInsertionArchive);
                    pst.setString(1, numeroCourierValue);
                    pst.setDate(2, sqlDateArchive);
                    pst.executeUpdate();
                    
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Dossier archivé");
                    AffichageTableArchive();
                    this.dispose();

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_ajouterMouseClicked

     public void AffichageTableEntrant() {
        try {
            String requete = "SELECT numEntrant AS \"N°\", dateEntrant AS \"Reçu le\", numCourrier AS \"Courier N°\", origine, nature, observation, destination FROM entrant ORDER BY numEntrant ASC";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableCourrierEntrant.setModel(model);

            // Désactiver l'édition des cellules individuelles
            TableCourrierEntrant.setDefaultEditor(Object.class, null);

            // Empêcher la sélection de plusieurs lignes
            TableCourrierEntrant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Personnaliser le rendu des lignes
            TableCourrierEntrant.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    if (row % 2 == 0) {
                        c.setBackground(new Color(255, 255, 255));
                    } else {
                        c.setBackground(new Color(255, 255, 255));
                    }

                    if (isSelected) {
                        c.setBackground(new Color(146, 185, 255)); // Couleur de fond de la sélection (bleu)
                    }

                    ((DefaultTableCellRenderer) c).setHorizontalAlignment(SwingConstants.CENTER);

                    return c;
                }
            });

            JTableHeader Head = TableCourrierEntrant.getTableHeader();
            Dimension dimension = Head.getPreferredSize();
            dimension.height = 35;
            Head.setPreferredSize(dimension);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ReccuperationTableEntrant() {
        try {
            int Row = TableCourrierEntrant.getSelectedRow();
            this.Tab2 = (TableCourrierEntrant.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numEntrant, dateEntrant, numCourrier, origine, nature, observation, destination FROM entrant WHERE numEntrant = '" + Tab2 + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String B1 = rs.getString("numEntrant");
                System.out.println(B1);
                String B2 = rs.getString("dateEntrant");
                System.out.println(B2);
                String B3 = rs.getString("numCourrier");
                System.out.println(B3);
                String B4 = rs.getString("origine");
                System.out.println(B4);
                String B5 = rs.getString("nature");
                System.out.println(B5);
                String B6 = rs.getString("observation");
                System.out.println(B6);
                String B7 = rs.getString("destination");
                System.out.println(B7);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getTable3() {
        return Tab2;
    }

    public void AffichageTableArchive() {
        try {
            String requete = "SELECT numArchive AS \"N°\", numCourrier AS \"Courrier N°\", dateArchive AS \"Archiver le\" FROM archive ORDER BY numArchive ASC";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableArchive.setModel(model);

            // Désactiver l'édition des cellules individuelles
            TableArchive.setDefaultEditor(Object.class, null);

            // Empêcher la sélection de plusieurs lignes
            TableArchive.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Personnaliser le rendu des lignes
            TableArchive.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    if (row % 2 == 0) {
                        c.setBackground(new Color(255, 255, 255));
                    } else {
                        c.setBackground(new Color(255, 255, 255));
                    }

                    if (isSelected) {
                        c.setBackground(new Color(146, 185, 255)); // Couleur de fond de la sélection (bleu)
                    }

                    ((DefaultTableCellRenderer) c).setHorizontalAlignment(SwingConstants.CENTER);

                    return c;
                }
            });

            JTableHeader Head = TableArchive.getTableHeader();
            Dimension dimension = Head.getPreferredSize();
            dimension.height = 35;
            Head.setPreferredSize(dimension);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void AffichageTableTraitement() {
        try {
            String requete = "SELECT numTraitement AS \"N°\", numCourrier AS \"Courrier N°\", dateTraitement AS \"Traiter le\", nomPersonnel AS \"Traiter par\", status AS \"statut\" FROM traitement ORDER BY numTraitement ASC";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableTraitement.setModel(model);

            // Désactiver l'édition des cellules individuelles
            TableTraitement.setDefaultEditor(Object.class, null);

            // Empêcher la sélection de plusieurs lignes
            TableTraitement.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Personnaliser le rendu des lignes
            TableTraitement.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    if (row % 2 == 0) {
                        c.setBackground(new Color(255, 255, 255));
                    } else {
                        c.setBackground(new Color(255, 255, 255));
                    }

                    if (isSelected) {
                        c.setBackground(new Color(146, 185, 255)); // Couleur de fond de la sélection (bleu)
                    }

                    ((DefaultTableCellRenderer) c).setHorizontalAlignment(SwingConstants.CENTER);

                    return c;
                }
            });

            JTableHeader Head = TableTraitement.getTableHeader();
            Dimension dimension = Head.getPreferredSize();
            dimension.height = 35;
            Head.setPreferredSize(dimension);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    
    private void annulerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annulerMouseClicked
        this.hide();
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Annulé");
    }//GEN-LAST:event_annulerMouseClicked

    private void numeroCourierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroCourierKeyTyped
        char t = evt.getKeyChar();
        if (!(Character.isDigit(t) || (t == KeyEvent.VK_BACK_SPACE) || (t == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_numeroCourierKeyTyped


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AjouterEntrant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AjouterEntrant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjouterEntrant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AjouterEntrant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjouterEntrant().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter;
    private javax.swing.JButton annuler;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextField destination;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField nature;
    private javax.swing.JTextField numeroCourier;
    private javax.swing.JTextField observation;
    private javax.swing.JComboBox<String> origine;
    // End of variables declaration//GEN-END:variables

    void setEntr(JTable TableCourrierEntrant) {
        this.TableCourrierEntrant = TableCourrierEntrant;
    }

    void setArch(JTable TableArchive) {
        this.TableArchive = TableArchive;
    }

    void setTraite(JTable TableTraitement) {
        this.TableTraitement = TableTraitement;
    } 
    
}
