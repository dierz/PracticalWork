package test;

import controller.JpaController;
import model.MainMenu;

public class TestMainView {
	public static void main(String[] args) {
		MainMenu view = new MainMenu();
		view.setController( new JpaController());
		view.setVisible(true);
	}
}

