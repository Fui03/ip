package lama.command;

import lama.Storage;
import lama.TaskList;
import lama.Ui;
import lama.task.Task;

/**
 * Represents a command to find a task given by a specified word.
 */
public class FindCommand extends Command {

    private String word;

    /**
     * Construct a FindCommand object with a string of specified word.
     *
     * @param word The keyword to search.
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Runs the find command, which searches for tasks containing specified
     * word given by the user.
     *
     * @param taskList Task list to search.
     * @param storage Storage to save or load tasks. although not in used in this command.
     * @param ui User interface that interact with user.
     */
    @Override
    public String run(TaskList taskList, Storage storage, Ui ui) {
        TaskList tasks = taskList.find(word);
        ui.showFindCommand(tasks);
        if (tasks.size() == 0) {
            return "No matching tasks found!";
        } else {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                output.append((i + 1)).append(".").append(task).append("\n");
            }

            return output.toString();
        }
    }
}
