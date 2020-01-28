package by.epam.finalTask.controller.command;

import by.epam.finalTask.controller.command.Impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final Logger logger= LogManager.getLogger(CommandProvider.class);

    private final static CommandProvider instance=new CommandProvider();
    private final Map<CommandName, Command> repository=new HashMap<>();

    private final NoSuchCommand noSuchCommand=new NoSuchCommand();

    private CommandProvider(){
        repository.put(CommandName.REGISTRATION_PAGE, new RegistrationPage());
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.SIGN_IN_PAGE, new SignInPage());
        repository.put(CommandName.SIGN_IN, new SignIn());
    }

    public static CommandProvider getInstance(){
        return instance;
    }

    public Command getCommand(String name){
        CommandName commandName;
        Command command;

        try {
            commandName=CommandName.valueOf(name.toUpperCase());
            command=repository.get(commandName);
        }catch (IllegalArgumentException | NullPointerException e){
            logger.warn("No such command: " + name);
            //добавить "команду отсутвия команды шоли"
            command=noSuchCommand;
        }

        return command;
    }
}
