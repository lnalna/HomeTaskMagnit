package magnit.test.service;

import magnit.test.dao.DaoDatabaseActions;
import magnit.test.model.DatabaseProperties;
import magnit.test.pojo.Entry;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ServiceDaoDatabaseActionsImpl {

    private DaoDatabaseActions daoDatabaseActions;

    public ServiceDaoDatabaseActionsImpl(){
        this.daoDatabaseActions = new DaoDatabaseActions();
    }


    public ArrayList<Entry> selectFields(){

        ArrayList<Entry> entries = null;
        try {
             entries = daoDatabaseActions.selectAllFields();
        } catch (Exception e) {
            System.err.println("\n Error Select Fields from DataBase\n");
            e.printStackTrace();
            System.exit(0);
        }
        return entries;
    }

    public void updateFields() {

        int entryCount = DatabaseProperties.DATABASE_PROPERTIES.getEntryCount();
        int batchCount = entryCount / DatabaseProperties.BATCH_SIZE;
        int restEntry = entryCount % DatabaseProperties.BATCH_SIZE;

        try {
            daoDatabaseActions.deleteAllFields();
        } catch (Exception e) {
            System.err.println("\n Can not insert fields in to database, " + e.getMessage()+"\n");
            System.exit(0);
        }

        Integer finishedTasks = 0;
        ExecutorService tasksPool = Executors.newFixedThreadPool(DatabaseProperties.POOL_SIZE);
        ArrayList<Future<Integer>> insertedBatch = new ArrayList<>();

        for (int batch = 0; batch < batchCount; batch++) {
            FieldsInserter inserter = new FieldsInserter(batch * DatabaseProperties.BATCH_SIZE+1, batch * DatabaseProperties.BATCH_SIZE + DatabaseProperties.BATCH_SIZE);
            Future<Integer> result = tasksPool.submit(inserter);
            insertedBatch.add(result);
        }


            FieldsInserter inserter = new FieldsInserter(batchCount * DatabaseProperties.BATCH_SIZE+1, batchCount * DatabaseProperties.BATCH_SIZE + restEntry);
            Future<Integer> result = tasksPool.submit(inserter);
            insertedBatch.add(result);


        while (finishedTasks < batchCount + 1) {
            finishedTasks = 0;
            for (Future<Integer> future : insertedBatch) {
                try {
                    finishedTasks += future.get();
                } catch (InterruptedException | ExecutionException ex) {
                    System.err.println("\n Can not insert fields to database, " + ex.getMessage()+"\n");
                    tasksPool.shutdown();
                    System.exit(0);
                }
            }
        }
        tasksPool.shutdown();

    }

    /**
     *  Insert fields in database using multithreads
     */
    class FieldsInserter implements Callable<Integer> {
        private Integer from;
        private Integer to;

        public FieldsInserter(Integer from, Integer to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Integer call() throws Exception {
            try {
                daoDatabaseActions.insertFields(from, to);
            } catch (Exception e) {
                throw new Exception("Error in thread [" + from.toString() + "," + to.toString() + "]");
            }
            return 1;
        }
    }

}
