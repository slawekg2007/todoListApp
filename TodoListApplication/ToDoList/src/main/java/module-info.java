module com.slawekg2007.todolist {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.slawekg2007.todolist to javafx.fxml;
    exports com.slawekg2007.todolist;
}