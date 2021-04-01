package fr.core.api;

import javafx.scene.control.Button;

public class VButton {

	private Button button;
	private String title;
	private String style;
	private boolean activate;
	
	public VButton(Button button, String title, String style, boolean activate) {
		this.button = button;
		this.title = title;
		this.style = style;
		this.activate = activate;
	}
	
	public void createVButton() {
		button.setText(title);
		button.setStyle(style);
		button.setVisible(activate);
	}
	
	public Button getButton() {
		return button;
	}	
	
}
