module com.mycompany.annuaireelectronique {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.annuaireelectronique to javafx.fxml;
    exports com.mycompany.annuaireelectronique;
}
