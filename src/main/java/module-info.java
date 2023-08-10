module com.sniper4ever.bankapp {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.sniper4ever.bankapp to javafx.fxml;
    exports com.sniper4ever.bankapp;
}