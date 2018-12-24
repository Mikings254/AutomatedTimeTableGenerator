package front;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import scheduler.*;

public class TimetableAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    List<String> start, end;
    List<String> studentgroup, nosubject, stgrpsubject, subjecttime;
    List<String> teacher, teachersubject;
    String hoursperday, breakstart, breakend, daysperweek;
    int cumusubcount = 0;

    public String fromFile() {
        //invokes method to take input
        new inputdata().takeinput();

        //invokes algorithm
        new SchedulerMain();

        //grabs final chromosome i.e. the output
        Chromosome finalson = SchedulerMain.finalson;

        //sets the final chromosome in request scope to be fetched by view page
        getServletRequest().setAttribute("son", finalson);

        return SUCCESS;
    }

    public String fromForm() throws IOException {

        inputdata id = new inputdata();
        id.file = new File("c:\\test\\input" + System.currentTimeMillis() + ".txt");

        inputdata.daysperweek = Integer.parseInt(daysperweek);
        inputdata.hoursperday = Integer.parseInt(hoursperday);
        inputdata.nostudentgroup = studentgroup.size();

        System.out.println("===========================================");

        System.out.println("sg " + studentgroup);
        System.out.println("sgsubs " + stgrpsubject);
        System.out.println("noofsubs " + nosubject);
        System.out.println("teac " + teacher);
        System.out.println("tecsubs " + teachersubject);
        System.out.println("subtime " + subjecttime);
        System.out.println("daysperweek " + daysperweek);
        System.out.println("hoursperday " + hoursperday);

        BufferedWriter bw = new BufferedWriter(new FileWriter(id.file));
        bw.write("studentgroups");
        bw.newLine();

        System.out.println("===========================================");
        for (int i = 0; i < studentgroup.size(); i++) {

            inputdata.studentgroup[i] = new StudentGroup();

            StudentGroup temp = inputdata.studentgroup[i];

            temp.setId(i);
            temp.setName(studentgroup.get(i));
            temp.setNosubject(nosubject.get(i));

            //new
            bw.write(studentgroup.get(i) + " ");

            int nosub = Integer.parseInt(nosubject.get(i));
            String[] sub = new String[nosub];
            int[] hrs = new int[nosub];
            for (int j = 0; j < nosub; j++) {
                sub[j] = stgrpsubject.get(cumusubcount);

                hrs[j] = Integer.parseInt(subjecttime.get(cumusubcount));
                //new
                bw.write(sub[j] + " " + hrs[j] + " ");

                cumusubcount++;
            }

            temp.setSubject(sub);
            temp.setHours(hrs);
            //new
            bw.newLine();
        }

        //new
        bw.write("teachers");
        bw.newLine();

        inputdata.noteacher = teacher.size();
        for (int i = 0; i < teacher.size(); i++) {

            inputdata.teacher[i] = new Teacher();
            Teacher tmp = inputdata.teacher[i];

            tmp.setId(i);
            tmp.setName(teacher.get(i));
            tmp.setSubject(teachersubject.get(i));

            //new
            bw.write(teacher.get(i)+" ");
            String [] ss=teachersubject.get(i).split(" ");
            for (int j = 0; j < ss.length; j++) {
                String s = ss[j];
                bw.write(s+" ");
            }
            
            bw.newLine();
        }

        bw.write("end");
        bw.flush();
        bw.close();
        
        
    
        
        //after getting all input, teachers are assigned to each subject
        
        //old
       
        //id.assignTeacher();

        id.takeinput(inputdata.hoursperday, inputdata.daysperweek);
        new SchedulerMain();

        Chromosome finalson = SchedulerMain.finalson;
        getServletRequest().setAttribute("son", finalson);
        
        //old
        return SUCCESS;
    }
    
    
     public String fromFormV1() throws IOException {

        inputdatamain id = new inputdatamain();
        id.file = new File("c:\\test\\input" + System.currentTimeMillis() + ".txt");

        id.daysperweek = Integer.parseInt(daysperweek);
        id.hoursperday = Integer.parseInt(hoursperday);
        id.nostudentgroup = studentgroup.size();

        System.out.println("===========================================");

        System.out.println("sg " + studentgroup);
        System.out.println("sgsubs " + stgrpsubject);
        System.out.println("noofsubs " + nosubject);
        System.out.println("teac " + teacher);
        System.out.println("tecsubs " + teachersubject);
        System.out.println("subtime " + subjecttime);
        System.out.println("daysperweek " + daysperweek);
        System.out.println("hoursperday " + hoursperday);

        BufferedWriter bw = new BufferedWriter(new FileWriter(id.file));
        bw.write("studentgroups");
        bw.newLine();

        System.out.println("===========================================");
        for (int i = 0; i < studentgroup.size(); i++) {

            id.studentgroup[i] = new StudentGroup();

            StudentGroup temp = id.studentgroup[i];

            temp.setId(i);
            temp.setName(studentgroup.get(i));
            temp.setNosubject(nosubject.get(i));

            //new
            bw.write(studentgroup.get(i) + " ");

            int nosub = Integer.parseInt(nosubject.get(i));
            String[] sub = new String[nosub];
            int[] hrs = new int[nosub];
            for (int j = 0; j < nosub; j++) {
                sub[j] = stgrpsubject.get(cumusubcount);

                hrs[j] = Integer.parseInt(subjecttime.get(cumusubcount));
                //new
                bw.write(sub[j] + " " + hrs[j] + " ");

                cumusubcount++;
            }

            temp.setSubject(sub);
            temp.setHours(hrs);
            //new
            bw.newLine();
        }

        //new
        bw.write("teachers");
        bw.newLine();

        id.noteacher = teacher.size();
        for (int i = 0; i < teacher.size(); i++) {

            id.teacher[i] = new Teacher();
            Teacher tmp = id.teacher[i];

            tmp.setId(i);
            tmp.setName(teacher.get(i));
            tmp.setSubject(teachersubject.get(i));

            //new
            bw.write(teacher.get(i)+" ");
            String [] ss=teachersubject.get(i).split(" ");
            for (int j = 0; j < ss.length; j++) {
                String s = ss[j];
                bw.write(s+" ");
            }
            
            bw.newLine();
        }

        bw.write("end");
        bw.flush();
        bw.close();
        
        
    
        
        //after getting all input, teachers are assigned to each subject
        
        //old
       
        //id.assignTeacher();

        id.takeinput(id.hoursperday, id.daysperweek);
        new SchedulerMain();

        Chromosome finalson = SchedulerMain.finalson;
        getServletRequest().setAttribute("son", finalson);
        
        //old
        return SUCCESS;
    }

    public List<String> getStart() {
        return start;
    }

    public void setStart(List<String> start) {
        this.start = start;
    }

    public List<String> getEnd() {
        return end;
    }

    public void setEnd(List<String> end) {
        this.end = end;
    }

    public List<String> getStudentgroup() {
        return studentgroup;
    }

    public void setStudentgroup(List<String> studentgroup) {
        this.studentgroup = studentgroup;
    }

    public List<String> getNosubject() {
        return nosubject;
    }

    public void setNosubject(List<String> nosubject) {
        this.nosubject = nosubject;
    }

    public List<String> getStgrpsubject() {
        return stgrpsubject;
    }

    public void setStgrpsubject(List<String> stgrpsubject) {
        this.stgrpsubject = stgrpsubject;
    }

    public List<String> getSubjecttime() {
        return subjecttime;
    }

    public void setSubjecttime(List<String> subjecttime) {
        this.subjecttime = subjecttime;
    }

    public List<String> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<String> teacher) {
        this.teacher = teacher;
    }

    public List<String> getTeachersubject() {
        return teachersubject;
    }

    public void setTeachersubject(List<String> teachersubject) {
        this.teachersubject = teachersubject;
    }

    public String getHoursperday() {
        return hoursperday;
    }

    public void setHoursperday(String hoursperday) {
        this.hoursperday = hoursperday;
    }

    public String getBreakstart() {
        return breakstart;
    }

    public void setBreakstart(String breakstart) {
        this.breakstart = breakstart;
    }

    public String getBreakend() {
        return breakend;
    }

    public void setBreakend(String breakend) {
        this.breakend = breakend;
    }

    public String getDaysperweek() {
        return daysperweek;
    }

    public void setDaysperweek(String daysperweek) {
        this.daysperweek = daysperweek;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return this.request;
    }

}
