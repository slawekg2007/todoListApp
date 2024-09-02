package com.slawekg2007.todolist;

import com.slawekg2007.todolist.datamodel.TodoData;
import com.slawekg2007.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;

    public TodoItem processResults(){
        String shortDescription = shortDescriptionField.getText().trim();
        String detail = detailsArea.getText().trim();
        LocalDate deadLine = deadlinePicker.getValue();
        TodoItem newItem = new TodoItem(shortDescription, detail, deadLine);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;
    }
}
