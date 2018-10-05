package visuals;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ObjectiveLabel extends StackPane {
	@FXML
	private Text objectiveText;
	@FXML
	private CheckBox objectiveCheckBox;
	
	private String objText;	// This is the text we want to set in the objectiveText
	private String numToCompleteString;	// This is the number(s) we want to set in the checkbox
	
	public ObjectiveLabel(String objText, String numToCompleteString) {
		super();
		this.objText = objText;
		this.numToCompleteString = numToCompleteString;
		try {
			FXMLLoader l = new FXMLLoader(getClass().getResource("/visuals/objectiveComponent.fxml"));
			l.setRoot(this);
			l.setController(this);
			l.load();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void initialize() {
		this.objectiveText.setText(objText);
		this.objectiveCheckBox.setText(numToCompleteString);
	}
}
