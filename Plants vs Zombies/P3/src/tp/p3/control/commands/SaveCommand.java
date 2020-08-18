package tp.p3.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.InvalidPatternException;
import tp.p3.logic.Game;

public class SaveCommand extends FileCommands {

	public SaveCommand() {
		super("save", "s", "[Sa]ve <filename>: save in a file the state of the game at this moment.");
	}

	/*
	 * Comprobar el nombre del fichero Crearlo o sobreescribirlo Escribir cabecera y
	 * añadir el .dat
	 * 
	 */
	@Override
	public boolean execute(Game game) throws CommandExecuteException, InvalidPatternException {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(filenameString))) {
			out.write("Plants Vs Zombies v3.0");
			out.newLine();
			out.newLine();
			game.store(out);
			System.out.println(
					"Game successfully saved in file " + filenameString + ".dat. Use the load command to reload it.");
			return true;
		} catch (IOException ioe) {
			System.out.println("Invalid filename: the filename contains invalid characters");
		}
		return false;
	}
}
