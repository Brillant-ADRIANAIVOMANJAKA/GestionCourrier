package vue;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

public class ModifierEntrant1 extends javax.swing.JFrame {

    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    static String Tab2;
    static String D3;
    private JTable TableCourrierEntrant;
    private JTable TableArchive;
    private JTable TableTraitement;

    public ModifierEntrant1() {
        initComponents();
        conn = Connexion.ConnexionDB.conn;
        ReccuperationDonee();
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
    
    public void ReccuperationDonee() {
        AcceuilPrincipaleUser Ac = new AcceuilPrincipaleUser();
        Ac.ReccuperationTableEntrant();
        try {
            String reccuperation = Ac.getTable3();
            String requete = "SELECT numEntrant, dateEntrant, numCourrier, origine, nature, observation, destination FROM entrant WHERE numEntrant = '" + reccuperation + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String D1 = rs.getString("numEntrant");
                numentrant.setText(D1);
                numentrant.setEnabled(false);
                Date D2 = rs.getDate("dateEntrant");
                date.setDate(D2);
                D3 = rs.getString("numCourrier");
                numeroCourier.setText(D3);
                String D4 = rs.getString("origine");
                origine.setEditable(false); // Par défaut, la liste n'est pas éditable
                origine.removeItem("Autre");
                origine.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if ("CISCO Manandriana".equals(origine.getSelectedItem()) || "CISCO Fandriana".equals(origine.getSelectedItem()) || "CISCO Ambatofinanadrahana".equals(origine.getSelectedItem()) || "CISCO Ambositra".equals(origine.getSelectedItem())) {
                            origine.setEditable(false);
                        } else {
                            origine.setEditable(true);
                        }
                    }
                });
                origine.addItem(D4);
                origine.setSelectedItem(D4);
                String D5 = rs.getString("nature");
                nature.setText(D5);
                String D6 = rs.getString("observation");
                observation.setText(D6);
                String D7 = rs.getString("destination");
                destination.setText(D7);
                origine.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if ("Autre".equals(origine.getSelectedItem())) {
                            origine.setEditable(true);
                        } else {
                            origine.setEditable(false);
                        }
                    }
                });

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        annuler = new javax.swing.JButton();
        Modifier = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser();
        origine = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        numentrant = new javax.swing.JTextField();
        numeroCourier = new javax.swing.JTextField();
        nature = new javax.swing.JTextField();
        observation = new javax.swing.JTextField();
        destination = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setType(java.awt.Window.Type.POPUP);

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel4.setPreferredSize(new java.awt.Dimension(618, 510));

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
        jLabel1.setText("MODIFIER COURRIER ENTRANT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
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

        Modifier.setBackground(new java.awt.Color(53, 143, 197));
        Modifier.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Modifier.setForeground(new java.awt.Color(255, 255, 255));
        Modifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonModifier.png"))); // NOI18N
        Modifier.setBorder(null);
        Modifier.setBorderPainted(false);
        Modifier.setContentAreaFilled(false);
        Modifier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Modifier.setFocusPainted(false);
        Modifier.setFocusable(false);
        Modifier.setRequestFocusEnabled(false);
        Modifier.setRolloverEnabled(false);
        Modifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModifierMouseClicked(evt);
            }
        });
        Modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(annuler, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Modifier)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(annuler, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText(" Observation *");

        jLabel3.setText(" Date *");

        jLabel4.setText(" Origine *");

        jLabel5.setText(" Numero Entrant *");

        jLabel9.setText("Nature du courrier *");

        date.setBackground(new java.awt.Color(3, 105, 161));
        date.setForeground(new java.awt.Color(3, 105, 161));
        date.setDateFormatString("yyyy-MM-dd");
        date.setFocusable(false);
        date.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        origine.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CISCO Manandriana", "CISCO Fandriana", "CISCO Ambatofinandrahana", "CISCO Ambositra", "Autre" }));
        origine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                origineActionPerformed(evt);
            }
        });

        jLabel6.setText("Numero Courrier *");

        jLabel7.setText("Destination *");

        numeroCourier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroCourierActionPerformed(evt);
            }
        });
        numeroCourier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroCourierKeyTyped(evt);
            }
        });

        destination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numentrant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(destination, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(observation, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nature, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numeroCourier)
                            .addComponent(origine, 0, 273, Short.MAX_VALUE)))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(numentrant, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel6))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(numeroCourier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(origine, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nature, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(observation, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destination, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx - x, yy - y);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void annulerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_annulerMouseClicked
        this.hide();
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Modification Annulé");
        AffichageTableEntrant();
    }//GEN-LAST:event_annulerMouseClicked

    private void ModifierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModifierMouseClicked

        String nouvelleOrigine = origine.isEditable() ? origine.getEditor().getItem().toString() : origine.getSelectedItem().toString();

        String R1 = numentrant.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String d = sdf.format(date.getDate());
        String R2 = numeroCourier.getText();
        String R3 = nouvelleOrigine;
        String R4 = nature.getText();
        String R5 = observation.getText();
        String R6 = destination.getText();

        System.out.println(R1);
        System.out.println(d);
        System.out.println(R2);
        System.out.println(R3);
        System.out.println(R4);
        System.out.println(R5);
        System.out.println(R6);

        if (origine.getSelectedItem() == "Veuillez selectionner") {
                if (origine.getSelectedItem().equals("Veuillez selectionner")) {
                    JOptionPane.showMessageDialog(null, "Veuillez selectionner sur l'origine");
                }
                return;
            }
        
        AcceuilPrincipaleUser Ac = new AcceuilPrincipaleUser();
        Ac.ReccuperationTableEntrant();

        String Reccuperations = Ac.getTable3();

        String requete = "UPDATE entrant SET numEntrant = ?, dateEntrant = ?, numCourrier = ?, origine = ?, nature = ?, observation = ?, destination = ? WHERE numEntrant = ?";

        try {
            pst = conn.prepareStatement(requete);
            pst.setString(1, R1);
            pst.setString(2, d);
            pst.setString(3, R2);
            pst.setString(4, R3);
            pst.setString(5, R4);
            pst.setString(6, R5);
            pst.setString(7, R6);
            pst.setString(8, Reccuperations);

            pst.executeUpdate();

            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Modification effectuée avec succès");
            AffichageTableEntrant();

            // Mise à jour de la table 'archive' en utilisant 'numCourrier'
            String requeteArchive = "UPDATE archive SET numCourrier = ? WHERE numCourrier = ?";
            pst = conn.prepareStatement(requeteArchive);
            pst.setString(1, R2); // Mettez à jour numCourrier dans la table 'archive'
            pst.setString(2, D3);
            pst.executeUpdate();
            AffichageTableArchive();

            // Mise à jour de la table 'traitement' en utilisant 'numCourrier'
            String requeteTraitement = "UPDATE traitement SET numCourrier = ? WHERE numCourrier = ?";
            pst = conn.prepareStatement(requeteTraitement);
            pst.setString(1, R2); // Mettez à jour numCourrier dans la table 'traitement'
            pst.setString(2, D3);
            pst.executeUpdate();
            AffichageTableTraitement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.hide();
    }//GEN-LAST:event_ModifierMouseClicked

    private void ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModifierActionPerformed

    private void origineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_origineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_origineActionPerformed

    private void numeroCourierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroCourierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroCourierActionPerformed

    private void numeroCourierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroCourierKeyTyped
        char t = evt.getKeyChar();
        if (!(Character.isDigit(t) || (t == KeyEvent.VK_BACK_SPACE) || (t == KeyEvent.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_numeroCourierKeyTyped

    private void destinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_destinationActionPerformed

    int x, y;

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifierEntrant1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Modifier;
    private javax.swing.JButton annuler;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextField destination;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField nature;
    private javax.swing.JTextField numentrant;
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
