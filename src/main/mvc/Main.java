public class Main {
    public static void main(String[] args) {
        // Initialization

        View view = new View();
        Controller controller = new Controller(new BeverageMachine(), view);
        // Run
        controller.processUser();
    }
}
