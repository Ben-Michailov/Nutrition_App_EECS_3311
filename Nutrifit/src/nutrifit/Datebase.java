package nutrifit;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

public class Datebase {
    //private User user;


   // public Datebase(User user) {
    //    this.user = user;
    //}

    public void createNew(User user) throws IOException {
        String path = "user.cvs";
        File cvsUser_profile = new File(path);
        BufferedWriter bw = null;
        if(cvsUser_profile.exists()){
            try
            {
               bw = new BufferedWriter(new FileWriter(cvsUser_profile));
               bw.write("name,age,DOB,height,weight,sex\n");
               bw.write(user.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                assert bw != null;
                bw.close();
            }
        }else {
            PrintWriter out = new PrintWriter(cvsUser_profile);
            out.println("name,age,DOB,height,weight,sex");
            out.println(user.toString());

            out.close();
        }
    }
    public String[] readUser() throws IOException {
        String path = "user.cvs";
        String line="";
        BufferedReader reader = null;
        try{
            reader  = new BufferedReader(new FileReader(path));

            while((line = reader.readLine())!=null){
                String[] row = line.split(",");
                if (!row[0].equals("name")){
                    return row;
                }
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }finally {
            assert reader != null;
            reader.close();
        }

        return null;
    }

    public String[] readUser(String s) throws IOException {
        String path = "user.cvs";
        String line="";
        BufferedReader reader = null;
        try{
            reader  = new BufferedReader(new FileReader(path));

            while((line = reader.readLine())!=null){
                String[] row = line.split(",");
                if (row[0].equals(s)){
                   return row;
                }
            }

        }catch(FileNotFoundException e){
           e.printStackTrace();
        }finally {
            assert reader != null;
            reader.close();
        }

        return null;
    }
    public void editUser(User user){
        List<String> lines = new ArrayList<>();
        String path = "user.cvs";
        File cvsUser_profile = new File(path);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {

            String line;
            reader = new BufferedReader(new FileReader(path));
            while((line = reader.readLine())!=null){
                String[] row = line.split(",");
                if (row[0].equals(user.getName())){
                    lines.add(user.toString());
                }else{
                    lines.add(line);
                }
            }
            reader.close();
            writer = new BufferedWriter(new FileWriter(path));
            for(String newline : lines){
                writer.write(newline);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeUser(String s){
        List<String> lines = new ArrayList<>();
        String path = "user.cvs";

        BufferedReader reader;
        BufferedWriter writer;
        try {

            String line;
            reader = new BufferedReader(new FileReader(path));
            while((line = reader.readLine())!=null){
                String[] row = line.split(",");
                if (!row[0].equals(s)){
                    lines.add(line);

                }
            }
            reader.close();
            writer = new BufferedWriter(new FileWriter(path));
            for(String newline : lines){
                writer.write(newline);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeAll() throws IOException {
        String path = "user.cvs";
        PrintWriter out = new PrintWriter(new FileWriter(path));
        out.println("name,age,DOB,height,weight,sex");


        out.close();
    }

    public void addUser(User user) throws IOException {
        String path = "user.cvs";
        File cvsUser_profile = new File(path);
        BufferedWriter bw = null;

            try
            {
                bw = new BufferedWriter(new FileWriter(cvsUser_profile,true));
                bw.write("\n" + user.toString());
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                assert bw != null;
                bw.close();
            }
    }


/*
    public static void main(String[] args) throws IOException {
        User user = new User();
        Datebase date = new Datebase();
        user.setName("l");
        user.setAge(6);
        //date.removeUser("l");
        //date.removeAll();
        date.createNew(user);
    }


 */

}
