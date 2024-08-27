package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;
import lama.task.Task;
import lama.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AddCommandTest {

    private static final String BAR = "____________________________________________________________";
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        try {
            Files.deleteIfExists(new File("./testAdd.txt").toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void runTest() throws LamaException {

        System.setOut(new PrintStream(outputStream));

        Task todo = new Todo("Read Book");
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./testAdd.txt");

        Command addCommand = new AddCommand(todo);

        addCommand.run(taskList, storage, ui);

        String output = BAR + "\r\nGot it. I've added this task:\r\n  " + taskList.get(taskList.size() - 1)
                + "\r\nNow you have 1 tasks in the list.\r\n" + BAR + "\n\r\n";

        TaskList storageTaskList = new TaskList(storage.loadTask());
        assertEquals(1, taskList.size());
        assertEquals("[T][ ] Read Book", taskList.get(0).toString());
        assertEquals(output, outputStream.toString());
        assertEquals(1, storageTaskList.size());
        assertEquals("[T][ ] Read Book", storageTaskList.get(0).toString());
    }

    @AfterEach
    public void reset() {
        try {
            Files.deleteIfExists(new File("./testAdd.txt").toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
