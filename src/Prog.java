public class Prog {

    public static void main(String[] args) {
        System.out.println("Application started");
        int booksFreeCount = 25;
        int booksByReadyRoomCount = 5;
        int peoplesCount = 10;

        BookLibrary bookLibrary = new BookLibrary(booksFreeCount, booksByReadyRoomCount, peoplesCount);

        try {
            bookLibrary.startWorkLibrary();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Application stopped");
    }
}
