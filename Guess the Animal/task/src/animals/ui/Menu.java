package animals.ui;

public interface Menu extends Runnable {







    final class Entry implements Runnable {
        private final String name;
        private final Runnable action;

        Entry(final String name, final Runnable action) {
            this.name = name;
            this.action = action;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public void run() {
            action.run();
        }
    }
}
