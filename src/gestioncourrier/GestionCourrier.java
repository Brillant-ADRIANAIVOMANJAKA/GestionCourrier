package gestioncourrier;

import Connexion.ConnexionDB;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.sql.SQLException;
import vue.Login;

public class GestionCourrier {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new Swingx.StartLog(null, true).setVisible(true);
        FlatAnimatedLafChange.showSnapshot();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
        FlatMacLightLaf.setup();
        ConnexionDB.loadConnexion();
        Login menu = new Login();
        menu.show();

    }

}
