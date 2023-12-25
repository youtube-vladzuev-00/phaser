import java.util.concurrent.Phaser;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class AbstractLeafTask extends Task {
    private final long secondDuration;
    private final Phaser phaser;

    public AbstractLeafTask(final long id, final long secondDuration, final Phaser phaser) {
        super(id);
        this.secondDuration = secondDuration;
        this.phaser = phaser;
    }

    @Override
    public final void perform() {
        try {
            out.printf("%s is starting\n", this);
            SECONDS.sleep(secondDuration);
            out.printf("%s has finished\n", this);
        } catch (final InterruptedException exception) {
            currentThread().interrupt();
        } finally {
            onFinish(phaser);
        }
    }

    protected abstract void onFinish(final Phaser phaser);
}
