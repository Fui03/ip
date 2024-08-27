package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;
import lama.task.Task;
import lama.task.Todo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    private static final String BAR = "____________________________________________________________";
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    public void runTest() throws LamaException {

        System.setOut(new PrintStream(outputStream));

        Task todo = new Todo("Read Book");
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./testAdd.txt");

        taskList.add(todo);
        Command listCommand = new ListCommand();

        listCommand.run(taskList, storage, ui);

        String output = BAR + "\r\nHere are the tasks in your list:\r\n1."
                + taskList.get(0) + "\r\n" + BAR + "\n\r\n";

        assertEquals(output, outputStream.toString());
        assertEquals(1, taskList.size());
        assertEquals("[T][ ] Read Book", taskList.get(0).toString());
    }

}
