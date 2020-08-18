package tp.p3.control.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import tp.p3.exceptions.AlteredFileException;
import tp.p3.exceptions.CommandExecuteException;
import tp.p3.exceptions.InvalidFirstLineFileException;
import tp.p3.logic.Game;

public class LoadCommand extends FileCommands {


	public LoadCommand() {
		super("load", "lo", "[Lo]ad <filename>: load the state of the game from a file.");
	}

	/*
	 * â€¢ Comprobar que el nombre del fichero proporcionado por el usuario es un
	 * nombre vÃ¡lido de fichero y que existe en el sistema de ficheros. â€¢
	 * Intentar abrir el fichero con este nombre en lectura. â€¢ Leer la cabecera,
	 * que consta del mensaje â€œPlants Vs Zombies v3.0â€�, seguido por una lÃ­nea
	 * en blanco. En un contexto real, probablemente tambiÃ©n aceptarÃ­a cabeceras
	 * de algunas versiones anteriores.
	 */

	public boolean execute(Game game) {

		try (BufferedReader out = new BufferedReader(new FileReader(filenameString))) {

			String s = out.readLine();
			if (s == null) {
				throw new InvalidFirstLineFileException("Load failed: Empty File");
			}

			else if (!s.equalsIgnoreCase("Plants Vs Zombies v3.0")) {
				throw new InvalidFirstLineFileException("Load failed: Invalid file format");
			}
			s = out.readLine();
			game.load(out);

			System.out.println("Game successfully loaded from file " + filenameString + ".dat");

			return true;

		} catch (InvalidFirstLineFileException e) {
			System.out.println(e.getMessage());
		} catch (CloneNotSupportedException e) {
			System.out.println(e.getMessage());
		} catch (CommandExecuteException e) {
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			System.out.println("File not found");
		} catch (AlteredFileException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
