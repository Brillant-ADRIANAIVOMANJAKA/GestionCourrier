package vue;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

public class ModifierResponsable1 extends javax.swing.JFrame {

    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    static String Tab;
    private JTable TableResponsable;

    public ModifierResponsable1() {
        initComponents();
        conn = Connexion.ConnexionDB.conn;
        ReccuperationDonee();
        init();
    }

    private void init() {
        im.putClientProperty(FlatClientProperties.STYLE, ""
                + "showClearButton:true");
        im.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "immatriculation");
        nom.putClientProperty(FlatClientProperties.STYLE, ""
                + "showClearButton:true");
        nom.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "nom");
        prenom.putClientProperty(FlatClientProperties.STYLE, ""
                + "showClearButton:true");
        prenom.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "prenom");
        poste.putClientProperty(FlatClientProperties.STYLE, ""
                + "showClearButton:true");
        poste.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "poste");
        cin.putClientProperty(FlatClientProperties.STYLE, ""
                + "showClearButton:true");
        cin.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "carte d'identite national");
        telephone.putClientProperty(FlatClientProperties.STYLE, ""
                + "showClearButton:true");
        telephone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "telephone");
        adresse.putClientProperty(FlatClientProperties.STYLE, ""
                + "showClearButton:true");
        adresse.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "adresse");
    }

    public void ReccuperationDonee() {
        AcceuilPrincipaleUser Pr = new AcceuilPrincipaleUser();
        Pr.ReccuperationTableResponsable();

        try {
            String reccuperation = Pr.getTable1();
            String requete = "SELECT IM, Nom, Prenom, Poste, CIN, Telephone, Adresse FROM responsable WHERE IM = '" + reccuperation + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String A1 = rs.getString("im");
                im.setText(A1);
                String A2 = rs.getString("nom");
                nom.setText(A2);
                String A3 = rs.getString("prenom");
                prenom.setText(A3);
                String A4 = rs.getString("poste");
                poste.setText(A4);
                String A5 = rs.getString("cin");
                cin.setText(A5);
                String A6 = rs.getString("telephone");
                telephone.setText(A6);
                String A7 = rs.getString("adresse");
                adresse.setText(A7);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        annuler = new javax.swing.JButton();
        modifier = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        im = new javax.swing.JTextField();
        nom = new javax.swing.JTextField();
        prenom = new javax.swing.JTextField();
        poste = new javax.swing.JTextField();
        cin = new javax.swing.JTextField();
        telephone = new javax.swing.JTextField();
        adresse = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setType(java.awt.Window.Type.POPUP);

        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel7.setPreferredSize(new java.awt.Dimension(632, 521));

        jPanel8.setBackground(new java.awt.Color(0, 102, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(671, 60));
        jPanel8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel8MouseDragged(evt);
            }
        });
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel8MousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MODIFIER PERSONNEL");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(0, 102, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(0, 20));

        annuler.setBackground(new java.awt.Color(199, 80, 82));
        annuler.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        annuler.setForeground(new java.awt.Color(255, 255, 255));
        annuler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonAnnuler.png"))); // NOI18N
        annuler.setBorder(null);
        annuler.setBorderPainted(false);
        annuler.setContentAreaFilled(false);
        annuler.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        annuler.setDefaultCapable(false);
        annuler.setFocusPainted(false);
        annuler.setFocusable(false);
        annuler.setRequestFocusEnabled(false);
        annuler.setRolloverEnabled(false);
        annuler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                annulerMouseClicked(evt);
            }
        });

        modifier.setBackground(new java.awt.Color(53, 143, 197));
        modifier.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        modifier.setForeground(new java.awt.Color(255, 255, 255));
        modifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonModifier.png"))); // NOI18N
        modifier.setBorder(null);
        modifier.setBorderPainted(false);
        modifier.setContentAreaFilled(false);
        modifier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        modifier.setFocusPainted(false);
        modifier.setFocusable(false);
        modifier.setRequestFocusEnabled(false);
        modifier.setRolloverEnabled(false);
        modifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modifierMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(annuler)
                .addGap(12, 12, 12)
                .addComponent(modifier)
                .addGap(20, 20, 20))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modifier, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(annuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText(" Telephone *");

        jLabel6.setText(" Nom *");

        jLabel7.setText(" Prenom *");

        jLabel8.setText(" IM *");

        jLabel9.setText(" Poste *");

        jLabel10.setText(" Adresse *");

        jLabel11.setText(" CIN *");

        im.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                imKeyTyped(evt);
            }
        });

        cin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cinKeyTyped(evt);
            }
        });

        telephone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telephoneKeyTyped(evt);
            }
        });

        adresse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adresseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(im, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(poste, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cin, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(im, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(poste, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telephone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(20, 20, 20)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel8MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx - x, yy - y);
    }//GEN-LAST:event_jPanel8MouseDragged

    private void jPanel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel8MousePressed

    private void annulerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annulerMouseClicked
        this.hide();
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Modification Annulé");
        AffichageTableResponsable();
    }//GEN-LAST:event_annulerMouseClicked

    private void modifierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifierMouseClicked
        String imValue = im.getText();
        String nomValue = nom.getText();
        String prenomValue = prenom.getText();
        String posteValue = poste.getText();
        String cinValue = cin.getText();
        String telephoneValue = telephone.getText();
        String adresseValue = adresse.getText();

              if (im.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ IM");
        } else if (nom.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ Nom");
        } else if (prenom.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ Prenom");
        } else if (poste.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ Poste");
        } else if (cin.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ CIN");
        } else if (telephone.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ Telephone");
        } else if (adresse.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ Adresse");
        }else if (!imValue.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ IM ne doit contenir que des chiffres");
        } else if (!nomValue.matches("^[a-zA-Z ]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ Nom ne doit contenir que des lettres");
        } else if (!prenomValue.matches("^[a-zA-Z ]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ Prenom ne doit contenir que des lettres");
        } else if (!posteValue.matches("^[a-zA-Z ]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ Poste ne doit contenir que des lettres");
        } else if (!cinValue.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ CIN ne doit contenir que des chiffres");
        } else if (!telephoneValue.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ Telephone ne doit contenir que des chiffres");
        } else if (!adresseValue.matches("^[a-zA-Z0-9 ]+$")) {
            JOptionPane.showMessageDialog(null, "Le champ Adresse ne doit contenir que des lettres et des chiffres");
        } else {
            try {

                String R1 = im.getText();
                String R2 = nom.getText();
                String R3 = prenom.getText();
                String R4 = poste.getText();
                String R5 = cin.getText();
                String R6 = telephone.getText();
                String R7 = adresse.getText();

                // Mise à jour dans la base de données
                AcceuilPrincipaleUser Pr = new AcceuilPrincipaleUser();
                Pr.ReccuperationTableResponsable();
                String Reccuperation = Pr.getTable1();

                String requete = "UPDATE responsable SET IM=?, Nom=?, Prenom=?, Poste=?, CIN=?, Telephone=?, Adresse=? WHERE IM=?";
                try (PreparedStatement pst = conn.prepareStatement(requete)) {
                    pst.setString(1, R1);
                    pst.setString(2, R2);
                    pst.setString(3, R3);
                    pst.setString(4, R4);
                    pst.setString(5, R5);
                    pst.setString(6, R6);
                    pst.setString(7, R7);
                    pst.setString(8, Reccuperation);

                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        // Modification réussie
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Modification effectuée avec succès");
                        this.hide();
                        AffichageTableResponsable();
                    } else {     
                        this.hide();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_modifierMouseClicked

    private void imKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_imKeyTyped
        char t = evt.getKeyChar();
        if (!(Character.isDigit(t) || (t == KeyEvent.VK_BACK_SPACE) || (t == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_imKeyTyped

    private void cinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cinKeyTyped
        char t = evt.getKeyChar();
        if (!(Character.isDigit(t) || (t == KeyEvent.VK_BACK_SPACE) || (t == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_cinKeyTyped

    private void telephoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephoneKeyTyped
        char t = evt.getKeyChar();
        if (!(Character.isDigit(t) || (t == KeyEvent.VK_BACK_SPACE) || (t == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_telephoneKeyTyped

    private void adresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adresseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adresseActionPerformed

    int x, y;

    public void ReccuperationTableResponsable() {
        try {
            int Row = TableResponsable.getSelectedRow();
            this.Tab = (TableResponsable.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT IM, Nom, Prenom, Poste, CIN, Telephone, Adresse FROM responsable WHERE IM = '" + Tab + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String B1 = rs.getString("im");
                System.out.println(B1);
                String B2 = rs.getString("nom");
                System.out.println(B2);
                String B3 = rs.getString("prenom");
                System.out.println(B3);
                String B4 = rs.getString("poste");
                System.out.println(B4);
                String B5 = rs.getString("cin");
                System.out.println(B5);
                String B6 = rs.getString("telephone");
                System.out.println(B6);
                String B7 = rs.getString("adresse");
                System.out.println(B7);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getTable1() {
        return Tab;
    }

    public void AffichageTableResponsable() {
        try {
            String requete = "SELECT IM, Nom, Prenom, Poste, CIN, Telephone, Adresse FROM responsable";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableResponsable.setModel(model);

            // Désactiver l'édition des cellules individuelles
            TableResponsable.setDefaultEditor(Object.class, null);

            // Empêcher la sélection de plusieurs lignes
            TableResponsable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Personnaliser le rendu des lignes
            TableResponsable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

            JTableHeader Head = TableResponsable.getTableHeader();
            Dimension dimension = Head.getPreferredSize();
            dimension.height = 35;
            Head.setPreferredSize(dimension);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifierResponsable1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adresse;
    private javax.swing.JButton annuler;
    private javax.swing.JTextField cin;
    private javax.swing.JTextField im;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton modifier;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField poste;
    private javax.swing.JTextField prenom;
    private javax.swing.JTextField telephone;
    // End of variables declaration//GEN-END:variables

    void setResp(JTable TableResponsable) {
        this.TableResponsable = TableResponsable;
    }
}
