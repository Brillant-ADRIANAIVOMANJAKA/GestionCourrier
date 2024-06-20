package vue;

import com.formdev.flatlaf.FlatClientProperties;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import pagination.EventPagination;
import pagination.style.PaginationItemRenderStyle1;
import raven.toast.Notifications;

public class AcceuilPrincipaleUser extends javax.swing.JFrame {

    public static Connection conn = null;
    public static PreparedStatement pst = null;
    public static ResultSet rs = null;
    static String Tab;
    static String Tab1;
    static String Tab2;
    static String Tab3;
    static String Tab4;
    static String Tab5;
    static String B5;
    public String type;
    public int count;

    boolean a = true;

    public AcceuilPrincipaleUser() {
        conn = Connexion.ConnexionDB.conn;
        initComponents();
        this.setExtendedState(AcceuilPrincipaleUser.MAXIMIZED_BOTH);
        AffichageTableResponsable();
        AffichageTableEntrant();
        AffichageTableSortant();
        AffichageTableTraitement();
        AffichageTableArchive();
        Total_Table();
        Histogramme();
        DashEntrant();
        DashSortant();
        DashPersonnel();
        init();
        compterCourriersArchives();
        jRadioButton1.add(Tous);

        TableCourrierEntrant.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TableCourrierEntrant.getTableHeader().setOpaque(false);
        TableCourrierEntrant.getTableHeader().setBackground(new Color(2, 132, 199));
        TableCourrierEntrant.getTableHeader().setForeground(new Color(255, 255, 255));

        TableCourrierSortant.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TableCourrierSortant.getTableHeader().setOpaque(false);
        TableCourrierSortant.getTableHeader().setBackground(new Color(2, 132, 199));
        TableCourrierSortant.getTableHeader().setForeground(new Color(255, 255, 255));

        TableTraitement.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TableTraitement.getTableHeader().setOpaque(false);
        TableTraitement.getTableHeader().setBackground(new Color(2, 132, 199));
        TableTraitement.getTableHeader().setForeground(new Color(255, 255, 255));

        TableResponsable.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TableResponsable.getTableHeader().setOpaque(false);
        TableResponsable.getTableHeader().setBackground(new Color(2, 132, 199));
        TableResponsable.getTableHeader().setForeground(new Color(255, 255, 255));

        TableArchive.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        TableArchive.getTableHeader().setOpaque(false);
        TableArchive.getTableHeader().setBackground(new Color(2, 132, 199));
        TableArchive.getTableHeader().setForeground(new Color(255, 255, 255));

        setColor(JBoard);
        resetColor(JEntrant);
        resetColor(JSortant);
        resetColor(JResponsable);
        resetColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(true);
        Jp2.setVisible(false);
        Jp3.setVisible(false);
        Jp4.setVisible(false);
        Jp5.setVisible(false);
        Jp7.setVisible(false);

        UIManager.put("OptionPane.yesButtonText", "Oui");
        UIManager.put("OptionPane.noButtonText", "Non");

        pagination1.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination1.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataPersonnel(page);
            }

        });

        pagination2.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination2.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataEntrant(page);
            }

        });

        pagination3.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination3.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataSortant(page);
            }

        });
        pagination4.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination4.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataTraitement(page);
            }

        });

        pagination5.setPaginationItemRender(new PaginationItemRenderStyle1());
        pagination5.addEventPagination(new EventPagination() {
            @Override
            public void pageChanged(int page) {
                loadDataArchive(page);
            }

        });

    }

    private void init() {
        txtrecherche.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Recherche");
    }
    
    private void loadDataPersonnel(int page) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            DecimalFormat f = new DecimalFormat("#,##0.##");
            DefaultTableModel model = (DefaultTableModel) TableResponsable.getModel();
            model.setRowCount(0);
            int limit = 12;

            // Correction de la requête SQL pour compter le nombre de lignes
            String requete = "SELECT COUNT(*) FROM responsable";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            pst.close();

            int totalPage = (int) Math.ceil((double) count / limit); // Assurez-vous de diviser en utilisant un double pour obtenir une valeur décimale
            String requete1 = "SELECT IM, Nom, Prenom, Poste, CIN, Telephone, Adresse FROM responsable limit " + (page - 1) * limit + " , " + limit;
            pst = conn.prepareStatement(requete1);
            rs = pst.executeQuery();
            while (rs.next()) {
                String b1 = rs.getString("im");
                System.out.println(b1);
                String b2 = rs.getString("nom");
                System.out.println(b2);
                String b3 = rs.getString("prenom");
                System.out.println(b3);
                String b4 = rs.getString("poste");
                System.out.println(b4);
                String b5 = rs.getString("cin");
                System.out.println(b5);
                String b6 = rs.getString("telephone");
                System.out.println(b6);
                String b7 = rs.getString("adresse");
                System.out.println(b7);
                model.addRow(new Object[]{b1, b2, b3, b4, b5, b6, b7});
            }
            rs.close();
            pst.close();
            pagination1.setPagegination(page, totalPage);
        } catch (Exception e) {
            e.printStackTrace(); // Utilisez e.printStackTrace() pour afficher l'erreur complète
        }

    }

    private void loadDataEntrant(int page) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            DecimalFormat f = new DecimalFormat("#,##0.##");
            DefaultTableModel model = (DefaultTableModel) TableCourrierEntrant.getModel();
            model.setRowCount(0);
            int limit = 12;

            // Correction de la requête SQL pour compter le nombre de lignes
            String requete = "SELECT COUNT(*) FROM entrant";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            pst.close();

            int totalPage = (int) Math.ceil((double) count / limit);
            String requete1 = "SELECT numEntrant, dateEntrant, numCourrier, origine, nature, observation, destination FROM entrant limit " + (page - 1) * limit + " , " + limit;
            PreparedStatement pst = conn.prepareStatement(requete1);
            pst = conn.prepareStatement(requete1);
            rs = pst.executeQuery();
            while (rs.next()) {
                String b1 = rs.getString("numEntrant");
                System.out.println(b1);
                String b2 = rs.getString("dateEntrant");
                System.out.println(b2);
                String b3 = rs.getString("numCourrier");
                System.out.println(b3);
                String b4 = rs.getString("origine");
                System.out.println(b4);
                String b5 = rs.getString("nature");
                System.out.println(b5);
                String b6 = rs.getString("observation");
                System.out.println(b6);
                String b7 = rs.getString("destination");
                System.out.println(b7);
                model.addRow(new Object[]{b1, b2, b3, b4, b5, b6, b7});
            }
            rs.close();
            pst.close();
            pagination2.setPagegination(page, totalPage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataSortant(int page) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            DecimalFormat f = new DecimalFormat("#,##0.##");
            DefaultTableModel model = (DefaultTableModel) TableCourrierSortant.getModel();
            model.setRowCount(0);
            int limit = 12;

            // Correction de la requête SQL pour compter le nombre de lignes
            String requete = "SELECT COUNT(*) FROM sortant";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            pst.close();

            int totalPage = (int) Math.ceil((double) count / limit);
            String requete1 = "SELECT numSortant, dateSortant, nature, observation, destination FROM sortant limit " + (page - 1) * limit + " , " + limit;
            pst = conn.prepareStatement(requete1);
            rs = pst.executeQuery();
            while (rs.next()) {
                String b1 = rs.getString("numSortant");
                System.out.println(b1);
                String b2 = rs.getString("dateSortant");
                System.out.println(b2);
                String b3 = rs.getString("nature");
                System.out.println(b3);
                String b4 = rs.getString("observation");
                System.out.println(b4);
                String b5 = rs.getString("destination");
                System.out.println(b5);
                model.addRow(new Object[]{b1, b2, b3, b4, b5});
            }
            rs.close();
            pst.close();
            pagination3.setPagegination(page, totalPage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataArchive(int page) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            DecimalFormat f = new DecimalFormat("#,##0.##");
            DefaultTableModel model = (DefaultTableModel) TableArchive.getModel();
            model.setRowCount(0);
            int limit = 12;

            // Correction de la requête SQL pour compter le nombre de lignes
            String requete = "SELECT COUNT(*) FROM archive";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            pst.close();

            int totalPage = (int) Math.ceil((double) count / limit);
            String requete1 = "SELECT numArchive, numCourrier, dateArchive FROM archive limit " + (page - 1) * limit + " , " + limit;
            pst = conn.prepareStatement(requete1);
            rs = pst.executeQuery();
            while (rs.next()) {
                String b1 = rs.getString("numArchive");
                System.out.println(b1);
                String b2 = rs.getString("numCourrier");
                System.out.println(b2);
                String b3 = rs.getString("dateArchive");
                System.out.println(b3);
                model.addRow(new Object[]{b1, b2, b3});
            }
            rs.close();
            pst.close();
            pagination5.setPagegination(page, totalPage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataTraitement(int page) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            DecimalFormat f = new DecimalFormat("#,##0.##");
            DefaultTableModel model = (DefaultTableModel) TableTraitement.getModel();
            model.setRowCount(0);
            int limit = 12;

            // Correction de la requête SQL pour compter le nombre de lignes
            String requete = "SELECT COUNT(*) FROM traitement";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            pst.close();

            int totalPage = (int) Math.ceil((double) count / limit);
            String requete1 = "SELECT numTraitement, numCourrier, dateTraitement, nomPersonnel, status FROM traitement limit " + (page - 1) * limit + " , " + limit;
            pst = conn.prepareStatement(requete1);
            rs = pst.executeQuery();
            while (rs.next()) {
                String b1 = rs.getString("numTraitement");
                System.out.println(b1);
                String b2 = rs.getString("numCourrier");
                System.out.println(b2);
                String b3 = rs.getString("dateTraitement");
                System.out.println(b3);
                String b4 = rs.getString("nomPersonnel");
                System.out.println(b4);
                String b5 = rs.getString("status");
                System.out.println(b5);
                model.addRow(new Object[]{b1, b2, b3, b4, b5});
            }
            rs.close();
            pst.close();
            pagination4.setPagegination(page, totalPage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setColor(JPanel panel) {
        panel.setBackground(new Color(2, 132, 199));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(3, 105, 161));
    }

    public class ButtonDisabler {

        public static void disableAllButtons(Container container) {
            Component[] components = container.getComponents();
            for (Component component : components) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    button.setEnabled(false);
                } else if (component instanceof Container) {
                    // Récursion pour les conteneurs imbriqués
                    disableAllButtons((Container) component);
                }
            }
        }
    }

    //GlassPane
    public void setGlasspaness() {
        getRootPane().setGlassPane(new JComponent() {
            public void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        Header = new javax.swing.JPanel();
        iconminmaxclose = new javax.swing.JPanel();
        Buttonclose = new javax.swing.JPanel();
        CloseButton = new javax.swing.JLabel();
        Buttonmax = new javax.swing.JPanel();
        MaxButton = new javax.swing.JLabel();
        ButtonReduire = new javax.swing.JPanel();
        Reduire = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        Menuhide = new javax.swing.JPanel();
        ButtonHideMenu = new javax.swing.JLabel();
        JTraitement = new javax.swing.JPanel();
        Traite = new javax.swing.JLabel();
        JEntrant = new javax.swing.JPanel();
        Entrant = new javax.swing.JLabel();
        JBoard = new javax.swing.JPanel();
        Dashboar = new javax.swing.JLabel();
        JSortant = new javax.swing.JPanel();
        Sortant = new javax.swing.JLabel();
        JResponsable = new javax.swing.JPanel();
        Responsab = new javax.swing.JLabel();
        JArch = new javax.swing.JPanel();
        Archive = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        deconn = new javax.swing.JLabel();
        Dashboard = new javax.swing.JPanel();
        Jp1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabel18 = new javax.swing.JLabel();
        LabelEntrant = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        LabelSortant = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jLabel22 = new javax.swing.JLabel();
        LabelPersonnel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        DonneTrimestre = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        Panel1 = new javax.swing.JPanel();
        Panel = new javax.swing.JPanel();
        Jp2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        TableResponsable = new javax.swing.JTable();
        txtrecherche = new javax.swing.JTextField();
        btnrecherche = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        pagination1 = new pagination.Pagination();
        jPanel2 = new javax.swing.JPanel();
        AjoutResp = new javax.swing.JButton();
        ModifResp = new javax.swing.JButton();
        voirResp = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Jp3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        scroll1 = new javax.swing.JScrollPane();
        TableCourrierEntrant = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        dateDebut = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        dateFin = new com.toedter.calendar.JDateChooser();
        Rechercher = new javax.swing.JButton();
        Termine = new javax.swing.JLabel();
        pagination2 = new pagination.Pagination();
        jPanel5 = new javax.swing.JPanel();
        AjoutEntrant = new javax.swing.JButton();
        ModifEntrant = new javax.swing.JButton();
        VoirEntrant = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Jp4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        scroll2 = new javax.swing.JScrollPane();
        TableCourrierSortant = new javax.swing.JTable();
        pagination3 = new pagination.Pagination();
        jPanel19 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        AjoutSortant = new javax.swing.JButton();
        PDF = new javax.swing.JButton();
        VoirSortant = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        DateDebut = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        DateFin = new com.toedter.calendar.JDateChooser();
        Recherche = new javax.swing.JButton();
        Terminer = new javax.swing.JLabel();
        Jp5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        scroll3 = new javax.swing.JScrollPane();
        TableTraitement = new javax.swing.JTable();
        pagination4 = new pagination.Pagination();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        envoyer = new javax.swing.JButton();
        ModifTraite = new javax.swing.JButton();
        VoirTraite = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        Tous = new javax.swing.JRadioButton();
        Envoyer = new javax.swing.JRadioButton();
        nonEnvoyer = new javax.swing.JRadioButton();
        Notification = new vue.Button();
        notifcompte = new vue.Button();
        Jp7 = new javax.swing.JPanel();
        Jp10 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        scroll4 = new javax.swing.JScrollPane();
        TableArchive = new javax.swing.JTable();
        pagination5 = new pagination.Pagination();
        jPanel26 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        TraiteArchive = new javax.swing.JButton();
        VoirArchive = new javax.swing.JButton();
        dateStart = new com.toedter.calendar.JDateChooser();
        dateEnd = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        Cherche = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        terminer = new javax.swing.JLabel();
        hidemenu = new javax.swing.JPanel();
        setting = new javax.swing.JPanel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Header.setBackground(new java.awt.Color(234, 236, 239));
        Header.setPreferredSize(new java.awt.Dimension(800, 35));
        Header.setVerifyInputWhenFocusTarget(false);
        Header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                HeaderMouseDragged(evt);
            }
        });
        Header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HeaderMousePressed(evt);
            }
        });

        iconminmaxclose.setBackground(new java.awt.Color(102, 153, 255));
        iconminmaxclose.setPreferredSize(new java.awt.Dimension(150, 50));

        Buttonclose.setBackground(new java.awt.Color(234, 236, 239));

        CloseButton.setBackground(new java.awt.Color(255, 255, 255));
        CloseButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-multiplier-50.png"))); // NOI18N
        CloseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CloseButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CloseButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ButtoncloseLayout = new javax.swing.GroupLayout(Buttonclose);
        Buttonclose.setLayout(ButtoncloseLayout);
        ButtoncloseLayout.setHorizontalGroup(
            ButtoncloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ButtoncloseLayout.setVerticalGroup(
            ButtoncloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CloseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        Buttonmax.setBackground(new java.awt.Color(234, 236, 239));

        MaxButton.setBackground(new java.awt.Color(255, 255, 255));
        MaxButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MaxButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-agrandir-30.png"))); // NOI18N
        MaxButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MaxButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MaxButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MaxButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ButtonmaxLayout = new javax.swing.GroupLayout(Buttonmax);
        Buttonmax.setLayout(ButtonmaxLayout);
        ButtonmaxLayout.setHorizontalGroup(
            ButtonmaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MaxButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ButtonmaxLayout.setVerticalGroup(
            ButtonmaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MaxButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ButtonReduire.setBackground(new java.awt.Color(234, 236, 239));

        Reduire.setBackground(new java.awt.Color(255, 255, 255));
        Reduire.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reduire.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-moins-20.png"))); // NOI18N
        Reduire.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReduireMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReduireMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReduireMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ButtonReduireLayout = new javax.swing.GroupLayout(ButtonReduire);
        ButtonReduire.setLayout(ButtonReduireLayout);
        ButtonReduireLayout.setHorizontalGroup(
            ButtonReduireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonReduireLayout.createSequentialGroup()
                .addComponent(Reduire, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ButtonReduireLayout.setVerticalGroup(
            ButtonReduireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Reduire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout iconminmaxcloseLayout = new javax.swing.GroupLayout(iconminmaxclose);
        iconminmaxclose.setLayout(iconminmaxcloseLayout);
        iconminmaxcloseLayout.setHorizontalGroup(
            iconminmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconminmaxcloseLayout.createSequentialGroup()
                .addComponent(ButtonReduire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Buttonmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Buttonclose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        iconminmaxcloseLayout.setVerticalGroup(
            iconminmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iconminmaxcloseLayout.createSequentialGroup()
                .addGroup(iconminmaxcloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ButtonReduire, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Buttonmax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Buttonclose, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("GESTION DES COURRIERS");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 613, Short.MAX_VALUE)
                .addComponent(iconminmaxclose, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconminmaxclose, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(Header, java.awt.BorderLayout.PAGE_START);

        Menu.setPreferredSize(new java.awt.Dimension(270, 450));
        Menu.setLayout(new java.awt.BorderLayout());

        Menuhide.setBackground(new java.awt.Color(3, 105, 161));

        ButtonHideMenu.setBackground(new java.awt.Color(0, 51, 51));
        ButtonHideMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ButtonHideMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ButtonHideMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonHideMenuMouseClicked(evt);
            }
        });

        JTraitement.setBackground(new java.awt.Color(3, 105, 161));

        Traite.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Traite.setForeground(new java.awt.Color(255, 255, 255));
        Traite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-nouveau-message.png"))); // NOI18N
        Traite.setText("     Traitement");
        Traite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Traite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TraiteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JTraitementLayout = new javax.swing.GroupLayout(JTraitement);
        JTraitement.setLayout(JTraitementLayout);
        JTraitementLayout.setHorizontalGroup(
            JTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JTraitementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Traite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JTraitementLayout.setVerticalGroup(
            JTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Traite, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JEntrant.setBackground(new java.awt.Color(3, 105, 161));

        Entrant.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Entrant.setForeground(new java.awt.Color(255, 255, 255));
        Entrant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-télécharger-le-courrier.png"))); // NOI18N
        Entrant.setText("     Courrier entrants");
        Entrant.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Entrant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EntrantMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JEntrantLayout = new javax.swing.GroupLayout(JEntrant);
        JEntrant.setLayout(JEntrantLayout);
        JEntrantLayout.setHorizontalGroup(
            JEntrantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JEntrantLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Entrant, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );
        JEntrantLayout.setVerticalGroup(
            JEntrantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Entrant, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );

        JBoard.setBackground(new java.awt.Color(2, 132, 199));

        Dashboar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Dashboar.setForeground(new java.awt.Color(255, 255, 255));
        Dashboar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-tableau-de-bord.png"))); // NOI18N
        Dashboar.setText("      Tableau de bord");
        Dashboar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Dashboar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DashboarMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DashboarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout JBoardLayout = new javax.swing.GroupLayout(JBoard);
        JBoard.setLayout(JBoardLayout);
        JBoardLayout.setHorizontalGroup(
            JBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Dashboar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JBoardLayout.setVerticalGroup(
            JBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Dashboar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JSortant.setBackground(new java.awt.Color(3, 105, 161));

        Sortant.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Sortant.setForeground(new java.awt.Color(255, 255, 255));
        Sortant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-envoyer-un-mail-50 (1).png"))); // NOI18N
        Sortant.setText("     Courrier sortants");
        Sortant.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Sortant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SortantMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JSortantLayout = new javax.swing.GroupLayout(JSortant);
        JSortant.setLayout(JSortantLayout);
        JSortantLayout.setHorizontalGroup(
            JSortantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JSortantLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Sortant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JSortantLayout.setVerticalGroup(
            JSortantLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Sortant, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JResponsable.setBackground(new java.awt.Color(3, 105, 161));

        Responsab.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Responsab.setForeground(new java.awt.Color(255, 255, 255));
        Responsab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-courrier-partagé-64.png"))); // NOI18N
        Responsab.setText("     Personnels");
        Responsab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Responsab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResponsabMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JResponsableLayout = new javax.swing.GroupLayout(JResponsable);
        JResponsable.setLayout(JResponsableLayout);
        JResponsableLayout.setHorizontalGroup(
            JResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JResponsableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Responsab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JResponsableLayout.setVerticalGroup(
            JResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Responsab, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JArch.setBackground(new java.awt.Color(3, 105, 161));

        Archive.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Archive.setForeground(new java.awt.Color(255, 255, 255));
        Archive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-courriel-ouvert.png"))); // NOI18N
        Archive.setText("     Archive");
        Archive.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Archive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ArchiveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JArchLayout = new javax.swing.GroupLayout(JArch);
        JArch.setLayout(JArchLayout);
        JArchLayout.setHorizontalGroup(
            JArchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JArchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Archive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JArchLayout.setVerticalGroup(
            JArchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JArchLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Archive, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/DrenStart.png"))); // NOI18N

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        deconn.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        deconn.setForeground(new java.awt.Color(255, 255, 255));
        deconn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-déconnexion.png"))); // NOI18N
        deconn.setText("    Deconnexion");
        deconn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deconn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deconnMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deconnMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                deconnMousePressed(evt);
            }
        });

        javax.swing.GroupLayout MenuhideLayout = new javax.swing.GroupLayout(Menuhide);
        Menuhide.setLayout(MenuhideLayout);
        MenuhideLayout.setHorizontalGroup(
            MenuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTraitement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JEntrant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JSortant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JResponsable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JArch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MenuhideLayout.createSequentialGroup()
                .addComponent(ButtonHideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(MenuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuhideLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(0, 60, Short.MAX_VALUE))
            .addGroup(MenuhideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deconn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MenuhideLayout.setVerticalGroup(
            MenuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuhideLayout.createSequentialGroup()
                .addGroup(MenuhideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuhideLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ButtonHideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MenuhideLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(JBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JEntrant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JSortant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JTraitement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JArch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(deconn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        Menu.add(Menuhide, java.awt.BorderLayout.CENTER);

        getContentPane().add(Menu, java.awt.BorderLayout.LINE_START);

        Dashboard.setBackground(new java.awt.Color(255, 255, 255));

        Jp1.setBackground(new java.awt.Color(234, 236, 239));
        Jp1.setPreferredSize(new java.awt.Dimension(646, 645));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(646, 61));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(3, 105, 161));
        jLabel15.setText("Tableau de bord");
        jPanel9.add(jLabel15, new java.awt.GridBagConstraints());

        jPanel20.setBackground(new java.awt.Color(234, 236, 239));
        jPanel20.setLayout(new java.awt.GridLayout(1, 4, 10, 0));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        kGradientPanel1.setkEndColor(new java.awt.Color(5, 45, 19));
        kGradientPanel1.setkStartColor(new java.awt.Color(34, 197, 96));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Design_sans_titre-removebg-preview.png"))); // NOI18N
        jLabel18.setRequestFocusEnabled(false);
        jLabel18.setVerifyInputWhenFocusTarget(false);

        LabelEntrant.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        LabelEntrant.setForeground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText(" Courriers Entrants");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEntrant, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(LabelEntrant, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel20.add(jPanel24);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        kGradientPanel2.setkEndColor(new java.awt.Color(59, 9, 96));
        kGradientPanel2.setkStartColor(new java.awt.Color(168, 80, 248));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Design_sans_titre-removebg-preview.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText(" Courriers Sortants");

        LabelSortant.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LabelSortant.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelSortant, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(LabelSortant, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel20.add(jPanel29);

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        kGradientPanel3.setkEndColor(new java.awt.Color(23, 39, 86));
        kGradientPanel3.setkStartColor(new java.awt.Color(59, 130, 246));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Design_sans_titre-removebg-preview.png"))); // NOI18N

        LabelPersonnel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LabelPersonnel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText(" Personnels");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelPersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(LabelPersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel20.add(jPanel30);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel21Layout = new java.awt.GridBagLayout();
        jPanel21Layout.columnWidths = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0};
        jPanel21Layout.rowHeights = new int[] {0};
        jPanel21.setLayout(jPanel21Layout);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(3, 105, 161));
        jLabel10.setText("Statistiques regroupées");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel21.add(jLabel10, gridBagConstraints);

        DonneTrimestre.setBackground(new java.awt.Color(255, 255, 255));
        DonneTrimestre.setForeground(new java.awt.Color(0, 0, 0));
        DonneTrimestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exportation des donnee par trimestre", "1er Trimestre", "2éme Trimestre", "3éme Trimestre" }));
        DonneTrimestre.setToolTipText("");
        DonneTrimestre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        DonneTrimestre.setFocusable(false);
        DonneTrimestre.setOpaque(true);
        DonneTrimestre.setRequestFocusEnabled(false);
        DonneTrimestre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DonneTrimestreMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 5;
        jPanel21.add(DonneTrimestre, gridBagConstraints);

        jPanel22.setBackground(new java.awt.Color(234, 236, 239));
        jPanel22.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        Panel1.setBackground(new java.awt.Color(204, 51, 0));
        Panel1.setLayout(new java.awt.BorderLayout());
        jPanel22.add(Panel1);

        Panel.setBackground(new java.awt.Color(0, 153, 51));
        Panel.setLayout(new java.awt.BorderLayout());
        jPanel22.add(Panel);

        javax.swing.GroupLayout Jp1Layout = new javax.swing.GroupLayout(Jp1);
        Jp1.setLayout(Jp1Layout);
        Jp1Layout.setHorizontalGroup(
            Jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        Jp1Layout.setVerticalGroup(
            Jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jp1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        Jp2.setBackground(new java.awt.Color(234, 236, 239));

        jPanel1.setBackground(new java.awt.Color(234, 236, 239));

        scroll.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        TableResponsable.setBackground(new java.awt.Color(234, 236, 239));
        TableResponsable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableResponsable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IM", "NOM", "PRENOM", "POSTE", "CIN", "TELEPHONE", "ADRESSE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableResponsable.setFocusable(false);
        TableResponsable.setGridColor(new java.awt.Color(204, 204, 204));
        TableResponsable.setRowHeight(30);
        TableResponsable.setShowGrid(false);
        TableResponsable.setShowHorizontalLines(true);
        TableResponsable.setSurrendersFocusOnKeystroke(true);
        TableResponsable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableResponsableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableResponsableMouseReleased(evt);
            }
        });
        scroll.setViewportView(TableResponsable);
        if (TableResponsable.getColumnModel().getColumnCount() > 0) {
            TableResponsable.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        txtrecherche.setBackground(new java.awt.Color(234, 236, 239));
        txtrecherche.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtrecherche.setForeground(new java.awt.Color(0, 0, 0));
        txtrecherche.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));
        txtrecherche.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        txtrecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercheKeyReleased(evt);
            }
        });

        btnrecherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        btnrecherche.setBorder(null);
        btnrecherche.setBorderPainted(false);
        btnrecherche.setContentAreaFilled(false);
        btnrecherche.setFocusPainted(false);
        btnrecherche.setFocusable(false);
        btnrecherche.setRequestFocusEnabled(false);
        btnrecherche.setRolloverEnabled(false);
        btnrecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrechercheActionPerformed(evt);
            }
        });

        pagination1.setBackground(new java.awt.Color(234, 236, 239));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrecherche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jPanel2.setBackground(new java.awt.Color(234, 236, 239));
        java.awt.GridBagLayout jPanel2Layout1 = new java.awt.GridBagLayout();
        jPanel2Layout1.columnWidths = new int[] {0, 15, 0, 15, 0};
        jPanel2Layout1.rowHeights = new int[] {0};
        jPanel2.setLayout(jPanel2Layout1);

        AjoutResp.setBackground(new java.awt.Color(53, 146, 197));
        AjoutResp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjoutResp.setForeground(new java.awt.Color(255, 255, 255));
        AjoutResp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/AjoutPersonnel.png"))); // NOI18N
        AjoutResp.setBorder(null);
        AjoutResp.setBorderPainted(false);
        AjoutResp.setContentAreaFilled(false);
        AjoutResp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AjoutResp.setFocusPainted(false);
        AjoutResp.setFocusable(false);
        AjoutResp.setRequestFocusEnabled(false);
        AjoutResp.setRolloverEnabled(false);
        AjoutResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutRespActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(AjoutResp, gridBagConstraints);

        ModifResp.setBackground(new java.awt.Color(73, 154, 83));
        ModifResp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ModifResp.setForeground(new java.awt.Color(255, 255, 255));
        ModifResp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Editer.png"))); // NOI18N
        ModifResp.setBorder(null);
        ModifResp.setBorderPainted(false);
        ModifResp.setContentAreaFilled(false);
        ModifResp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModifResp.setFocusPainted(false);
        ModifResp.setFocusable(false);
        ModifResp.setRequestFocusEnabled(false);
        ModifResp.setRolloverEnabled(false);
        ModifResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifRespActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel2.add(ModifResp, gridBagConstraints);

        voirResp.setBackground(new java.awt.Color(240, 163, 51));
        voirResp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        voirResp.setForeground(new java.awt.Color(255, 255, 255));
        voirResp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/voir.png"))); // NOI18N
        voirResp.setBorder(null);
        voirResp.setBorderPainted(false);
        voirResp.setContentAreaFilled(false);
        voirResp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        voirResp.setFocusPainted(false);
        voirResp.setFocusable(false);
        voirResp.setRequestFocusEnabled(false);
        voirResp.setRolloverEnabled(false);
        voirResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voirRespActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel2.add(voirResp, gridBagConstraints);

        jPanel13.setPreferredSize(new java.awt.Dimension(0, 100));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(646, 61));
        jPanel14.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(3, 105, 161));
        jLabel2.setText("Liste des Personnels");
        jPanel14.add(jLabel2, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout Jp2Layout = new javax.swing.GroupLayout(Jp2);
        Jp2.setLayout(Jp2Layout);
        Jp2Layout.setHorizontalGroup(
            Jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jp2Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(Jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        Jp2Layout.setVerticalGroup(
            Jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp2Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(Jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jp2Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        Jp3.setBackground(new java.awt.Color(234, 236, 239));

        jPanel4.setBackground(new java.awt.Color(234, 236, 239));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setToolTipText("");

        scroll1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));
        scroll1.setFocusable(false);

        TableCourrierEntrant.setBackground(new java.awt.Color(234, 236, 239));
        TableCourrierEntrant.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableCourrierEntrant.setForeground(new java.awt.Color(0, 0, 0));
        TableCourrierEntrant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "N°", "Date", "Numero du Courrier", "Origine", "Nature du Courrier", "Observation", "Destination"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCourrierEntrant.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        TableCourrierEntrant.setFocusable(false);
        TableCourrierEntrant.setGridColor(new java.awt.Color(204, 204, 204));
        TableCourrierEntrant.setRowHeight(30);
        TableCourrierEntrant.setShowGrid(false);
        TableCourrierEntrant.setShowHorizontalLines(true);
        TableCourrierEntrant.setSurrendersFocusOnKeystroke(true);
        TableCourrierEntrant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCourrierEntrantMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableCourrierEntrantMouseReleased(evt);
            }
        });
        scroll1.setViewportView(TableCourrierEntrant);
        if (TableCourrierEntrant.getColumnModel().getColumnCount() > 0) {
            TableCourrierEntrant.getColumnModel().getColumn(0).setResizable(false);
            TableCourrierEntrant.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableCourrierEntrant.getColumnModel().getColumn(1).setResizable(false);
            TableCourrierEntrant.getColumnModel().getColumn(2).setResizable(false);
            TableCourrierEntrant.getColumnModel().getColumn(3).setResizable(false);
            TableCourrierEntrant.getColumnModel().getColumn(4).setResizable(false);
            TableCourrierEntrant.getColumnModel().getColumn(5).setResizable(false);
            TableCourrierEntrant.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Debut");

        dateDebut.setBackground(new java.awt.Color(234, 236, 239));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Fin");

        dateFin.setBackground(new java.awt.Color(234, 236, 239));

        Rechercher.setBackground(new java.awt.Color(255, 255, 255));
        Rechercher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        Rechercher.setBorder(null);
        Rechercher.setBorderPainted(false);
        Rechercher.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Rechercher.setFocusable(false);
        Rechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RechercherActionPerformed(evt);
            }
        });

        Termine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-fermer-30.png"))); // NOI18N
        Termine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TermineMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(scroll1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Termine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(Rechercher, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(pagination2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(dateDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(dateFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Rechercher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Termine, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagination2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jPanel5.setBackground(new java.awt.Color(234, 236, 239));
        jPanel5.setPreferredSize(new java.awt.Dimension(561, 36));
        java.awt.GridBagLayout jPanel5Layout = new java.awt.GridBagLayout();
        jPanel5Layout.columnWidths = new int[] {0, 10, 0, 10, 0};
        jPanel5Layout.rowHeights = new int[] {0};
        jPanel5.setLayout(jPanel5Layout);

        AjoutEntrant.setBackground(new java.awt.Color(53, 146, 197));
        AjoutEntrant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjoutEntrant.setForeground(new java.awt.Color(255, 255, 255));
        AjoutEntrant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/AjoutCourrier.png"))); // NOI18N
        AjoutEntrant.setBorder(null);
        AjoutEntrant.setBorderPainted(false);
        AjoutEntrant.setContentAreaFilled(false);
        AjoutEntrant.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AjoutEntrant.setFocusPainted(false);
        AjoutEntrant.setFocusable(false);
        AjoutEntrant.setRequestFocusEnabled(false);
        AjoutEntrant.setRolloverEnabled(false);
        AjoutEntrant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AjoutEntrantMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel5.add(AjoutEntrant, gridBagConstraints);

        ModifEntrant.setBackground(new java.awt.Color(199, 85, 80));
        ModifEntrant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ModifEntrant.setForeground(new java.awt.Color(255, 255, 255));
        ModifEntrant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Editer.png"))); // NOI18N
        ModifEntrant.setBorder(null);
        ModifEntrant.setBorderPainted(false);
        ModifEntrant.setContentAreaFilled(false);
        ModifEntrant.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModifEntrant.setFocusPainted(false);
        ModifEntrant.setFocusable(false);
        ModifEntrant.setRequestFocusEnabled(false);
        ModifEntrant.setRolloverEnabled(false);
        ModifEntrant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifEntrantActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel5.add(ModifEntrant, gridBagConstraints);

        VoirEntrant.setBackground(new java.awt.Color(240, 163, 51));
        VoirEntrant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        VoirEntrant.setForeground(new java.awt.Color(255, 255, 255));
        VoirEntrant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/voir.png"))); // NOI18N
        VoirEntrant.setBorder(null);
        VoirEntrant.setBorderPainted(false);
        VoirEntrant.setContentAreaFilled(false);
        VoirEntrant.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VoirEntrant.setFocusPainted(false);
        VoirEntrant.setFocusable(false);
        VoirEntrant.setRequestFocusEnabled(false);
        VoirEntrant.setRolloverEnabled(false);
        VoirEntrant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoirEntrantActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel5.add(VoirEntrant, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(646, 61));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(3, 105, 161));
        jLabel6.setText("Liste des Courrier entrants");
        jPanel3.add(jLabel6, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout Jp3Layout = new javax.swing.GroupLayout(Jp3);
        Jp3.setLayout(Jp3Layout);
        Jp3Layout.setHorizontalGroup(
            Jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
            .addGroup(Jp3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        Jp3Layout.setVerticalGroup(
            Jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp3Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        Jp4.setBackground(new java.awt.Color(234, 236, 239));
        Jp4.setForeground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(234, 236, 239));

        scroll2.setBackground(new java.awt.Color(255, 255, 255));
        scroll2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));
        scroll2.setFocusable(false);
        scroll2.setOpaque(false);

        TableCourrierSortant.setBackground(new java.awt.Color(234, 236, 239));
        TableCourrierSortant.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableCourrierSortant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "N°", "Date", "Nature du Courrier", "Observation", "Destination"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCourrierSortant.setFocusable(false);
        TableCourrierSortant.setGridColor(new java.awt.Color(204, 204, 204));
        TableCourrierSortant.setRowHeight(30);
        TableCourrierSortant.setShowGrid(false);
        TableCourrierSortant.setShowHorizontalLines(true);
        TableCourrierSortant.setSurrendersFocusOnKeystroke(true);
        TableCourrierSortant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCourrierSortantMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableCourrierSortantMouseReleased(evt);
            }
        });
        scroll2.setViewportView(TableCourrierSortant);
        if (TableCourrierSortant.getColumnModel().getColumnCount() > 0) {
            TableCourrierSortant.getColumnModel().getColumn(0).setResizable(false);
            TableCourrierSortant.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableCourrierSortant.getColumnModel().getColumn(1).setResizable(false);
            TableCourrierSortant.getColumnModel().getColumn(2).setResizable(false);
            TableCourrierSortant.getColumnModel().getColumn(3).setResizable(false);
            TableCourrierSortant.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 228, Short.MAX_VALUE)
                        .addComponent(pagination3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 227, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(scroll2, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagination3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setForeground(new java.awt.Color(3, 105, 161));
        jPanel19.setPreferredSize(new java.awt.Dimension(0, 61));
        jPanel19.setLayout(new java.awt.GridBagLayout());

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(3, 105, 161));
        jLabel8.setText("Liste des Courrier sortants");
        jPanel19.add(jLabel8, new java.awt.GridBagConstraints());

        jPanel8.setBackground(new java.awt.Color(234, 236, 239));
        jPanel8.setPreferredSize(new java.awt.Dimension(647, 36));
        java.awt.GridBagLayout jPanel8Layout = new java.awt.GridBagLayout();
        jPanel8Layout.columnWidths = new int[] {0, 10, 0, 10, 0};
        jPanel8Layout.rowHeights = new int[] {0};
        jPanel8.setLayout(jPanel8Layout);

        AjoutSortant.setBackground(new java.awt.Color(53, 146, 197));
        AjoutSortant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AjoutSortant.setForeground(new java.awt.Color(255, 255, 255));
        AjoutSortant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/AjoutCourrier.png"))); // NOI18N
        AjoutSortant.setBorder(null);
        AjoutSortant.setBorderPainted(false);
        AjoutSortant.setContentAreaFilled(false);
        AjoutSortant.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AjoutSortant.setFocusPainted(false);
        AjoutSortant.setFocusable(false);
        AjoutSortant.setRequestFocusEnabled(false);
        AjoutSortant.setRolloverEnabled(false);
        AjoutSortant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AjoutSortantMouseClicked(evt);
            }
        });
        AjoutSortant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutSortantActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel8.add(AjoutSortant, gridBagConstraints);

        PDF.setBackground(new java.awt.Color(199, 85, 80));
        PDF.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PDF.setForeground(new java.awt.Color(0, 0, 0));
        PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buttonDownload.png"))); // NOI18N
        PDF.setBorder(null);
        PDF.setBorderPainted(false);
        PDF.setContentAreaFilled(false);
        PDF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PDF.setFocusPainted(false);
        PDF.setPreferredSize(new java.awt.Dimension(88, 32));
        PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PDFActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel8.add(PDF, gridBagConstraints);

        VoirSortant.setBackground(new java.awt.Color(240, 163, 51));
        VoirSortant.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        VoirSortant.setForeground(new java.awt.Color(255, 255, 255));
        VoirSortant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/voir.png"))); // NOI18N
        VoirSortant.setBorder(null);
        VoirSortant.setBorderPainted(false);
        VoirSortant.setContentAreaFilled(false);
        VoirSortant.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VoirSortant.setFocusPainted(false);
        VoirSortant.setFocusable(false);
        VoirSortant.setPreferredSize(new java.awt.Dimension(88, 40));
        VoirSortant.setRequestFocusEnabled(false);
        VoirSortant.setRolloverEnabled(false);
        VoirSortant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoirSortantActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel8.add(VoirSortant, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Debut");

        DateDebut.setBackground(new java.awt.Color(234, 236, 239));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Fin");

        DateFin.setBackground(new java.awt.Color(234, 236, 239));

        Recherche.setBackground(new java.awt.Color(255, 255, 255));
        Recherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        Recherche.setBorder(null);
        Recherche.setBorderPainted(false);
        Recherche.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Recherche.setFocusable(false);
        Recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RechercheActionPerformed(evt);
            }
        });

        Terminer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-fermer-30.png"))); // NOI18N
        Terminer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TerminerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Jp4Layout = new javax.swing.GroupLayout(Jp4);
        Jp4.setLayout(Jp4Layout);
        Jp4Layout.setHorizontalGroup(
            Jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Terminer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DateDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Recherche, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
            .addGroup(Jp4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Jp4Layout.setVerticalGroup(
            Jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jp4Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(Jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DateDebut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DateFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Recherche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Terminer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        Jp5.setBackground(new java.awt.Color(234, 236, 239));

        jPanel10.setBackground(new java.awt.Color(234, 236, 239));

        scroll3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));

        TableTraitement.setBackground(new java.awt.Color(234, 236, 239));
        TableTraitement.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableTraitement.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Numero Traitement", "IM", "Date", "Numer du Courrier ", "Design"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableTraitement.setFocusable(false);
        TableTraitement.setGridColor(new java.awt.Color(204, 204, 204));
        TableTraitement.setRowHeight(30);
        TableTraitement.setShowGrid(false);
        TableTraitement.setShowHorizontalLines(true);
        TableTraitement.setSurrendersFocusOnKeystroke(true);
        TableTraitement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableTraitementMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableTraitementMouseReleased(evt);
            }
        });
        scroll3.setViewportView(TableTraitement);
        if (TableTraitement.getColumnModel().getColumnCount() > 0) {
            TableTraitement.getColumnModel().getColumn(0).setResizable(false);
            TableTraitement.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableTraitement.getColumnModel().getColumn(1).setResizable(false);
            TableTraitement.getColumnModel().getColumn(2).setResizable(false);
            TableTraitement.getColumnModel().getColumn(3).setResizable(false);
            TableTraitement.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 228, Short.MAX_VALUE)
                        .addComponent(pagination4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 227, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(scroll3, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(pagination4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setPreferredSize(new java.awt.Dimension(463, 61));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(3, 105, 161));
        jLabel11.setText("Liste des dossiers a traiter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 150, 14, 172);
        jPanel12.add(jLabel11, gridBagConstraints);

        jPanel11.setBackground(new java.awt.Color(234, 236, 239));
        jPanel11.setPreferredSize(new java.awt.Dimension(494, 36));
        java.awt.GridBagLayout jPanel11Layout = new java.awt.GridBagLayout();
        jPanel11Layout.columnWidths = new int[] {0, 10, 0, 10, 0};
        jPanel11Layout.rowHeights = new int[] {10};
        jPanel11Layout.columnWeights = new double[] {0.0};
        jPanel11Layout.rowWeights = new double[] {3.0};
        jPanel11.setLayout(jPanel11Layout);

        envoyer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        envoyer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Envoyer.png"))); // NOI18N
        envoyer.setBorder(null);
        envoyer.setBorderPainted(false);
        envoyer.setContentAreaFilled(false);
        envoyer.setFocusPainted(false);
        envoyer.setFocusable(false);
        envoyer.setRequestFocusEnabled(false);
        envoyer.setRolloverEnabled(false);
        envoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envoyerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel11.add(envoyer, gridBagConstraints);

        ModifTraite.setBackground(new java.awt.Color(199, 85, 80));
        ModifTraite.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ModifTraite.setForeground(new java.awt.Color(255, 255, 255));
        ModifTraite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Editer.png"))); // NOI18N
        ModifTraite.setBorder(null);
        ModifTraite.setBorderPainted(false);
        ModifTraite.setContentAreaFilled(false);
        ModifTraite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ModifTraite.setFocusPainted(false);
        ModifTraite.setFocusable(false);
        ModifTraite.setPreferredSize(new java.awt.Dimension(88, 32));
        ModifTraite.setRequestFocusEnabled(false);
        ModifTraite.setRolloverEnabled(false);
        ModifTraite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifTraiteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel11.add(ModifTraite, gridBagConstraints);

        VoirTraite.setBackground(new java.awt.Color(240, 163, 51));
        VoirTraite.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        VoirTraite.setForeground(new java.awt.Color(255, 255, 255));
        VoirTraite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/voir.png"))); // NOI18N
        VoirTraite.setBorder(null);
        VoirTraite.setBorderPainted(false);
        VoirTraite.setContentAreaFilled(false);
        VoirTraite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VoirTraite.setFocusPainted(false);
        VoirTraite.setFocusable(false);
        VoirTraite.setMinimumSize(new java.awt.Dimension(116, 46));
        VoirTraite.setPreferredSize(new java.awt.Dimension(88, 40));
        VoirTraite.setRequestFocusEnabled(false);
        VoirTraite.setRolloverEnabled(false);
        VoirTraite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoirTraiteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel11.add(VoirTraite, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Affichage :");

        Tous.setBackground(new java.awt.Color(234, 236, 239));
        buttonGroup1.add(Tous);
        Tous.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Tous.setForeground(new java.awt.Color(0, 0, 0));
        Tous.setText("Tous");
        Tous.setBorder(null);
        Tous.setFocusable(false);
        Tous.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TousMouseClicked(evt);
            }
        });
        Tous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TousActionPerformed(evt);
            }
        });

        Envoyer.setBackground(new java.awt.Color(234, 236, 239));
        buttonGroup1.add(Envoyer);
        Envoyer.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Envoyer.setForeground(new java.awt.Color(0, 0, 0));
        Envoyer.setText("Envoyer");
        Envoyer.setBorder(null);
        Envoyer.setFocusable(false);
        Envoyer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnvoyerMouseClicked(evt);
            }
        });
        Envoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnvoyerActionPerformed(evt);
            }
        });

        nonEnvoyer.setBackground(new java.awt.Color(234, 236, 239));
        buttonGroup1.add(nonEnvoyer);
        nonEnvoyer.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        nonEnvoyer.setForeground(new java.awt.Color(0, 0, 0));
        nonEnvoyer.setText("non Envoyer");
        nonEnvoyer.setBorder(null);
        nonEnvoyer.setFocusable(false);
        nonEnvoyer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nonEnvoyerMouseClicked(evt);
            }
        });
        nonEnvoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonEnvoyerActionPerformed(evt);
            }
        });

        Notification.setBackground(new java.awt.Color(204, 204, 204));
        Notification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/noti.png"))); // NOI18N
        Notification.setBorderPainted(false);
        Notification.setFocusable(false);
        Notification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NotificationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NotificationMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NotificationMousePressed(evt);
            }
        });
        Notification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotificationActionPerformed(evt);
            }
        });

        notifcompte.setBackground(new java.awt.Color(255, 102, 102));
        notifcompte.setBorder(null);
        notifcompte.setForeground(new java.awt.Color(255, 255, 255));
        notifcompte.setFocusPainted(false);
        notifcompte.setFocusable(false);

        javax.swing.GroupLayout Jp5Layout = new javax.swing.GroupLayout(Jp5);
        Jp5.setLayout(Jp5Layout);
        Jp5Layout.setHorizontalGroup(
            Jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Jp5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp5Layout.createSequentialGroup()
                        .addComponent(Notification, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(notifcompte, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Tous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Envoyer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nonEnvoyer)
                        .addGap(20, 20, 20))
                    .addGroup(Jp5Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        Jp5Layout.setVerticalGroup(
            Jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp5Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(Jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jp5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(Jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tous)
                            .addComponent(Envoyer)
                            .addComponent(nonEnvoyer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Jp5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Jp5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Notification, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(notifcompte, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        Jp7.setBackground(new java.awt.Color(255, 255, 255));

        Jp10.setBackground(new java.awt.Color(234, 236, 239));

        jPanel25.setBackground(new java.awt.Color(234, 236, 239));

        scroll4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(234, 236, 239), 1, true));

        TableArchive.setBackground(new java.awt.Color(234, 236, 239));
        TableArchive.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TableArchive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "numArchive", "numCourrier", "DateArchive"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableArchive.setFocusable(false);
        TableArchive.setGridColor(new java.awt.Color(204, 204, 204));
        TableArchive.setRowHeight(30);
        TableArchive.setShowGrid(false);
        TableArchive.setShowHorizontalLines(true);
        TableArchive.setSurrendersFocusOnKeystroke(true);
        TableArchive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableArchiveMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableArchiveMouseReleased(evt);
            }
        });
        scroll4.setViewportView(TableArchive);
        if (TableArchive.getColumnModel().getColumnCount() > 0) {
            TableArchive.getColumnModel().getColumn(0).setResizable(false);
            TableArchive.getColumnModel().getColumn(0).setPreferredWidth(30);
            TableArchive.getColumnModel().getColumn(1).setResizable(false);
            TableArchive.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addGap(0, 228, Short.MAX_VALUE)
                        .addComponent(pagination5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 227, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(scroll4, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagination5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setPreferredSize(new java.awt.Dimension(396, 61));
        jPanel26.setLayout(new java.awt.GridBagLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(3, 105, 161));
        jLabel16.setText("Dossier archivée");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 161, 16, 218);
        jPanel26.add(jLabel16, gridBagConstraints);

        jPanel27.setBackground(new java.awt.Color(234, 236, 239));
        jPanel27.setPreferredSize(new java.awt.Dimension(504, 36));
        java.awt.GridBagLayout jPanel27Layout = new java.awt.GridBagLayout();
        jPanel27Layout.columnWidths = new int[] {0, 10, 0};
        jPanel27Layout.rowHeights = new int[] {0};
        jPanel27.setLayout(jPanel27Layout);

        TraiteArchive.setBackground(new java.awt.Color(73, 154, 83));
        TraiteArchive.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TraiteArchive.setForeground(new java.awt.Color(0, 0, 0));
        TraiteArchive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Traiter.png"))); // NOI18N
        TraiteArchive.setBorder(null);
        TraiteArchive.setBorderPainted(false);
        TraiteArchive.setContentAreaFilled(false);
        TraiteArchive.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TraiteArchive.setFocusPainted(false);
        TraiteArchive.setFocusable(false);
        TraiteArchive.setRequestFocusEnabled(false);
        TraiteArchive.setRolloverEnabled(false);
        TraiteArchive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TraiteArchiveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel27.add(TraiteArchive, gridBagConstraints);

        VoirArchive.setBackground(new java.awt.Color(240, 163, 51));
        VoirArchive.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        VoirArchive.setForeground(new java.awt.Color(255, 255, 255));
        VoirArchive.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/voir.png"))); // NOI18N
        VoirArchive.setBorder(null);
        VoirArchive.setBorderPainted(false);
        VoirArchive.setContentAreaFilled(false);
        VoirArchive.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VoirArchive.setFocusPainted(false);
        VoirArchive.setFocusable(false);
        VoirArchive.setRequestFocusEnabled(false);
        VoirArchive.setRolloverEnabled(false);
        VoirArchive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoirArchiveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel27.add(VoirArchive, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Fin");

        Cherche.setBackground(new java.awt.Color(255, 255, 255));
        Cherche.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        Cherche.setBorder(null);
        Cherche.setBorderPainted(false);
        Cherche.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cherche.setFocusable(false);
        Cherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChercheActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Debut");

        terminer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-fermer-30.png"))); // NOI18N
        terminer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                terminerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Jp10Layout = new javax.swing.GroupLayout(Jp10);
        Jp10.setLayout(Jp10Layout);
        Jp10Layout.setHorizontalGroup(
            Jp10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(terminer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Cherche, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(Jp10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Jp10Layout.setVerticalGroup(
            Jp10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jp10Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(Jp10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateStart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cherche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(terminer, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout Jp7Layout = new javax.swing.GroupLayout(Jp7);
        Jp7.setLayout(Jp7Layout);
        Jp7Layout.setHorizontalGroup(
            Jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
            .addGroup(Jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Jp7Layout.setVerticalGroup(
            Jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
            .addGroup(Jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Jp1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addComponent(Jp1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Jp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(Dashboard, java.awt.BorderLayout.CENTER);

        hidemenu.setBackground(new java.awt.Color(0, 51, 51));
        hidemenu.setLayout(new java.awt.BorderLayout());

        setting.setBackground(new java.awt.Color(0, 51, 51));
        setting.setLayout(new java.awt.BorderLayout());
        hidemenu.add(setting, java.awt.BorderLayout.CENTER);

        getContentPane().add(hidemenu, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(915, 630));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    int x, y;

    public void changecolor(JPanel hover, Color rand) {
        hover.setBackground(rand);
    }

    public void clickmenu(JPanel h1, JPanel h2, int numberbool) {
        if (numberbool == 1) {
            h1.setBackground(new Color(25, 29, 74));
            h2.setBackground(new Color(102, 153, 255));
        } else {
            h1.setBackground(new Color(102, 153, 255));
            h2.setBackground(new Color(25, 29, 74));
        }
    }

    public void changeimage(JLabel button, String resourcheimag) {
        ImageIcon aimg = new ImageIcon(getClass().getResource(resourcheimag));
        button.setIcon(aimg);

    }

    public void hideshow(JPanel menushowhide, boolean dashboard, JLabel button) {
        if (dashboard == true) {
            menushowhide.setPreferredSize(new Dimension(50, menushowhide.getHeight()));
            changeimage(button, "/Icons/icons8-menu.png");
        } else {
            menushowhide.setPreferredSize(new Dimension(270, menushowhide.getHeight()));
            changeimage(button, "/Icons/icons8-flèche-30.png");
        }
    }

    private void CloseButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseEntered
        changecolor(Buttonclose, new Color(240, 50, 50));
    }//GEN-LAST:event_CloseButtonMouseEntered

    private void CloseButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseExited
        changecolor(Buttonclose, new Color(234, 236, 239));
    }//GEN-LAST:event_CloseButtonMouseExited

    private void CloseButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_CloseButtonMouseClicked

    private void MaxButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxButtonMouseEntered
        changecolor(Buttonmax, new Color(140, 130, 130));
    }//GEN-LAST:event_MaxButtonMouseEntered

    private void MaxButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxButtonMouseExited
        changecolor(Buttonmax, new Color(234, 236, 239));
    }//GEN-LAST:event_MaxButtonMouseExited

    private void MaxButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxButtonMouseClicked
        if (this.getExtendedState() != AcceuilPrincipaleUser.MAXIMIZED_BOTH) {
            this.setExtendedState(AcceuilPrincipaleUser.MAXIMIZED_BOTH);
        } else {
            this.setExtendedState(AcceuilPrincipaleUser.NORMAL);
        }
    }//GEN-LAST:event_MaxButtonMouseClicked

    private void ButtonHideMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonHideMenuMouseClicked
//        clickmenu(hidemenu, setting, 1);
//
//        // affiche et cache menu
//        if (a == true) {
//            hideshow(Menu, a, ButtonHideMenu);
//            SwingUtilities.updateComponentTreeUI(this);
//
//            // modifier icon
//            a = false;
//        } else {
//            hideshow(Menu, a, ButtonHideMenu);
//            SwingUtilities.updateComponentTreeUI(this);
//            a = true;
//        }

    }//GEN-LAST:event_ButtonHideMenuMouseClicked

    private void DashboarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboarMouseClicked
        setColor(JBoard);
        resetColor(JEntrant);
        resetColor(JSortant);
        resetColor(JResponsable);
        resetColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(true);
        Jp2.setVisible(false);
        Jp3.setVisible(false);
        Jp4.setVisible(false);
        Jp5.setVisible(false);
        Jp7.setVisible(false);
        Histogramme();
        Total_Table();
        DashEntrant();
        DashSortant();
        DashPersonnel();
    }//GEN-LAST:event_DashboarMouseClicked

    private void ResponsabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResponsabMouseClicked
        resetColor(JBoard);
        resetColor(JEntrant);
        resetColor(JSortant);
        setColor(JResponsable);
        resetColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(false);
        Jp2.setVisible(true);
        Jp3.setVisible(false);
        Jp4.setVisible(false);
        Jp5.setVisible(false);
        Jp7.setVisible(false);
    }//GEN-LAST:event_ResponsabMouseClicked

    private void EntrantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntrantMouseClicked
        resetColor(JBoard);
        setColor(JEntrant);
        resetColor(JSortant);
        resetColor(JResponsable);
        resetColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(false);
        Jp2.setVisible(false);
        Jp3.setVisible(true);
        Jp4.setVisible(false);
        Jp5.setVisible(false);
        Jp7.setVisible(false);
    }//GEN-LAST:event_EntrantMouseClicked

    private void SortantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SortantMouseClicked
        resetColor(JBoard);
        resetColor(JEntrant);
        setColor(JSortant);
        resetColor(JResponsable);
        resetColor(JTraitement);
        resetColor(JArch);
        Jp1.setVisible(false);
        Jp2.setVisible(false);
        Jp3.setVisible(false);
        Jp4.setVisible(true);
        Jp5.setVisible(false);
    }//GEN-LAST:event_SortantMouseClicked

    private void ReduireMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReduireMouseClicked
        this.setState(AcceuilPrincipaleUser.ICONIFIED);
    }//GEN-LAST:event_ReduireMouseClicked

    private void ReduireMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReduireMouseEntered
        changecolor(ButtonReduire, new Color(140, 130, 130));
    }//GEN-LAST:event_ReduireMouseEntered

    private void ReduireMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReduireMouseExited
        changecolor(ButtonReduire, new Color(234, 236, 239));
    }//GEN-LAST:event_ReduireMouseExited

    private void TraiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TraiteMouseClicked
        resetColor(JBoard);
        resetColor(JEntrant);
        resetColor(JSortant);
        resetColor(JResponsable);
        setColor(JTraitement);
        resetColor(JArch);
        compterCourriersArchives();
        Jp1.setVisible(false);
        Jp2.setVisible(false);
        Jp3.setVisible(false);
        Jp4.setVisible(false);
        Jp5.setVisible(true);
        Jp7.setVisible(false);
    }//GEN-LAST:event_TraiteMouseClicked

    private void DashboarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DashboarMousePressed

    private void AjoutSortantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutSortantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AjoutSortantActionPerformed

    private void DashboarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_DashboarMouseEntered

    private void AjoutSortantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjoutSortantMouseClicked
        AjoutSortant As = new AjoutSortant();
        As.setSort(TableCourrierSortant);
        As.setVisible(true);
    }//GEN-LAST:event_AjoutSortantMouseClicked

    private void TableCourrierSortantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCourrierSortantMouseClicked
        ReccuperationTableSortant();
    }//GEN-LAST:event_TableCourrierSortantMouseClicked

    private void AjoutEntrantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjoutEntrantMouseClicked
        AjouterEntrant Ae = new AjouterEntrant();
        Ae.setEntr(TableCourrierEntrant);
        Ae.setArch(TableArchive);
        Ae.setTraite(TableTraitement);
        Ae.setVisible(true);

    }//GEN-LAST:event_AjoutEntrantMouseClicked

    private void TableCourrierEntrantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCourrierEntrantMouseClicked
        ReccuperationTableEntrant();
    }//GEN-LAST:event_TableCourrierEntrantMouseClicked

    private void TableTraitementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableTraitementMouseClicked
        ReccuperationTableTraitement();
    }//GEN-LAST:event_TableTraitementMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        TraiteArchive.setEnabled(false);
        ModifTraite.setEnabled(false);
        ModifResp.setEnabled(false);
        envoyer.setEnabled(false);
        PDF.setEnabled(false);
        ModifEntrant.setEnabled(false);
        voirResp.setEnabled(false);
        VoirTraite.setEnabled(false);
        VoirSortant.setEnabled(false);
        VoirEntrant.setEnabled(false);
        VoirArchive.setEnabled(false);
        terminer.setVisible(false);
        Terminer.setVisible(false);
        Termine.setVisible(false);
        loadDataPersonnel(1);
        loadDataEntrant(1);
        loadDataSortant(1);
        loadDataTraitement(1);
        loadDataArchive(1);

    }//GEN-LAST:event_formWindowActivated

    private void TableCourrierEntrantMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCourrierEntrantMouseReleased
        ModifEntrant.setEnabled(true);
        VoirEntrant.setEnabled(true);
    }//GEN-LAST:event_TableCourrierEntrantMouseReleased

    private void TableCourrierSortantMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCourrierSortantMouseReleased
        PDF.setEnabled(true);
        VoirSortant.setEnabled(true);
    }//GEN-LAST:event_TableCourrierSortantMouseReleased

    private void TableTraitementMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableTraitementMouseReleased
        ModifTraite.setEnabled(true);
        VoirTraite.setEnabled(true);
        envoyer.setEnabled(true);
    }//GEN-LAST:event_TableTraitementMouseReleased

    private void TableArchiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArchiveMouseClicked
        ReccuperationTableArchive();
    }//GEN-LAST:event_TableArchiveMouseClicked

    private void TableArchiveMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableArchiveMouseReleased
        VoirArchive.setEnabled(true);
        TraiteArchive.setEnabled(true);
    }//GEN-LAST:event_TableArchiveMouseReleased

    private void VoirArchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoirArchiveActionPerformed
        VoirArchive1 Va = new VoirArchive1();
        Va.setVisible(true);
    }//GEN-LAST:event_VoirArchiveActionPerformed

    private void ArchiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ArchiveMouseClicked
        resetColor(JBoard);
        resetColor(JEntrant);
        resetColor(JSortant);
        resetColor(JResponsable);
        resetColor(JTraitement);
        setColor(JArch);
        Jp1.setVisible(false);
        Jp2.setVisible(false);
        Jp3.setVisible(false);
        Jp4.setVisible(false);
        Jp5.setVisible(false);
        Jp7.setVisible(true);
    }//GEN-LAST:event_ArchiveMouseClicked

    private void ModifEntrantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifEntrantActionPerformed
        ModifierEntrant1 Me = new ModifierEntrant1();
        Me.setEntr(TableCourrierEntrant);
        Me.setArch(TableArchive);
        Me.setTraite(TableTraitement);
        Me.setVisible(true);
    }//GEN-LAST:event_ModifEntrantActionPerformed

    private void VoirEntrantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoirEntrantActionPerformed
        VoirEntrant1 Ve = new VoirEntrant1();
        Ve.setVisible(true);
    }//GEN-LAST:event_VoirEntrantActionPerformed

    private void VoirSortantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoirSortantActionPerformed
        VoirSortant1 Vs = new VoirSortant1();
        Vs.setVisible(true);
    }//GEN-LAST:event_VoirSortantActionPerformed

    private void envoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envoyerActionPerformed
        if (B5.equals("envoyer")) {
            JOptionPane.showMessageDialog(null, "Ce courrier a déjà été envoyé.");
        } else {
            AjoutEnvoyer1 Aenv = new AjoutEnvoyer1();
            Aenv.setEnv(TableTraitement);
            Aenv.setSort(TableCourrierSortant);
            Aenv.setVisible(true);
        }

    }//GEN-LAST:event_envoyerActionPerformed

    private void ModifTraiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifTraiteActionPerformed
        ModifierTraitement1 Mt = new ModifierTraitement1();
        Mt.setTrait(TableTraitement);
        Mt.setVisible(true);
    }//GEN-LAST:event_ModifTraiteActionPerformed

    private void VoirTraiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoirTraiteActionPerformed
        VoirTraitement1 Vt = new VoirTraitement1();
        Vt.setVisible(true);
    }//GEN-LAST:event_VoirTraiteActionPerformed

    private void TraiteArchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraiteArchiveActionPerformed
        AjoutTraite1 Atrai = new AjoutTraite1();
        Atrai.setTrai(TableTraitement);
        Atrai.setArch(TableArchive);
        Atrai.setVisible(true);
    }//GEN-LAST:event_TraiteArchiveActionPerformed

    private void EnvoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnvoyerActionPerformed
        type = "envoyer";
    }//GEN-LAST:event_EnvoyerActionPerformed

    private void RechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RechercheActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String jdStr = sdf.format(DateDebut.getDate());
        String jd1Str = sdf.format(DateFin.getDate());
        try {
            String Sql = ("SELECT * FROM sortant WHERE dateSortant >= '" + jdStr + "' AND dateSortant <='" + jd1Str + "'");
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();
            TableCourrierSortant.setModel(DbUtils.resultSetToTableModel(rs));
            Terminer.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_RechercheActionPerformed

    private void TerminerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TerminerMouseClicked
        AffichageTableSortant();
        DateDebut.setDate(null);
        DateFin.setDate(null);
        Terminer.setVisible(false);
    }//GEN-LAST:event_TerminerMouseClicked

    private void RechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RechercherActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String jdStr = sdf.format(dateDebut.getDate());
        String jd1Str = sdf.format(dateFin.getDate());
        try {
            String Sql = ("SELECT * FROM entrant WHERE dateEntrant >= '" + jdStr + "' AND dateEntrant <='" + jd1Str + "'");
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();
            TableCourrierEntrant.setModel(DbUtils.resultSetToTableModel(rs));
            Termine.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_RechercherActionPerformed

    private void TermineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TermineMouseClicked
        AffichageTableEntrant();
        dateDebut.setDate(null);
        dateFin.setDate(null);
        Termine.setVisible(false);
    }//GEN-LAST:event_TermineMouseClicked

    private void ChercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChercheActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String jdStr = sdf.format(dateStart.getDate());
        String jd1Str = sdf.format(dateEnd.getDate());
        try {
            String Sql = ("SELECT * FROM archive WHERE dateArchive >= '" + jdStr + "' AND dateArchive <='" + jd1Str + "'");
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();
            TableArchive.setModel(DbUtils.resultSetToTableModel(rs));
            terminer.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_ChercheActionPerformed

    private void terminerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_terminerMouseClicked
        AffichageTableArchive();
        dateStart.setDate(null);
        dateEnd.setDate(null);
        terminer.setVisible(false);
    }//GEN-LAST:event_terminerMouseClicked

    private void TousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TousActionPerformed
        type = "Tous";
    }//GEN-LAST:event_TousActionPerformed

    private void nonEnvoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonEnvoyerActionPerformed
        type = "non envoyer";
    }//GEN-LAST:event_nonEnvoyerActionPerformed

    private void EnvoyerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnvoyerMouseClicked
        searchtypes();
    }//GEN-LAST:event_EnvoyerMouseClicked

    private void nonEnvoyerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nonEnvoyerMouseClicked
        searchtypes();
    }//GEN-LAST:event_nonEnvoyerMouseClicked

    private void TousMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TousMouseClicked
        AffichageTableTraitement();
    }//GEN-LAST:event_TousMouseClicked

    private void HeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderMouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx - x, yy - y);
    }//GEN-LAST:event_HeaderMouseDragged

    private void HeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HeaderMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_HeaderMousePressed

    private void NotificationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotificationMouseEntered
        Notification.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_NotificationMouseEntered

    private void NotificationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotificationMouseExited
        Notification.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_NotificationMouseExited

    private void NotificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotificationActionPerformed

        String requete = "SELECT numCourrier, dateArchive FROM archive WHERE dateArchive <= ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String troisJoursAvant = sdf.format(new Date(today.getTime() - 3 * 24 * 60 * 60 * 1000));

        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, troisJoursAvant);
            ResultSet rs = pst.executeQuery();
            
            boolean resultNotEmpty = false; // Un drapeau pour vérifier si le résultat n'est pas vide

            while (rs.next()) {
                String numCourrier = rs.getString("numCourrier");
                String dateArchive = rs.getString("dateArchive");
                
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Courriers archivés plus de 3 jours : courriers N° : " +numCourrier+ ",  Le : " +dateArchive+ "");
                resultNotEmpty = true; // Le résultat n'est pas vide
            }

            if (resultNotEmpty) {
                // Il y a des courriers archivés depuis plus de 3 jours
             
            } else {
                // Le résultat est vide
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Accun Courriers archivés plus 3 j");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_NotificationActionPerformed

    private void PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PDFActionPerformed

        try {
            String C4 = rs.getString("numSortant");
            Date D1 = rs.getDate("dateSortant");
            Document document = new Document();
            try {
                String query = "SELECT numSortant , dateSortant , nature, observation, destination FROM sortant WHERE numSortant=? AND dateSortant =? ";
                pst = conn.prepareStatement(query);
                pst.setString(1, C4);
                pst.setDate(2, (java.sql.Date) D1);
                rs = pst.executeQuery();

                ResultSet resultSet = pst.executeQuery();

                boolean SortantDetailsAdded = false; // Variable to track if client details paragraph is added

                // Demande à l'utilisateur de sélectionner l'emplacement du fichier PDF
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choisir l'emplacement du fichier PDF");
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // L'utilisateur a sélectionné un emplacement valide
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                    if (!filePath.toLowerCase().endsWith(".pdf")) {
                        filePath += ".pdf";
                    }

                    // Génération du reçu d'envoi au format PDF avec l'emplacement sélectionné
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));
                    document.open();

                    Rectangle pageSize = document.getPageSize();
                    float pageWidth = pageSize.getWidth();

                    float padding = pageWidth / 70;

                    String imagePath = getClass().getResource("../icons/logoMagascar.png").toString();
                    Image image = Image.getInstance(imagePath);
                    image.scaleAbsolute(130, 70);  // Ajustez la taille de l'image selon vos besoins
                    image.setAlignment(Element.ALIGN_CENTER);
                    Paragraph p = new Paragraph();
                    p.add(image);
                    document.add(p);
                    
                    PdfPCell titreCell = new PdfPCell(new Paragraph("REPOBLIKAN'I MADAGASIKARA"));
                    PdfPCell titreCell1 = new PdfPCell(new Paragraph("Fitiavana - Tanindrazana - Fandrosoana"));
                    PdfPCell titreCell2 = new PdfPCell(new Paragraph("----------------------------"));
                    titreCell.setBackgroundColor(new BaseColor(255, 255, 255));
                    titreCell.setPadding(padding);
                    titreCell.setBorder(Rectangle.NO_BORDER);
                    titreCell1.setBorder(Rectangle.NO_BORDER);
                    titreCell2.setBorder(Rectangle.NO_BORDER);
                    titreCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    titreCell1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                    titreCell2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

                    PdfPTable TableCourrierSortant = new PdfPTable(1);
                    TableCourrierSortant.setWidthPercentage(100);
                    TableCourrierSortant.addCell(titreCell);
                    TableCourrierSortant.addCell(titreCell1);
                    TableCourrierSortant.addCell(titreCell2);
                    TableCourrierSortant.setSpacingAfter(15);
                    document.add(TableCourrierSortant);

                    PdfPTable infoSortant = new PdfPTable(4);
                    infoSortant.setWidthPercentage(100);
                    PdfPCell emptyCell = new PdfPCell();
                    emptyCell.setFixedHeight(300); 
                    float[] columnWidths = {10f, 40f, 20f, 30f};
                    infoSortant.setWidths(columnWidths);
                    infoSortant.addCell(createCell("N°", true));
                    infoSortant.addCell(createCell("DESIGNATION DES PIECES", true));
                    infoSortant.addCell(createCell("NOMBRE", true));
                    infoSortant.addCell(createCell("OBSERVATIONS", true));
                    infoSortant.addCell(emptyCell);
                    infoSortant.addCell(emptyCell);
                    infoSortant.addCell(emptyCell);
                    infoSortant.addCell(emptyCell);

                    while (resultSet.next()) {
                        if (!SortantDetailsAdded) {
                            String numSortant = resultSet.getString("numSortant");
                            java.util.Date dateSortant = resultSet.getDate("dateSortant");

                            Paragraph p0 = new Paragraph();
                            p0.add("DIRECTION REGIONALE DE L'EDUCATION" + "                   " + "Ambositra, le " + dateSortant);
                            p0.setAlignment(Element.ALIGN_JUSTIFIED);
                            p0.setSpacingAfter(5);
                            document.add(p0);
                            Paragraph p01 = new Paragraph();
                            p01.setAlignment(Element.ALIGN_JUSTIFIED);
                            p01.add("      " + "NATIONALE AMORON'I MANIA");
                            p01.setSpacingAfter(5);
                            document.add(p01);
                            Paragraph p2 = new Paragraph();
                            p2.setAlignment(Element.ALIGN_JUSTIFIED);
                            p2.add("              " + " ********************* " + "                     " + "Monsieur Le Directeur Régional de l'Education Nationale");
                            p2.setSpacingAfter(10);
                            document.add(p2);
                            Paragraph p02 = new Paragraph();
                            p02.add("                                                                                            " + "AMORON'I MANIA  ");
                            p02.setSpacingAfter(15);
                            document.add(p02);
                            Paragraph p3 = new Paragraph();
                            p3.add(" N° " + numSortant + "/2023.A.M./SAF/Div RH.");
                            p3.setSpacingAfter(10);
                            document.add(p3);
                            Paragraph p03 = new Paragraph();
                            p03.add("                                                                                                        " + "à");
                            p03.setSpacingAfter(10);
                            document.add(p03);
                            Paragraph p4 = new Paragraph();
                            p4.add("                                                                " + "Monsieur le Chef de Service Regionale de Solde et des");
                            p4.setSpacingAfter(10);
                            document.add(p4);
                            Paragraph p04 = new Paragraph();
                            p04.add("                                                                                         " + "Pensions Amoron'i Mania");
                            p04.setSpacingAfter(30);
                            document.add(p04);

                            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.HELVETICA, 28);
                            Paragraph p5 = new Paragraph();
                            p5.setFont(font);
                            p5.add(" BORDEREAUX D'ENVOI");
                            p5.setAlignment(Element.ALIGN_CENTER);
                            p5.setSpacingAfter(25);
                            document.add(p5);

                            SortantDetailsAdded = true;
                        }

                        String observation = resultSet.getString("observation");
                        PdfPCell observationCell = createCell(observation, false);
                        infoSortant.addCell(observationCell);
                        infoSortant.addCell(emptyCell);
                    }

                    infoSortant.setSpacingAfter(15);
                    document.add(infoSortant);

                    document.close();
                    JOptionPane.showMessageDialog(null, "Téléchargement terminé avec succès");

                    // Ouvrir l'emplacement du fichier PDF
                    Desktop desktop = Desktop.getDesktop();
                    File pdfFile = new File(filePath);
                    if (pdfFile.exists()) {
                        desktop.open(pdfFile);
                    } else {
                        JOptionPane.showMessageDialog(null, "Le fichier PDF n'existe pas à l'emplacement spécifié");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Téléchargement échoué");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        AffichageTableSortant();
    }//GEN-LAST:event_PDFActionPerformed

    private void deconnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnMouseClicked
        int choix = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir vous déconnecter ?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (choix == JOptionPane.YES_OPTION) {
            dispose();
            new Login().setVisible(true);
        }
    }//GEN-LAST:event_deconnMouseClicked

    private void deconnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnMousePressed
        deconn.setForeground(new Color(96, 165, 250));
    }//GEN-LAST:event_deconnMousePressed

    private void deconnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconnMouseExited
        deconn.setForeground(Color.WHITE);
    }//GEN-LAST:event_deconnMouseExited

    private void TableResponsableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableResponsableMouseClicked
        ReccuperationTableResponsable();
    }//GEN-LAST:event_TableResponsableMouseClicked

    private void TableResponsableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableResponsableMouseReleased
        ModifResp.setEnabled(true);
        voirResp.setEnabled(true);
    }//GEN-LAST:event_TableResponsableMouseReleased

    private void AjoutRespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutRespActionPerformed
        AjoutResponsable Ar = new AjoutResponsable();
        Ar.setResp(TableResponsable);
        Ar.setVisible(true);
    }//GEN-LAST:event_AjoutRespActionPerformed

    private void txtrechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercheKeyReleased
        btnrechercheActionPerformed(null);
    }//GEN-LAST:event_txtrechercheKeyReleased

    private void btnrechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrechercheActionPerformed
        String requete = "SELECT IM , Nom, Prenom, Poste, CIN, Telephone, Adresse FROM responsable WHERE Nom LIKE ? OR Prenom LIKE ? OR IM LIKE ? ";
        try {
            pst = conn.prepareStatement(requete);
            pst.setString(1, "%" + txtrecherche.getText() + "%");
            pst.setString(2, "%" + txtrecherche.getText() + "%");
            pst.setString(3, "%" + txtrecherche.getText() + "%");
            rs = pst.executeQuery();
            TableResponsable.setModel(DbUtils.resultSetToTableModel(rs));

            if (txtrecherche.getText().isEmpty()) {
                AffichageTableResponsable();
                loadDataPersonnel(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnrechercheActionPerformed

    private void ModifRespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifRespActionPerformed
        ModifierResponsable1 Mr = new ModifierResponsable1();
        Mr.setResp(TableResponsable);
        Mr.setVisible(true);
    }//GEN-LAST:event_ModifRespActionPerformed

    private void voirRespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voirRespActionPerformed
        VoirResponsable1 Vr = new VoirResponsable1();
        Vr.setVisible(true);
    }//GEN-LAST:event_voirRespActionPerformed

    private void DonneTrimestreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DonneTrimestreMousePressed
        DonneTrimestre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTrimestre = (String) DonneTrimestre.getSelectedItem();

                if ("1er Trimestre".equals(selectedTrimestre) || "2éme Trimestre".equals(selectedTrimestre) || "3éme Trimestre".equals(selectedTrimestre)) {
                    java.util.Date datePdf = new java.util.Date();
                    java.sql.Date sqlDatepdf = new java.sql.Date(datePdf.getTime());
                    Document document = new Document();

                    try {
                        String dateCondition;
                        if ("1er Trimestre".equals(selectedTrimestre)) {
                            dateCondition = "BETWEEN '2023-01-01' AND '2023-03-31'";
                        } else if ("2éme Trimestre".equals(selectedTrimestre)) {
                            dateCondition = "BETWEEN '2023-04-01' AND '2023-06-30'";
                        } else { // 3eme Trimestre
                            dateCondition = "BETWEEN '2023-07-01' AND '2023-09-30'";
                        }

                        String query = "SELECT 'Entrant' AS type, COUNT(*) AS total FROM entrant WHERE DATE(dateEntrant) " + dateCondition + " UNION "
                                + "SELECT 'Sortant' AS type, COUNT(*) AS total FROM sortant WHERE DATE(dateSortant) " + dateCondition;
                        pst = conn.prepareStatement(query);
                        rs = pst.executeQuery();

                        // Variables for total amount calculation
                        int totalAmount = 0;

                        boolean clientDetailsAdded = false; // Variable to track if client details paragraph is added

                        // Demande à l'utilisateur de sélectionner l'emplacement du fichier PDF
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Choisir l'emplacement du fichier PDF");
                        int userSelection = fileChooser.showSaveDialog(null);

                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                            // L'utilisateur a sélectionné un emplacement valide
                            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                            if (!filePath.toLowerCase().endsWith(".pdf")) {
                                filePath += ".pdf";
                            }

                            // Génération du reçu d'entretien au format PDF avec l'emplacement sélectionné
                            PdfWriter.getInstance(document, new FileOutputStream(filePath));
                            document.open();

                            Rectangle pageSize = document.getPageSize();
                            float pageWidth = pageSize.getWidth();

                            float padding = pageWidth / 40;

                            PdfPCell titreCell = new PdfPCell(new Paragraph("DONNER TRIMESTRIELLE"));
                            titreCell.setBackgroundColor(new BaseColor(2,132,199));
                            titreCell.setPadding(padding);
                            titreCell.setBorder(Rectangle.NO_BORDER);
                            titreCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                            titreCell.setBorderColor(BaseColor.WHITE);

                            PdfPTable TableEntretien = new PdfPTable(1);
                            TableEntretien.setWidthPercentage(100);
                            TableEntretien.addCell(titreCell);
                            TableEntretien.setSpacingAfter(15);
                            document.add(TableEntretien);

                            // Ajouter la date de téléchargement
                            Paragraph dateDownloadParagraph = new Paragraph("Aujourd'hui : " + new SimpleDateFormat("dd/MM/yyyy").format(sqlDatepdf));
                            document.add(dateDownloadParagraph);

                            // Ajouter le trimestre choisi
                            Paragraph trimestreParagraph = new Paragraph("Donnée du : " + selectedTrimestre);
                            document.add(trimestreParagraph);

                            // Espacement entre la date de téléchargement, le trimestre et le tableau
                            document.add(new Paragraph(" "));
                            
                            PdfPTable infoEntretien = new PdfPTable(2);
                            infoEntretien.setWidthPercentage(100);
                            // Ajouter le fond pour "Courriers"
                            PdfPCell courriersCell = createCell("Courriers", true);
                            courriersCell.setBackgroundColor(new BaseColor(2,132,199));
                            infoEntretien.addCell(courriersCell);

                            // Ajouter le fond pour "Nombre"
                            PdfPCell nombreCell = createCell("Nombre", true);
                            nombreCell.setBackgroundColor(new BaseColor(2,132,199));
                            infoEntretien.addCell(nombreCell);

                            while (rs.next()) {
                                String type = rs.getString("type");
                                int total = rs.getInt("total");

                                // Add the current service price to the total amount
                                totalAmount += total;

                                if (!clientDetailsAdded) {
                                    // Ajoutez ici le code pour récupérer les détails du client, s'ils sont disponibles dans le ResultSet rs.

                                    clientDetailsAdded = true; // Set the flag to true once client details are added
                                }

                                infoEntretien.addCell(createCell(type, false));
                                infoEntretien.addCell(createCell(Integer.toString(total), false));
                            }

                            infoEntretien.setSpacingAfter(15);
                            document.add(infoEntretien);

                            // Display the total amount
                            Paragraph p4 = new Paragraph();
                            p4.add("Total du Courriers : " + totalAmount);
                            p4.setSpacingAfter(15);
                            document.add(p4);

                            document.close();
                            JOptionPane.showMessageDialog(null, "Téléchargement terminé avec succès");

                            // Ouvrir l'emplacement du fichier PDF
                            Desktop desktop = Desktop.getDesktop();
                            File pdfFile = new File(filePath);
                            if (pdfFile.exists()) {
                                desktop.open(pdfFile);
                            } else {
                                JOptionPane.showMessageDialog(null, "Le fichier PDF n'existe pas à l'emplacement spécifié");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Téléchargement échoué");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace(); // À des fins de débogage. Remplacez-le par le gestionnaire d'exceptions approprié dans votre application.
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez choisir trimestrielle valid");
                }
            }
        });
    }//GEN-LAST:event_DonneTrimestreMousePressed

    private void NotificationMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotificationMousePressed
        notifcompte.setVisible(false);
    }//GEN-LAST:event_NotificationMousePressed

    private static PdfPCell createCell(String content, boolean isHeader) {
        PdfPCell cell;
        if (isHeader) {
            cell = new PdfPCell(new Phrase(content));
        } else {
            cell = new PdfPCell(new Phrase(content));
        }
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        cell.setUseBorderPadding(true);

        cell.setBorderColor(new BaseColor(0, 0, 0));

        return cell;
    }

    public void compterCourriersArchives() {
        String requete = "SELECT COUNT(*) AS count FROM archive WHERE dateArchive <= ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String troisJoursAvant = sdf.format(new Date(today.getTime() - 3 * 24 * 60 * 60 * 1000));

        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, troisJoursAvant);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                count = rs.getInt("count"); // Récupérer le nombre de courriers correspondants

                if (count > 0) {
                    // Il y a des courriers archivés depuis plus de 3 jours
                    notifcompte.setText(Integer.toString(count));
                } else {
                    // Il n'y a aucun courrier archivé depuis plus de 3 jours
                    notifcompte.setVisible(false);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------------------------RECCUPERATION ET AFFICHAGE TABLE RESPONSABLE---------------
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

    //-----------------------------------------------FIN------------------------------------------------
    //-------------------------------RECCUPERATION ET AFFICHAGE TABLE SORTANT---------------
    public void AffichageTableSortant() {
        try {
            String requete = "SELECT numSortant AS \"N°\", dateSortant AS \"Envoyer le\", nature, observation, destination FROM sortant ORDER BY numSortant ASC";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableCourrierSortant.setModel(model);

            // Désactiver l'édition des cellules individuelles
            TableCourrierSortant.setDefaultEditor(Object.class, null);

            // Empêcher la sélection de plusieurs lignes
            TableCourrierSortant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Personnaliser le rendu des lignes
            TableCourrierSortant.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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

            JTableHeader Head = TableCourrierSortant.getTableHeader();
            Dimension dimension = Head.getPreferredSize();
            dimension.height = 35;
            Head.setPreferredSize(dimension);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ReccuperationTableSortant() {
        try {
            int Row = TableCourrierSortant.getSelectedRow();
            this.Tab1 = (TableCourrierSortant.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numSortant, dateSortant, nature, observation, destination FROM sortant WHERE numSortant = '" + Tab1 + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String B1 = rs.getString("numSortant");
                System.out.println(B1);
                String B2 = rs.getString("dateSortant");
                System.out.println(B2);
                String B3 = rs.getString("nature");
                System.out.println(B3);
                String B4 = rs.getString("observation");
                System.out.println(B4);
                String B5 = rs.getString("destination");
                System.out.println(B5);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getTable2() {
        return Tab1;
    }

    //-----------------------------------------------FIN------------------------------------------------
    //-------------------------------RECCUPERATION ET AFFICHAGE TABLE ENTRANT---------------
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

            TableCourrierEntrant.setBorder(new EmptyBorder(0, 0, 0, 0));

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

    //-----------------------------------------------FIN------------------------------------------------
    //-------------------------------RECCUPERATION ET AFFICHAGE TABLE TRAITEMENT---------------
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

    public void searchtypes() {
        try {
            String Requete = "SELECT numTraitement AS \"N°\", numCourrier AS \"Courrier N°\", dateTraitement AS \"Traiter le\", nomPersonnel AS \"Traiter par\", status AS \"statut\" FROM traitement WHERE status = '" + type + "'";
            pst = conn.prepareStatement(Requete);
            rs = pst.executeQuery();
            TableModel model = DbUtils.resultSetToTableModel(rs);
            TableTraitement.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ReccuperationTableTraitement() {
        try {
            int Row = TableTraitement.getSelectedRow();
            this.Tab3 = (TableTraitement.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numTraitement, numCourrier, dateTraitement, nomPersonnel, status FROM traitement WHERE numTraitement = '" + Tab3 + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String B1 = rs.getString("numTraitement");
                System.out.println(B1);
                String B2 = rs.getString("numCourrier");
                System.out.println(B2);
                String B3 = rs.getString("dateTraitement");
                System.out.println(B3);
                String B4 = rs.getString("nomPersonnel");
                System.out.println(B4);
                B5 = rs.getString("status");
                System.out.println(B5);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getTable4() {
        return Tab3;
    }

    //-----------------------------------------------FIN------------------------------------------------
    //-------------------------------RECCUPERATION ET AFFICHAGE TABLE ARCHIVE---------------
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

    public void ReccuperationTableArchive() {
        try {
            int Row = TableArchive.getSelectedRow();
            this.Tab5 = (TableArchive.getModel().getValueAt(Row, 0).toString());
            String requete = "SELECT numArchive, numCourrier, dateArchive FROM archive WHERE numArchive = '" + Tab5 + "' ";
            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();
            if (rs.next()) {
                String B1 = rs.getString("numArchive");
                System.out.println(B1);
                String B2 = rs.getString("numCourrier");
                System.out.println(B2);
                String B3 = rs.getString("dateArchive");
                System.out.println(B3);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getTable6() {
        return Tab5;
    }

    //------------------------------------fin--------------
    //---------------------------------------Histograme----------------------
    public void Total_Table() {
        try {
            String requete = "SELECT 'responsable' AS source, COUNT(*) AS total_operations FROM responsable "
                    + "UNION ALL "
                    + "SELECT 'entrant' AS source, COUNT(*) FROM entrant "
                    + "UNION ALL "
                    + "SELECT 'sortant' AS source, COUNT(*) FROM sortant ";

            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            // Create a dataset for the pie chart
            DefaultPieDataset dataset = new DefaultPieDataset();
            while (rs.next()) {
                String source = rs.getString("source");
                int totalOperations = rs.getInt("total_operations");
                dataset.setValue(source, totalOperations);
            }

            // Create the pie chart
            JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false); // Empty title
            PiePlot plot = (PiePlot) chart.getPlot(); // Utilize PiePlot for a pie chart

            // Remove the border around the plot
            plot.setOutlineVisible(false);
            plot.setBackgroundPaint(Color.WHITE);
            chart.setBackgroundPaint(Color.WHITE);

            // Define colors for the sections of the pie chart
            Color[] colors = new Color[]{
                new Color(118, 90, 147),
                new Color(102, 153, 255),
                new Color(73, 167, 83)
            };

            // Assign colors to sections of the pie chart
            for (int i = 0; i < dataset.getItemCount(); i++) {
                plot.setSectionPaint(dataset.getKey(i), colors[i]);
            }

            // Create a chart panel and add it to your panel (Panel1)
            ChartPanel chartPanel = new ChartPanel(chart);
            Panel1.setLayout(new BorderLayout()); // Set the layout manager of Panel1

            // Refresh the panel to reflect the changes
            Panel1.removeAll();
            Panel1.add(chartPanel, BorderLayout.CENTER);
            Panel1.validate();

            // Centrer le contenu des cellules du tableau
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            // TablePartitifs.setDefaultRenderer(Object.class, centerRenderer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------------fin--------------------
    //---------------------HISTOGRAMME-----------------------------
    public void Histogramme() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

            String requete = "SELECT DATE_FORMAT(dateEntrant, '%Y-%m') AS mois, 'entrant' AS type, COUNT(*) AS total "
                    + "FROM entrant "
                    + "GROUP BY mois "
                    + "UNION "
                    + "SELECT DATE_FORMAT(dateSortant, '%Y-%m') AS mois, 'sortant' AS type, COUNT(*) AS total "
                    + "FROM sortant "
                    + "GROUP BY mois";

            pst = conn.prepareStatement(requete);
            rs = pst.executeQuery();

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            while (rs.next()) {
                String mois = rs.getString("mois");
                String type = rs.getString("type");
                int total = rs.getInt("total");
                dataset.addValue(total, type, mois);
            }

            JFreeChart chart = ChartFactory.createBarChart3D(
                    "", // Titre vide
                    "Mois",
                    "Nombre de Courriers",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(59, 130, 246)); // Couleur pour les courriers entrants (bleu)
            renderer.setSeriesPaint(1, new Color(34, 197, 96)); // Couleur pour les courriers sortants (vert)
            plot.setOutlineVisible(false);
            plot.setBackgroundPaint(Color.WHITE);

            chart.setBackgroundPaint(new Color(255, 255, 255));
            chart.getTitle().setPaint(new Color(0, 0, 0));

            // Créer un JPanel pour afficher le graphique
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new ChartPanel(chart), BorderLayout.CENTER);

            // Maintenant, vous pouvez ajouter le JPanel "panel" à votre interface utilisateur
            ChartPanel chartPanel = new ChartPanel(chart);
            Panel.setLayout(new BorderLayout());

            // Refresh the panel to reflect the changes
            Panel.removeAll();
            Panel.add(chartPanel, BorderLayout.CENTER);
            Panel.validate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //-----------------------FIN----------------------------------
    //----------------------DASHBOARD------------------------------
    public void DashEntrant() {
        try {
            String requeteComptage = "SELECT COUNT(*) AS total FROM entrant";
            PreparedStatement pst = conn.prepareStatement(requeteComptage);

            // Exécute la requête de comptage
            ResultSet rs = pst.executeQuery();

            // Vérifie si le résultat a été retourné
            if (rs.next()) {
                int nombreEntrant = rs.getInt("total");

                // Maintenant, vous pouvez afficher le résultat sur un label
                LabelEntrant.setText("Total : " + nombreEntrant);
            }

            // Fermez les ressources    
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DashSortant() {
        try {
            String requeteComptage = "SELECT COUNT(*) AS total FROM sortant";
            PreparedStatement pst = conn.prepareStatement(requeteComptage);

            // Exécute la requête de comptage
            ResultSet rs = pst.executeQuery();

            // Vérifie si le résultat a été retourné
            if (rs.next()) {
                int nombreSortant = rs.getInt("total");

                // Maintenant, vous pouvez afficher le résultat sur un label
                LabelSortant.setText("Total : " + nombreSortant);
            }

            // Fermez les ressources
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DashPersonnel() {
        try {
            String requeteComptage = "SELECT COUNT(*) AS total FROM responsable";
            PreparedStatement pst = conn.prepareStatement(requeteComptage);

            // Exécute la requête de comptage
            ResultSet rs = pst.executeQuery();

            // Vérifie si le résultat a été retourné
            if (rs.next()) {
                int nombreResponsables = rs.getInt("total");

                // Maintenant, vous pouvez afficher le résultat sur un label
                LabelPersonnel.setText("Total : " + nombreResponsables);
            }

            // Fermez les ressources
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //----------------------FIN-------------------------------
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AcceuilPrincipaleUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AjoutEntrant;
    private javax.swing.JButton AjoutResp;
    private javax.swing.JButton AjoutSortant;
    private javax.swing.JLabel Archive;
    private javax.swing.JLabel ButtonHideMenu;
    private javax.swing.JPanel ButtonReduire;
    private javax.swing.JPanel Buttonclose;
    private javax.swing.JPanel Buttonmax;
    private javax.swing.JButton Cherche;
    private javax.swing.JLabel CloseButton;
    private javax.swing.JLabel Dashboar;
    private javax.swing.JPanel Dashboard;
    private com.toedter.calendar.JDateChooser DateDebut;
    private com.toedter.calendar.JDateChooser DateFin;
    private javax.swing.JComboBox<String> DonneTrimestre;
    private javax.swing.JLabel Entrant;
    private javax.swing.JRadioButton Envoyer;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel JArch;
    private javax.swing.JPanel JBoard;
    private javax.swing.JPanel JEntrant;
    private javax.swing.JPanel JResponsable;
    private javax.swing.JPanel JSortant;
    private javax.swing.JPanel JTraitement;
    private javax.swing.JPanel Jp1;
    private javax.swing.JPanel Jp10;
    private javax.swing.JPanel Jp2;
    private javax.swing.JPanel Jp3;
    private javax.swing.JPanel Jp4;
    private javax.swing.JPanel Jp5;
    private javax.swing.JPanel Jp7;
    private javax.swing.JLabel LabelEntrant;
    private javax.swing.JLabel LabelPersonnel;
    private javax.swing.JLabel LabelSortant;
    private javax.swing.JLabel MaxButton;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Menuhide;
    private javax.swing.JButton ModifEntrant;
    private javax.swing.JButton ModifResp;
    private javax.swing.JButton ModifTraite;
    private vue.Button Notification;
    private javax.swing.JButton PDF;
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel Panel1;
    private javax.swing.JButton Recherche;
    private javax.swing.JButton Rechercher;
    private javax.swing.JLabel Reduire;
    private javax.swing.JLabel Responsab;
    private javax.swing.JLabel Sortant;
    private javax.swing.JTable TableArchive;
    private javax.swing.JTable TableCourrierEntrant;
    private javax.swing.JTable TableCourrierSortant;
    private javax.swing.JTable TableResponsable;
    private javax.swing.JTable TableTraitement;
    private javax.swing.JLabel Termine;
    private javax.swing.JLabel Terminer;
    private javax.swing.JRadioButton Tous;
    private javax.swing.JLabel Traite;
    private javax.swing.JButton TraiteArchive;
    private javax.swing.JButton VoirArchive;
    private javax.swing.JButton VoirEntrant;
    private javax.swing.JButton VoirSortant;
    private javax.swing.JButton VoirTraite;
    private javax.swing.JButton btnrecherche;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dateDebut;
    private com.toedter.calendar.JDateChooser dateEnd;
    private com.toedter.calendar.JDateChooser dateFin;
    private com.toedter.calendar.JDateChooser dateStart;
    private javax.swing.JLabel deconn;
    private javax.swing.JButton envoyer;
    private javax.swing.JPanel hidemenu;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private javax.swing.JRadioButton nonEnvoyer;
    private vue.Button notifcompte;
    private pagination.Pagination pagination1;
    private pagination.Pagination pagination2;
    private pagination.Pagination pagination3;
    private pagination.Pagination pagination4;
    private pagination.Pagination pagination5;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroll1;
    private javax.swing.JScrollPane scroll2;
    private javax.swing.JScrollPane scroll3;
    private javax.swing.JScrollPane scroll4;
    private javax.swing.JPanel setting;
    private javax.swing.JLabel terminer;
    private javax.swing.JTextField txtrecherche;
    private javax.swing.JButton voirResp;
    // End of variables declaration//GEN-END:variables
}
