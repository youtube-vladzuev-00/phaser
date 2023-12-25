import java.util.List;
import java.util.concurrent.Phaser;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.util.Collections.emptyList;

public final class Runner {
    public static void main(final String... args) {
        final Phaser phaser = new Phaser(3) {

            @Override
            protected boolean onAdvance(final int phase, final int parties) {
                out.println();
                out.printf("Thread: %s\n", currentThread().getName());
                out.printf("Current phase: %d\n", phase);
                out.printf("Current parties: %d\n", parties);
                out.println();
                return super.onAdvance(phase, parties);
            }
        };

        final LeafTask firstLeafTask = new LeafTask(0, 5, phaser);
        final LeafTask secondLeafTask = new LeafTask(1, 3, phaser);
        final LastLeafTask firstLastLeafTask = new LastLeafTask(0, 1, phaser);
        final Subtask firstSubtask = new Subtask(0, List.of(firstLeafTask, secondLeafTask), firstLastLeafTask);

        final LeafTask thirdLeafTask = new LeafTask(2, 6, phaser);
        final LastLeafTask secondLastLeafTask = new LastLeafTask(1, 4, phaser);
        final Subtask secondSubtask = new Subtask(1, List.of(thirdLeafTask), secondLastLeafTask);

        final LastLeafTask thirdLastLeafTask = new LastLeafTask(2, 7, phaser);
        final Subtask thirdSubtask = new Subtask(2, emptyList(), thirdLastLeafTask);

        final MainTask mainTask = new MainTask(0, List.of(firstSubtask, secondSubtask, thirdSubtask));

        mainTask.perform();
    }
}
