package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;
import lama.task.Task;

public class DeleteCommand extends Command {

    private int indexOfDeleteCommand;

    public DeleteCommand(int indexOfDeleteCommand) {
        this.indexOfDeleteCommand = indexOfDeleteCommand;
    }

    @Override
    public void run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        try {
            Task task = taskList.remove(indexOfDeleteCommand);
            ui.showDeleteCommandHeader();
            System.out.println("  " + task);
            ui.showDeleteCommandFooter(taskList);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new LamaException("Sorry, the number given exceed the bound of list");
        }
    }
}
