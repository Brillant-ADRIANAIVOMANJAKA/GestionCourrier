package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

public class AjoutTraite1 extends javax.swing.JFrame {

    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    private JTable TableTraitement;
    private JTable TableArchive;
    static String numCourrier;
    static String numArchive;

    public AjoutTraite1() {
        initComponents();
        conn = Connexion.ConnexionDB.conn;
        GetIM();
        ReccuperationDonee();

    }

    public void ReccuperationDonee() {
        AcceuilPrincipaleUser Ac = new AcceuilPrincipaleUser();
        Ac.ReccuperationTableArchive();
        try {
            String reccuperation = Ac.getTable6();
            String requete = "SELECT numArchive, numCourrier, dateArchive FROM archive WHERE numArchive = '" + reccuperation + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                numCourrier = rs.getString("numCourrier");
                numArchive = rs.getString("numArchive");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void GetIM() {
        try {
            String Query = "SELECT Nom, Prenom FROM responsable";
            pst = conn.prepareStatement(Query);
            rs = pst.executeQuery();
            while (rs.next()) {
                String prenom = rs.getString("Prenom");
                String nom = rs.getString("Nom");
                nomPers.addItem(nom + " " + prenom);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        annuler = new javax.swing.JButton();
        ajouter = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        nomPers = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setType(java.awt.Window.Type.POPUP);

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(671, 60));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selectionner  le nom du responsable");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 20));

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

        ajouter.setBackground(new java.awt.Color(53, 143, 197));
        ajouter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ajouter.setForeground(new java.awt.Color(255, 255, 255));
        ajouter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonTraiter.png"))); // NOI18N
        ajouter.setBorder(null);
        ajouter.setBorderPainted(false);
        ajouter.setContentAreaFilled(false);
        ajouter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ajouter.setFocusPainted(false);
        ajouter.setFocusable(false);
        ajouter.setRequestFocusEnabled(false);
        ajouter.setRolloverEnabled(false);
        ajouter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ajouterMouseClicked(evt);
            }
        });
        ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(annuler)
                .addGap(18, 18, 18)
                .addComponent(ajouter)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(annuler, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jLabel9.setText("Nom *");

        nomPers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Veuillez selectionner" }));
        nomPers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomPersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(nomPers, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomPers, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    int x, y;

    private void annulerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annulerMouseClicked
        this.hide();
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Annulé");
        AffichageTableTraitement();
        AffichageTableArchive();
    }//GEN-LAST:event_annulerMouseClicked

    private void ajouterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouterMouseClicked
        try {
            // Vérifier les champs vides
            if (nomPers.getSelectedItem() == "Veuillez selectionner") {
                if (nomPers.getSelectedItem().equals("Veuillez selectionner")) {
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner sur la responsable");
                }
                return;
            }
            java.util.Date dateTraitement = new java.util.Date();
            java.sql.Date sqlDateTraitement = new java.sql.Date(dateTraitement.getTime());
            String requeteInsertion = "INSERT INTO traitement (numCourrier, dateTraitement, nomPersonnel, status) VALUES (?, ?, ?, 'non envoyer')";
            pst = conn.prepareStatement(requeteInsertion);
            pst.setString(1, numCourrier);
            pst.setDate(2, sqlDateTraitement);
            pst.setString(3, nomPers.getSelectedItem().toString());
            pst.execute();

            //Suppression sur l'Archive apres traitement
            String requete = "DELETE FROM archive WHERE numArchive = ? ";
            pst = conn.prepareStatement(requete);
            pst.setString(1, numArchive);
            pst.execute();

            AffichageTableArchive();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Dossier traité avec succès");
            AffichageTableTraitement();

            this.dispose();

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_ajouterMouseClicked

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


    private void nomPersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomPersActionPerformed

    }//GEN-LAST:event_nomPersActionPerformed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx - x, yy - y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajouterActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjoutTraite1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter;
    private javax.swing.JButton annuler;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JComboBox<String> nomPers;
    // End of variables declaration//GEN-END:variables

    void setTrai(JTable TableTraitement) {
        this.TableTraitement = TableTraitement;
    }

    void setArch(JTable TableArchive) {
        this.TableArchive = TableArchive;
    }
}
