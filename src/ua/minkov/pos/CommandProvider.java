package ua.minkov.pos;

import ua.minkov.pos.commands.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Konstantin on 27.09.2016.
 */
public class CommandProvider {

    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("select", new SelectProductCommand());
        commands.put("insert", new InsertCoinCommand());
        commands.put("total", new TotalSumCommand());
        commands.put("checkout", new CheckoutCommand());
        commands.put("cancel", new CancelPurchaseCommand());
        commands.put("default", new DefaultCommand());
        commands.put("change", new ReturnChangeCommand());
    }

    public static Command getCommand(String name) {
        Command command = commands.get(name);
        if (command == null) {
            command = commands.get("default");
        }
        return command;
    }
}
