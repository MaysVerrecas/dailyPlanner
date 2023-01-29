package task;

public enum Repeatability {

        SINGLE("Однократная"),
        DAILY("Ежедневная"),
        WEEKLY("Еженедельная"),
        MONTHLY("Ежемесячная"),
        YEARLY("Ежегодная");
        private final String title;
        Repeatability(String title) {
            this.title = title;
        }

        public String getTitle() {
                return title;
        }
}
