package magnit.test.service;

import magnit.test.model.DatabaseProperties;
import magnit.test.pojo.Entry;
import magnit.test.pojo.ResultEntries;
import magnit.test.pojo.ResultEntry;

import java.util.ArrayList;


public class MainService {

    private ServiceDaoDatabaseActionsImpl serviceDaoDatabaseActionsImpl;
    private XmlService xmlService;

    public MainService(){
        this.serviceDaoDatabaseActionsImpl = new ServiceDaoDatabaseActionsImpl();
        this.xmlService = new XmlService();
    }

    public void run(){
        System.out.println("Create work directory if not exist..."+XmlService.WORK_DIRECTORY);
        xmlService.createWorkDirectoryIfNotExist();
        System.out.println("Work directory "+XmlService.WORK_DIRECTORY+" is created.");
        System.out.println("Update fields in database . "+ DatabaseProperties.DATABASE_PROPERTIES.getEntryCount()+ " records.");
        serviceDaoDatabaseActionsImpl.updateFields();
        System.out.println("Success update fields in database.");
        System.out.println("Select fileds from database.");
        ArrayList<Entry> entries = serviceDaoDatabaseActionsImpl.selectFields();
        System.out.println("Create 1.xml file ...");
        xmlService.createFirstXmlFile(entries);
        System.out.println("Transform 1.xml in to 2.xml");
        xmlService.transformFirstXmlToSecondXml();
        System.out.println("Unmarshal 2.xml");
        ResultEntries resultEntries = xmlService.getEntriesFromSecondXml();
        System.out.println("Success.");

        long summa=0;
        if(resultEntries!=null){
            for(ResultEntry resultEntry : resultEntries.getEntries()){
                summa += resultEntry.getField();
            }
        }

        System.out.println("\n Summa = " +summa+" \n" );

    }
}
