public class Hello {
    public static void main(String[] args) {
        System.out.println("Process is running");

        while (true) {
            try {
                Thread.sleep(1000); 
                System.out.println("Still running...");
            } catch (InterruptedException e) {
                System.out.println("Interrupted, exiting...");
                break;
            }
        }
    }
}
