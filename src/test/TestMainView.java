package test;

import controller.JdbsController;
import model.MainMenu;

public class TestMainView {
	public static void main(String[] args) {
		MainMenu view = new MainMenu();
		view.setController( new JdbsController());
		view.setVisible(true);
	}
}

