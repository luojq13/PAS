package SparkStreaming; /**
 * Created by root on 4/12/17.
 */

import java.util.List;
import java.util.ArrayList;
import SparkStreaming.Pair;

public class UserQuery {

    private String taskId;
    private String extractCondition; //抽取条件
    private String extractStartTime; //开始时间
    private String extractEndTime; //结束时间
    private List<String> devList;//
    private String projectId; //项目编号
    private String className; //类路径
    private String jarName; //jar包路径
    private String group;//分组方式
    List<String> floatTParaList; //工矿参数列表
    List<String> stringTParaList;
    List<String> doubleTParaList;
    List<String> intTParaList;
    List<String> longTParaList;
    List<String> booleanTParaList;
    List<String> geoTParaList;

    public UserQuery(){
        devList = new ArrayList<String>();
        floatTParaList = new ArrayList<String>();
        stringTParaList = new ArrayList<String>();
        doubleTParaList = new ArrayList<String>();
        intTParaList = new ArrayList<String>();
        longTParaList = new ArrayList<String>();
        booleanTParaList = new ArrayList<String>();
        geoTParaList = new ArrayList<String>();
    }

    public UserQuery(UserQuery a){
        devList = new ArrayList<String>();
        floatTParaList = new ArrayList<String>();
        stringTParaList = new ArrayList<String>();
        doubleTParaList = new ArrayList<String>();
        intTParaList = new ArrayList<String>();
        longTParaList = new ArrayList<String>();
        booleanTParaList = new ArrayList<String>();
        geoTParaList = new ArrayList<String>();
        this.set(a.get_taskId(),
                a.get_extractCondition(),
                a.get_extractStartTime(),
                a.get_extractEndTime(),
                a.get_devList(),
                a.get_className(),
                a.get_jarName(),
                a.get_group(),
                a.get_floatTParaList(),
                a.get_stringTParaList(),
                a.get_doubleTParaList(),
                a.get_intTParaList(),
                a.get_longTParaList(),
                a.get_booleanTParaList(),
                a.get_geoTParaList());
    }

    public String get_taskId(){
        return taskId;
    }

    public String get_extractCondition(){
        return extractCondition;
    }

    public String get_extractStartTime(){
        return extractStartTime;
    }

    public String get_extractEndTime(){
        return extractEndTime;
    }

    public List<String> get_devList(){
        return devList;
    }

    public String get_jarName(){
        return jarName;
    }

    public String get_className(){
        return className;
    }

    public String get_group(){
        return group;
    }

    public List<String> get_floatTParaList(){
        return floatTParaList;
    }

    public List<String> get_stringTParaList(){
        return stringTParaList;
    }

    public List<String> get_doubleTParaList(){
        return doubleTParaList;
    }

    public List<String> get_intTParaList(){
        return intTParaList;
    }

    public List<String> get_longTParaList(){
        return longTParaList;
    }

    public List<String> get_booleanTParaList(){
        return booleanTParaList;
    }

    public List<String> get_geoTParaList(){
        return geoTParaList;
    }

    public void set(String tid, String c, String t1, String t2, List<String> d, String cn, String jn, String gp, List<String> a1, List<String> a2, List<String> a3,List<String> a4, List<String> a5, List<String> a6, List<String> a7){
        taskId = tid;
        extractCondition = c;
        extractStartTime = t1;
        extractEndTime = t2;
        devList.clear(); devList.addAll(d);
        group = gp;
        jarName = jn;
        className = cn;
        floatTParaList.clear(); floatTParaList.addAll(a1);
        stringTParaList.clear(); stringTParaList.addAll(a2);
        doubleTParaList.clear(); doubleTParaList.addAll(a3);
        intTParaList.clear(); intTParaList.addAll(a4);
        longTParaList.clear(); longTParaList.addAll(a5);
        booleanTParaList.clear(); booleanTParaList.addAll(a6);
        geoTParaList.clear(); geoTParaList.addAll(a7);
    }

    public void set_taskId(String t){
        taskId = t;
    }

    public void set_extractCondition(String c){
        extractCondition = c;
    }

    public void set_extractStartTime(String t1){
        extractStartTime = t1;
    }

    public void set_extractEndTime(String t2){
        extractEndTime = t2;
    }

    public void set_devList(List<String> d){
        devList = new ArrayList<String>(d);
    }

    public void set_jarName(String jn){
        jarName = jn;
    }

    public void set_className(String cn){
        className = cn;
    }

    public void set_group(String g){
        group = g;
    }

    public void set_floatTParaList(List<String> p){
        floatTParaList.clear(); floatTParaList.addAll(p);
    }

    public void set_stringTParaList(List<String> p){
        stringTParaList.clear(); stringTParaList.addAll(p);
    }

    public void set_doubleTParaList(List<String> p){
        doubleTParaList.clear(); doubleTParaList.addAll(p);
    }

    public void set_intTParaList(List<String> p){
        intTParaList.clear(); intTParaList.addAll(p);
    }

    public void set_longTParaList(List<String> p){
        longTParaList.clear(); longTParaList.addAll(p);
    }

    public void set_booleanTParaList(List<String> p){
        booleanTParaList.clear(); booleanTParaList.addAll(p);
    }

    public void set_geoTParaList(List<String> p){
        geoTParaList.clear(); geoTParaList.addAll(p);
    }

    public void print(){
        System.out.println("#\ntaskId: " + taskId);
        System.out.print("deviceID: ");
        if (devList != null)
           for (String str: devList)
                System.out.print(str + ",");
        System.out.println();
        System.out.print("attributeName: ");
        if (floatTParaList != null)
            for (String str: floatTParaList)
                System.out.print(str + ",");
        System.out.println();
        System.out.println("conditions: " + extractCondition);
        System.out.println("time: " + extractStartTime + ", " + extractEndTime);
        //for (Pair<String,String> c: conditions)
        //    System.out.print("(" + c.getFirst() + "," + c.getSecond() + "),");
        System.out.println("divisionType: " + group);
        System.out.println("jarPosition: " + jarName + "," + className);
    }

}