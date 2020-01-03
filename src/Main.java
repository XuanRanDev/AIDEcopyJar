import java.io.*;
import java.nio.channels.*;
import java.text.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        File file=new File("/storage/emulated/0/.aide/maven");
        File file2=new File("/data/user/0/com.aide.ui/no_backup/.aide/");
        File file4=new File("/data/user/0/com.aide.ui2/no_backup/.aide/");
        File file3=new File("/storage/emulated/0/Test/");
        try
        {
            copy2(file2, file3);
            System.out.println("复制完成");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("出现异常 错误原因:" + e.getMessage());
        }
    }
    private void deleteFile(File file)
    {

        file.delete();
    }

    public static void copy2(File file, File toFile) throws Exception
    {
        byte[] b = new byte[1024];
        int a;
        FileInputStream fis;
        FileOutputStream fos;
        if (file.isDirectory())
        {
            String filepath = file.getAbsolutePath();
            filepath = filepath.replaceAll("\\\\", "/");
            String toFilepath = toFile.getAbsolutePath();
            toFilepath = toFilepath.replaceAll("\\\\", "/");
            int lastIndexOf = filepath.lastIndexOf("/");
            toFilepath = toFilepath + filepath.substring(lastIndexOf , filepath.length());
            File copy=new File(toFilepath);
            //复制文件夹
            if (!copy.exists())
            {
                copy.mkdir();
            }
            //遍历文件夹
            for (File f : file.listFiles())
            {
                copy2(f, copy);
            }
        }
        else
        {
            if (toFile.isDirectory())
            {
                String filepath = file.getAbsolutePath();
                filepath = filepath.replaceAll("\\\\", "/");
                String toFilepath = toFile.getAbsolutePath();
                toFilepath = toFilepath.replaceAll("\\\\", "/");
                int lastIndexOf = filepath.lastIndexOf("/");
                toFilepath = toFilepath + filepath.substring(lastIndexOf , filepath.length());

                //写文件
                File newFile = new File(toFilepath);
                fis = new FileInputStream(file);
                fos = new FileOutputStream(newFile);
                while ((a = fis.read(b)) != -1)
                {
                    fos.write(b, 0, a);
                }
            }
            else
            {
                //写文件
                fis = new FileInputStream(file);
                fos = new FileOutputStream(toFile);
                while ((a = fis.read(b)) != -1)
                {
                    fos.write(b, 0, a);
                }
            }
        }
    }
}
