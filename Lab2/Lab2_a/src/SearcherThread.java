import java.util.List;

public class SearcherThread extends Thread {

    @Override
    public void run() {

        SearchManager manager = SearchManager.getInstance();
        List<Boolean> list = manager.getNextSearchPlace();

        while (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i)) {
                    manager.foundTarget();
                    return;
                }
            }

            list = manager.getNextSearchPlace();
        }

    }
}
