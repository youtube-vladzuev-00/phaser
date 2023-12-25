import java.util.List;

public abstract class CompositeTask<S extends Task> extends Task {
    private final List<S> subtasks;

    public CompositeTask(final long id, final List<S> subtasks) {
        super(id);
        this.subtasks = subtasks;
    }

    @Override
    public final void perform() {
        subtasks.forEach(this::perform);
    }

    protected abstract void perform(final S subtask);
}
