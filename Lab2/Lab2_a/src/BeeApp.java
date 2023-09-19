public class BeeApp {
    public BeeApp(int beesNumber, int n, int m) {
        SearchManager manager = SearchManager.getInstance();
        manager.createGrid(n, m);

        for (int i = 0; i < beesNumber; ++i) {
            SearcherThread thread = new SearcherThread();
            thread.start();
        }
    }
}