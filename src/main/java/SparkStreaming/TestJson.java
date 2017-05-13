package SparkStreaming; /**
 * Created by root on 4/12/17.
 */
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.fastjson.*;
import com.datastax.driver.core.*;
//import SparkStreaming.Pair;
//import SparkStreaming.UserQuery;

import com.sagittarius.bean.result.FloatPoint;
import com.sagittarius.exceptions.NoHostAvailableException;
import com.sagittarius.exceptions.QueryExecutionException;
import com.sagittarius.exceptions.TimeoutException;
import com.sagittarius.read.Reader;
import com.sagittarius.util.TimeUtil;

public class TestJson{

    static void testKMX(){
        Reader reader = KMXUtil.getReader();
        ArrayList<String> hosts = new ArrayList<>();
        hosts.add("128275");
        ArrayList<String> metrics = new ArrayList<>();
        metrics.add("高精度总里程(EE)");
        long start = LocalDateTime.of(2016,6,1,0,8).toEpochSecond(TimeUtil.zoneOffset)*1000;
        long end = LocalDateTime.of(2016,6,2,23,59).toEpochSecond(TimeUtil.zoneOffset)*1000;

        Map<String, Map<String, List<FloatPoint>>> result = null;
        try {
            result = reader.getFloatRange(hosts, metrics, start, end);
            for (Map.Entry<String, Map<String, List<FloatPoint>>> entry: result.entrySet()){
                System.out.println("ID = " + entry.getKey());
                for (Map.Entry<String, List<FloatPoint>> e: entry.getValue().entrySet()){
                    System.out.println("attr = " + e.getKey() + ", " + e.getValue());
                }
            }
            System.out.println(result.get("128275").get("高精度总里程(EE)").size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        long time = TimeUtil.string2Date("2016-06-01 00:08:08", "yyyy-mm-dd hh:mm:ss");
        ArrayList<String> deviceIds= new ArrayList<>();
        deviceIds.add("128275");
        ArrayList<String> sensorIds= new ArrayList<>();
        sensorIds.add("高精度总里程(EE)");
        try {
            Map<String, Map<String, FloatPoint>> result = reader.getFloatPoint(deviceIds, sensorIds, time);
            System.out.println(result.get("128275").get("高精度总里程(EE)"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    static void testJson(){
        UserQuery a = new UserQuery();
        List<String> attr = new ArrayList<String>(); attr.add("timestamp"); attr.add("高精度总里程(EE)");
        List<String> dev = new ArrayList<String>(); dev.add("128275"); dev.add("128274"); dev.add("128276");
        //List<Pair<String,String>> cond = new ArrayList<Pair<String,String>>();
        //cond.add(new Pair<>("201704101234", "201704102234"));
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Pair<String,String> timeSlice = new Pair<>("2016-06-01 00:01:08", "2016-06-02 00:01:08");
        //cond.add(new Pair<>("-", "-"));
        Pair<String,String> jp = new Pair<>("/home/seasons/Desktop/git/New1/PAS/src/main/resources/userFunc.jar","userStuff.TimeSum");

        //a.set("12", "1", timeSlice.getFirst(), timeSlice.getSecond(), dev, attr, "DEVICE", jp.getFirst(), jp.getSecond());

        //attr.clear();

        System.out.println(JSON.toJSON(a));



        UserQuery b = JSON.parseObject(JSON.toJSONString(a), UserQuery.class);
        //SparkStreaming.UserQuery b = JSON.parseObject("{\"devs\":[\"007\",\"008\",\"006\"],\"jarPos\":{\"first\"}", SparkStreaming.UserQuery.class);
        System.out.println(b);
        System.out.println(JSON.toJSON(b));
        b.print();

        String str = "{\"allowNestedValues\":true,\"className\":\"implement.ErrorDist\",\"devList\":[\"12345\", \"234bg\"],\"extractCondition\":\"test\",\"extractEndTime\":\"2017-04-08 00:00:00.0\",\"extractStartTime\":\"2017-04-13 00:00:00.0\",\"group\":\"DAY\",\"jarName\":\"80998E7D83D04647991299674FE5B8E9\",\"projectId\":\"A202AEF18E8009458C7E2BC7B4EE06D4\",\"properties\":{},\"propertyNames\":[],\"silent\":false,\"tParaList\":[\"开关量输出-故障报警指示\",\"累计里程\"],\"taskId\":\"33DB7D1377DD0E499F161071F5D5FC9F\"}";
        UserQuery c = JSON.parseObject(str, UserQuery.class);
        //SparkStreaming.UserQuery b = JSON.parseObject("{\"devs\":[\"007\",\"008\",\"006\"],\"jarPos\":{\"first\"}", SparkStreaming.UserQuery.class);
        System.out.println(c);
        System.out.println(JSON.toJSON(c));
        c.print();

        //a.print();
    }

    static void testCassandra(){
        QueryOptions options = new QueryOptions();
        options.setConsistencyLevel(ConsistencyLevel.QUORUM);

        Cluster cluster = Cluster.builder()
                .addContactPoint("192.168.15.114")
                .withPort(9042)
                .withCredentials("cassandra", "cassandra")
                //.withQueryOptions(options)
                .build();
        System.out.println("yes");
        Session session = cluster.connect();//"sagittarius");
        ResultSet resultSet = session.execute("select * from sagittarius.data_int;");
        //ResultSet resultSet = session.execute("select * from system_schema.keyspaces;");
        //resultSet = session.execute("select * from system.data_int;");
        //SELECT table_name FROM system_schema.tables
        for (Row row : resultSet)
            {
                     System.out.println(row);
           }
        System.out.println("succeed!");
        //session.execute("select columnfamily_name from schema_columnfamilies where keyspace_name=\'sagittarius\';");
    }

    static void testSth() throws Exception{
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //format1.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        Pair<String,String> timeSlice = new Pair<>("2016-03-01 00:09:08", "2016-04-03 00:02:08");
        try{
            long start0 = TimeUtil.string2Date(timeSlice.getFirst(), format1);
            long end0 = TimeUtil.string2Date(timeSlice.getSecond(), format1);
            System.out.println(start0 + ", " + end0);
            long daytime = 86400000;
            long offset = daytime / 3 * 2;
            start0 -= offset ; end0 -= offset;
            long start = start0 / daytime * daytime + daytime;
            long end = end0 / daytime * daytime;
            if (start <= end0){
                System.out.println((start0 + offset) + ", " + format1.format(start0 + offset) + ", " + format1.format(start - 1 + offset));
            }
            while (start < end){
                System.out.println((start + offset) + ", " + format1.format(start + offset) + ", " + format1.format(start + daytime - 1 + offset) + ", " + format2.format(start + offset));
                start += daytime;
            }
            if (start0 <= end){
                System.out.println(format1.format(end + offset) + ", " + format1.format(end0 + offset));
            }
            if (start > end){
                System.out.println(format1.format(start0 + offset) + ", " + format1.format(end0 + offset));
            }
            /*Date startDate = format1.parse(timeSlice.getFirst());
            Date endDate = format1.parse(timeSlice.getSecond());

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(startDate);

            while(calendar.getTime().before(endDate)){
                Date d0 = calendar.getTime();
                //Date d1(d0.getYear(), d);
                long d1 = TimeUtil.string2Date(format1.format(d0), format1);
                long d2 = TimeUtil.string2Date(format2.format(d0), format2);
                System.out.println(format2.format(calendar.getTime()) + ", " + d1 + ", " + d2);

                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }*/
        }
        catch(Exception e){
            e.printStackTrace();
        }
        /*File dir1 = new File("/home/seasons/Desktop/MRO");
        File file1 = new File("/home/seasons/Desktop/MRO/", "abc.csv");
        //File theDir = new File(DirectoryPath);
        if (!dir1.exists()){
            System.out.println("create dir!");
            if (dir1.mkdirs())
                System.out.println("successfully create dir!");
        }
        if (!file1.exists()){
            System.out.println("create file!");
            file1.createNewFile();
        }
        System.out.println(file1.getAbsolutePath());
        FileWriter fw = new FileWriter(file1, true);
        StringBuilder head = new StringBuilder();
        head.append("DeviceID,").append("Result\n");
        fw.write(head.toString());
        fw.flush();
        fw.close();*/
        /*SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            System.out.println("time1: " + TimeUtil.string2Date("2016-06-01 00:00:00.000000000", format1));
            System.out.println("time2: " + LocalDateTime.of(2016, 6, 1, 0, 8).toEpochSecond(TimeUtil.zoneOffset) * 1000);
        } catch (Exception e){
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args) throws Exception{
        /*List<Object> i1 = new ArrayList<>();
        i1.add(14); i1.add(17);
        List<Object> i2 = new ArrayList<>();
        i2.add(-3); i2.add(-1); i2.add(-1005);
        System.out.println(i1);
        System.out.println(i2);
        i1.addAll(i2);
        System.out.println(i1);*/
        //testJson();
        //testCassandra();
        //testKMX();
        testSth();
    }
}
