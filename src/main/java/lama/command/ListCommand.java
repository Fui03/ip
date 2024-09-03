package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;
import lama.task.Task;

/**
 * Represents a command to list the task list.
 * Subclass of command class.
 */
public class ListCommand extends Command {

    /**
     * Run the command to show the list of task list.
     *
     * @param taskList Task list being shown.
     * @param storage Storage used to save or load task, although not used in this command.
     * @param ui User interface that interacts with user.
     * @throws LamaException Thrown if an error occurs during the execution of the command.
     */
    @Override
    public String run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        ui.showListCommand(taskList);
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            output.append((i + 1)).append(".").append(task).append("\n");
        }

        return output.toString();
    }

}
