package magnit.test.Main;

import magnit.test.model.DatabaseProperties;
import magnit.test.service.MainService;


public class MainClass {

    public static void main(String[] args) {

        System.out.println("Start program!");
        long startTime = System.currentTimeMillis();


        if(args.length != 5){
            System.out.println("\n Error: Program parameters a not valid ... \n");
            System.out.println("\n Required parameters by order: databaseDriver databaseUrl userName password entryCount\n");
            System.out.println("Example:  com.mysql.jdbc.Driver \"jdbc:mysql://localhost:3306/magnitDB?autoReconnect=true\" magnit magnit 1000000");
            return;
        }

        DatabaseProperties.DATABASE_PROPERTIES.setDataBaseDriver(args[0]);
        DatabaseProperties.DATABASE_PROPERTIES.setUrl(args[1]);
        DatabaseProperties.DATABASE_PROPERTIES.setUserName(args[2]);
        DatabaseProperties.DATABASE_PROPERTIES.setPassword(args[3]);

        int entryCount = 0;
        try {

            entryCount = Integer.parseInt(args[4]);

            if(entryCount <= 0){
                throw new NumberFormatException();
            }
        }catch (NumberFormatException e){
            System.err.println("\n Entry count must be integer and > 0\n");
            return;
        }

        DatabaseProperties.DATABASE_PROPERTIES.setEntryCount(entryCount);

        MainService mainService = new MainService();
        mainService.run();

        long totalTime = (System.currentTimeMillis() - startTime)/1000;

        System.out.println("Program runnimg time : "+totalTime+" seconds");
    }
}
