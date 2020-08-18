package tp.pr3.control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import tp.pr3.exceptions.InvalidFirstLineFileException;
import tp.pr3.exceptions.InvalidPatternException;
import tp.pr3.exceptions.NoFileNameException;
import tp.pr3.exceptions.SpaceNameException;
import tp.pr3.logic.multigames.Game;
import tp.pr3.util.MyStringUtils;

public class LoadCommand extends Command {
	private String filenameString;

	public LoadCommand(String s) {
		super("load <filename>", "load the state of the game from a file.");
		filenameString = s;
	}

	// guardar estado
	public boolean execute(Game game) {

		try (BufferedReader out = new BufferedReader(new FileReader(filenameString))) {

			String s = out.readLine();
			if (s == null) {
				throw new InvalidFirstLineFileException("Load failed: Empty File");
			}

			if (!s.equalsIgnoreCase("This file stores a saved 2048 game")) {
				throw new InvalidFirstLineFileException("Load failed: Invalid file format");
			}
			s = out.readLine();

			GameType gt = game.load(out);
			game.setGameType(gt);

			
				System.out.println("Game successfully loaded from file: " + gt.toString());
				System.out.println(game.toString());


			return true;
		

		}
		catch(NullPointerException e) {
			System.out.println("No se ha podido cargar el archivo. Tipo de juego erroneo");
		}
		catch(NumberFormatException e) {
			System.out.println("No se ha podido cargar el archivo. Caracter invalido en la matriz");
		}
		catch (IOException ioe) {
			System.out.println(ioe.getMessage());
			
		} catch (InvalidFirstLineFileException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public Command parse(String[] commandWords, Scanner in) throws Exception {
		Command command = null;

		if (commandWords.length == 2 && commandWords[0].equalsIgnoreCase(commandName)
				&& MyStringUtils.correctPattern(commandWords[1])) {

			String filename = confirmFileNameStringForRead(commandWords[1], in); // null, nombre de fichero correcto o
																					// AnswerAgain
			if (filename != null) {
				command = new LoadCommand(commandWords[1]);
				return command;
			}
			if (filename == null)
				throw new Exception("File not found");
		} else {
			if (commandWords.length == 2 && commandWords[0].equalsIgnoreCase(commandName)
					&& !MyStringUtils.correctPattern(commandWords[1])) {
				throw new InvalidPatternException("Invalid filename: the filename contains invalid characters");
			}
			if (commandWords.length == 1 && commandWords[0].equalsIgnoreCase(commandName)) {
				throw new NoFileNameException("Load must be followed by a filename");
			}
			if (commandWords.length > 2 && commandWords[0].equalsIgnoreCase(commandName)) {
				throw new SpaceNameException("Invalid filename: the filename contains spaces");
			}
			return null;
		}
		return null;
	}

	private String confirmFileNameStringForRead(String filenameString, Scanner in) {
		String loadName = filenameString;
		File file = new File(loadName);
		if (file.exists())
			return loadName;
		return null;
	}
}