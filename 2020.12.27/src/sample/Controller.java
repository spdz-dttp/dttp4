package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class Controller {

    @FXML
    TextField input;
    @FXML
    Text output;
    @FXML public void handleSubmit() {
        System.out.println("你好");
    }


}
