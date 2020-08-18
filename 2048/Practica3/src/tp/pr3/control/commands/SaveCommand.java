package tp.pr3.control.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tp.pr3.exceptions.InvalidPatternException;
import tp.pr3.exceptions.NoFileNameException;
import tp.pr3.exceptions.SpaceNameException;
import tp.pr3.logic.multigames.Game;
import tp.pr3.util.MyStringUtils;

public class SaveCommand extends Command{
	private boolean filename_confirmed;
	private String filenameString;
	public static final String filenameInUseMsg = "The file already exists ; do you want to overwrite it ? (Y/N)";
	
	public SaveCommand(String filename) {
		super("save <filename>", "save in a file the state of the game at this moment.");
		filenameString = filename;
	}

	public boolean execute(Game game) {
		try(BufferedWriter out = new BufferedWriter(new FileWriter(filenameString))){
			out.write("This file stores a saved 2048 game");
			out.newLine();
			out.newLine();
			game.store(out);
			System.out.println("Game successfully saved to file; use load command to reload it.");
			return true;
		} catch (IOException ioe) {
			System.out.println("store failed!!");	
		}
		return false;
	}

	public Command parse(String[] commandWords, Scanner in) throws InvalidPatternException, NoFileNameException, SpaceNameException {
		Command command = null;
		boolean answer = true;
		String nameFile = "";
		
		if (commandWords.length == 2 && commandWords[0].equalsIgnoreCase(commandName) && 
			MyStringUtils.correctPattern(commandWords[1])) {
			nameFile = commandWords[1];
			while(answer) {
	
				String fileName = confirmFileNameStringForWrite(nameFile, in); // null, nombre de fichero correcto o AnswerAgain	
				
				if (fileName.equalsIgnoreCase("AnswerAgain")) {
					answer = true;
					System.out.println("Please enter another filename:");
					String[] s = in.nextLine().toLowerCase().trim().split("\\s+");
					if (s.length == 1 && MyStringUtils.correctPattern(s[0])) {
						nameFile = s[0];
					}else {
						if(s.length > 1)
							throw new SpaceNameException("Invalid filename: the filename contains spaces");
						if (!MyStringUtils.correctPattern(s[0]))
							throw new InvalidPatternException("Invalid filename: the filename contains invalid characters");
					}
				}
				if (fileName != null && !fileName.equalsIgnoreCase("AnswerAgain")) {
					command = new SaveCommand(fileName);
					answer = false;
					return command;
				}
			}	
			return null;
		}
		else {	
			if (commandWords.length == 2 && commandWords[0].equalsIgnoreCase(commandName) && 
					!MyStringUtils.correctPattern(commandWords[1])) {
				throw new InvalidPatternException("Invalid filename: the filename contains invalid characters");
			}
			if (commandWords.length == 1 && commandWords[0].equalsIgnoreCase(commandName)) {
				throw new NoFileNameException("Save must be followed by a filename");
			}
			if (commandWords.length > 2 && commandWords[0].equalsIgnoreCase(commandName)) {
				throw new SpaceNameException("Invalid filename: the filename contains spaces");
			}	
			return null;
		}
	}

	private String confirmFileNameStringForWrite(String filenameString, Scanner in) {
		String loadName = filenameString;
		filename_confirmed = false;
		while (!filename_confirmed) {
			if (MyStringUtils.validFileName(loadName)) {
				File file = new File(loadName);
				if (!file.exists())
					filename_confirmed = true; // se puede crear o leer
				else {
					loadName = getLoadName(filenameString, in);
					filename_confirmed = true;
				}
			} else {
				System.out.println("No se ha podido escribir en el fichero!");
			}
		}
			return loadName;
	}
	
	public String getLoadName(String filenameString, Scanner in) {
		String newFilename = null;
		boolean yesOrNo = false;
			while (!yesOrNo) {
				System.out.print(filenameInUseMsg + ": ");
				String[] responseYorN = in.nextLine().toLowerCase().trim().split("\\s+");
					if (responseYorN.length == 1) {
						switch (responseYorN[0]) {
						case "y": yesOrNo = true;
						 	newFilename = filenameString; break;
						case "n": yesOrNo = true;
							newFilename = "AnswerAgain"; break;
						default: System.out.println("Please answer ’Y’ or ’N’");
								yesOrNo = false;
						}
					} else {
						System.out.println("Please answer ’Y’ or ’N’");
					}
			}
		return newFilename;
	}
	
	public boolean existsInDirectory(String nameFile) {
		File dir = new File(System.getProperty("user.dir"));
		boolean exists = false;
		for(String fileName : dir.list()) {
			if(nameFile.equals(fileName))
				exists = true;
		}
		return exists;
	}
}