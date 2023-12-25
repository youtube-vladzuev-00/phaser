import java.util.concurrent.Phaser;

public final class LeafTask extends AbstractLeafTask {

    public LeafTask(final long id, final long secondDuration, final Phaser phaser) {
        super(id, secondDuration, phaser);
    }

    @Override
    protected void onFinish(final Phaser phaser) {
        phaser.arriveAndAwaitAdvance();
    }

}
