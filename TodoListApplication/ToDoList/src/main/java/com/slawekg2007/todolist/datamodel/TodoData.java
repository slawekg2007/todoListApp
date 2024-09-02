package com.slawekg2007.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TodoData {
    //Singleton pattern design
    private static TodoData instance = new TodoData();
    private static String filename = "TodoListItems.txt";
    private ObservableList<TodoItem> todoItems;
    private DateTimeFormatter formatter;

    public static TodoData getInstance() {
        return instance;
    }
    private TodoData(){
        formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
    public ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }
    public void addTodoItem(TodoItem item){
        todoItems.add(item);
    }
    public void loadTodoItems() throws IOException{
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try{
            while ((input = br.readLine())!=null){
                String[] itemPieces =input.split("\t\t");
                String shortDescriptions = itemPieces [0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];
                LocalDate date = LocalDate.parse(dateString, formatter);
                TodoItem todoItem = new TodoItem(shortDescriptions, details, date);
                todoItems.add(todoItem);
            }
        }finally {
            if(br!=null){
                br.close();
            }
        }
    }
    public void storeToDoItems() throws IOException{
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            for (TodoItem item : todoItems) {
                bw.write(String.format("%s\t\t%s\t\t%s\n",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));
            }

        }finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
    public void deleteTodoItem(TodoItem item) {
        todoItems.remove(item);
    }

}









