import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchManager {
    private final int SEMAPHORE_IS_AVAILABLE = 0;
    private final int SEMAPHORE_IS_NOT_AVAILABLE = 1;

    private static final SearchManager instance = new SearchManager();

    List<List<Boolean>> searchGrid = null;

    volatile int currentFreeRow = 0;

    boolean hasFound = false;

    volatile int semaphore = SEMAPHORE_IS_AVAILABLE;


    private SearchManager() {

    }

    public void foundTarget() {
        while (semaphore == SEMAPHORE_IS_NOT_AVAILABLE) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        semaphore = SEMAPHORE_IS_NOT_AVAILABLE;

        hasFound = true;

        System.out.println("Found by: " + Thread.currentThread().getName());

        semaphore = SEMAPHORE_IS_AVAILABLE;
    }

    public List<Boolean> getNextSearchPlace() {
        while (semaphore == SEMAPHORE_IS_NOT_AVAILABLE) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        semaphore = SEMAPHORE_IS_NOT_AVAILABLE;

        List<Boolean> returnValue = !hasFound && searchGrid.size() > currentFreeRow ? searchGrid.get(currentFreeRow) : null;

        if (returnValue != null) {
            System.out.println(Thread.currentThread().getName() + " took row: " + currentFreeRow + " " + this);
        }

        ++currentFreeRow;

        semaphore = SEMAPHORE_IS_AVAILABLE;

        return returnValue;
    }

    public void createGrid(int n, int m) {
        searchGrid = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            searchGrid.add(new ArrayList<>(m));
            for (int j = 0; j < m; ++j) {
                searchGrid.get(i).add(false);
            }
        }

        Random random = new Random();

        int i = random.nextInt(0, n);
        int j = random.nextInt(0, m);
        searchGrid.get(i).set(j, true);

        currentFreeRow = 0;
        hasFound = false;
    }

    public static SearchManager getInstance() {
        return instance;
    }
}
