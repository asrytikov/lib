import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Book implements Runnable {

    // наименование книги
    private String bookName;

    // время на которое дается книга
    private int timeReady = 2000;

    // если = true, то книга только для чтения в читальном зале
    private boolean byReadyRoom = false;

    // список читателей, которые хотят взять книгу для чтения
    private Queue<PeopleReader> lstPeoplesReader = new ConcurrentLinkedQueue<PeopleReader>();

    // конструктор
    public Book(String bookName, boolean byReadyRoom, int timeReady) {
        this.bookName = bookName;
        this.byReadyRoom = byReadyRoom;
        this.timeReady = timeReady;

        System.out.println("Create new book: name = " + bookName +
                ", byReadyRoom = " + byReadyRoom + ", timeReady = " + timeReady);
    }

    // геттеры
    public String getBookName() {
        return bookName;
    }

    public Queue<PeopleReader> getLstPeoplesReader() {
        return lstPeoplesReader;
    }

    public boolean isByReadyRoom() {
        return byReadyRoom;
    }

    // добавляет в очередь на чтение книги читателя
    public void addToQueueForReady(PeopleReader peopleReader) {
        lstPeoplesReader.offer(peopleReader);
    }

    // работа потока для обработки очереди на чтение книги
    @Override
    public void run() {
        try {
            while (!lstPeoplesReader.isEmpty()) {
                PeopleReader peopleReader = lstPeoplesReader.poll();

                readyBook(new Random().nextInt(this.timeReady), peopleReader.getPeopleName());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // метод процесса чтения книги, параметр timeReady задает время, за которое книга будет читаться
    public void readyBook(int timeReady, String peopleName) throws InterruptedException{
        System.out.println(peopleName + ": " + this.bookName + " start is reading...");
        Thread.sleep(timeReady);
        System.out.println(peopleName + ": " + this.bookName + " stop reading...");
    }
}
