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

public class DeleteCommandTest {

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

        Ui ui = new Ui();
        Storage storage = new Storage("./testAdd.txt");
        Task todo = new Todo("Read Book");
        Task todo2 = new Todo("Return Book");
        TaskList taskList = new TaskList();

        taskList.add(todo);
        taskList.add(todo2);

        Command deleteCommand = new DeleteCommand(1);

        assertEquals(2, taskList.size());
        assertEquals("[T][ ] Read Book", taskList.get(0).toString());
        assertEquals("[T][ ] Return Book", taskList.get(1).toString());

        deleteCommand.run(taskList, storage, ui);
        assertEquals(1, taskList.size());

        String output = BAR + "\r\nNoted. I've removed this task:\r\n  " + todo2.toString()
                +"\r\nNow you have 1 tasks in the list.\r\n" + BAR + "\n\r\n";
        assertEquals(output, outputStream.toString());

        TaskList storageTaskList = new TaskList(storage.loadTask());
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
